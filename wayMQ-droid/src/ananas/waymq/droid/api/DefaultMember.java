package ananas.waymq.droid.api;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class DefaultMember implements IMember {

	private String _id;
	private String _name;
	private String _phone;
	private String[] _kws;

	public DefaultMember(Properties prop) {
		this._id = Helper.safeGet(prop, Key.id);
		this._name = Helper.safeGet(prop, Key.name);
		this._phone = Helper.safeGet(prop, Key.phone);
		String kws = Helper.safeGet(prop, Key.keywords);
		this._kws = Helper.parseArray(kws);
	}

	public DefaultMember(String name, String phone, String keywords) {
		this._id = Helper.genIdByPhone(phone);
		this._name = name;
		this._phone = phone;
		String[] kws = Helper.parseArray(keywords);
		this._kws = kws;
	}

	@Override
	public Object getId() {
		return _id;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getPhone() {
		return _phone;
	}

	@Override
	public String[] getKeyWords() {
		return _kws;
	}

	interface Key {

		String name = "name";
		String phone = "phone";
		String id = "id";
		String keywords = "keywords";

	}

	@Override
	public Properties toProperties() {
		Properties prop = new Properties();
		prop.setProperty(Key.id, _id + "");
		prop.setProperty(Key.name, _name + "");
		prop.setProperty(Key.phone, _phone + "");
		prop.setProperty(Key.keywords, Helper.arrayToString(_kws));
		return prop;
	}

	static class Helper {

		public static String arrayToString(String[] array) {
			StringBuilder sb = new StringBuilder();
			for (String s : array) {
				if (s != null) {
					sb.append(s);
					sb.append(s + ", ");
				}
			}
			return sb.toString();
		}

		public static String genIdByPhone(String phone) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (char ch : phone.toCharArray()) {
				if ('0' <= ch && ch <= '9') {
					baos.write(ch);
				}
			}
			byte[] ba = baos.toByteArray();
			try {
				MessageDigest md = java.security.MessageDigest
						.getInstance("SHA-1");
				ba = md.digest(ba);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			StringBuilder sb = new StringBuilder();
			for (byte b : ba) {
				sb.append(toHexChar(b >> 4));
				sb.append(toHexChar(b));
			}
			return sb.toString();
		}

		private static char toHexChar(int b) {
			b = b & 0x0f;
			if (b < 10) {
				return (char) ('0' + b);
			} else {
				return (char) ('a' + b - 10);
			}
		}

		public static String safeGet(Properties prop, String key) {
			String value = prop.getProperty(key);
			if (value == null)
				return "";
			return value;
		}

		public static String[] parseArray(String s) {
			return s.split("[,; ]");
		}
	}

}
