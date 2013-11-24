package ananas.waymq.orm.id_gen;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

	public static byte[] bytes_of_phone(String s) {
		if (s == null)
			return new byte[0];
		char[] chs = s.toCharArray();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (char ch : chs) {
			if ('0' <= ch && ch <= '9') {
				baos.write(ch);
			}
		}
		return baos.toByteArray();
	}

	public static String string_of_phone(String s) {
		if (s == null)
			return "";
		char[] chs = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char ch : chs) {
			if ('0' <= ch && ch <= '9') {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static byte[] bytes_sha1_bytes(byte[] ba) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			return md.digest(ba);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[20];
		}
	}

	private final static char[] hex_char_array = "0123456789abcdef"
			.toCharArray();

	public static String string_of_bytes(byte[] ba) {
		StringBuilder sb = new StringBuilder();
		for (byte b : ba) {
			sb.append(hex_char_array[(b >> 4) & 0x0f]);
			sb.append(hex_char_array[(b) & 0x0f]);
		}
		return sb.toString();
	}

	public static String string_sha1_phone(String number) {
		byte[] ba = Util.bytes_of_phone(number);
		ba = Util.bytes_sha1_bytes(ba);
		return Util.string_of_bytes(ba);
	}

	public static String string_sha1_string(String s) {
		if (s == null) {
			// ... "0123456789012345678901234567890123456789"
			return "0000000000000000000000000000000000000000";
		}
		byte[] ba = s.getBytes();
		ba = Util.bytes_sha1_bytes(ba);
		return Util.string_of_bytes(ba);
	}

}
