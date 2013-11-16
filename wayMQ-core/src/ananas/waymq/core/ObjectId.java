package ananas.waymq.core;

import java.security.MessageDigest;

public class ObjectId {

	private final String _id;

	public ObjectId(String id) {
		this._id = (id == null) ? "null" : id;
	}

	@Override
	public String toString() {
		return _id;
	}

	private final static char[] hex_char_array = "0123456789abcdef"
			.toCharArray();

	public static String sha1ForString(String s) {
		try {
			byte[] ba = s.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			ba = md.digest(ba);
			StringBuilder sb = new StringBuilder();
			for (byte b : ba) {
				sb.append(hex_char_array[(b >> 4) & 0x0f]);
				sb.append(hex_char_array[(b) & 0x0f]);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ... "0123456789012345678901234567890123456789"
		return "0000000000000000000000000000000000000000";
	}

}
