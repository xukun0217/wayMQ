package waymq.util;

import java.security.MessageDigest;

public class StringUtil {

	interface Const {

		char[] hex_char_array = "0123456789abcdef".toCharArray();

	}

	public static String hashString(String s) {
		try {
			byte[] ba = s.getBytes("utf-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			ba = md.digest(ba);
			StringBuilder sb = new StringBuilder();
			char[] chs = Const.hex_char_array;
			for (byte b : ba) {
				sb.append(chs[(b >> 4) & 0x0f]);
				sb.append(chs[(b) & 0x0f]);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// .... 0123456789012345678901234567890123456789
		return "0000000000000000000000000000000000000000";
	}

}
