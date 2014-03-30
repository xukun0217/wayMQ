package ananas.waymq.ht5.litegroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Util {

	public static boolean checkPhoneNumber(String phone) {
		char[] chs = phone.toCharArray();
		for (char ch : chs) {
			switch (ch) {
			case ' ':
			case 0x0a:
			case 0x0d:
			case 0x09:
				break;
			default:
				if ('0' <= ch && ch <= '9') {
				} else {
					return false;
				}
				break;
			}
		}
		return true;
	}

	public static String normalizePhoneNumber(String phone) {
		StringBuilder sb = new StringBuilder();
		char[] chs = phone.toCharArray();
		for (char ch : chs) {
			if ('0' <= ch && ch <= '9') {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static void saveJsonToFile(JSONObject json, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			Writer wtr = new OutputStreamWriter(out, "UTF-8");
			wtr.write(JSON.toJSONString(json, true));
			wtr.flush();
			wtr.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject loadJsonFromFile(File file) {
		try {
			if (!file.exists()) {
				return null;
			}
			InputStream in = new FileInputStream(file);
			Reader rdr = new InputStreamReader(in, "UTF-8");
			char[] buf = new char[256];
			StringBuilder sb = new StringBuilder();
			for (;;) {
				int cc = rdr.read(buf);
				if (cc < 0)
					break;
				sb.append(buf, 0, cc);
			}
			rdr.close();
			in.close();
			return JSON.parseObject(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
