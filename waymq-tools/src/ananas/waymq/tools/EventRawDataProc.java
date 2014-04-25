package ananas.waymq.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class EventRawDataProc implements Runnable {

	@Override
	public void run() {

		JFileChooser fc = new JFileChooser();
		int rlt = fc.showOpenDialog(null);
		if (rlt != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = fc.getSelectedFile();
		File dir = file.getParentFile();
		String suffix = Helper.getFileNameSuffix(file.getName());
		FilenameFilter fnf = new MyFilenameFilter(suffix);
		File[] list = dir.listFiles(fnf);
		MyRawItem head = new MyRawItem();
		List<MyRawItem> list2 = new ArrayList<MyRawItem>();

		for (File jfile : list) {

			MyRawItem item = new MyRawItem();
			try {
				item.load(jfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			list2.add(item);
		}

		System.out.println(head.headString());
		for (MyRawItem item : list2) {
			System.out.println(item);
		}
		System.out.println("/*********************************************/");
		for (MyRawItem item : list2) {
			System.out.println(item.toHtmlString());
		}

	}

	interface Key {

		String column_amount = "column_amount";
		String column_nickname = "column_nickname";
		String column_phone_id = "column_phone_id";
		String column_weight = "column_weight";
		String timestamp = "timestamp";

	}

	class MyRawItem {

		private String _nickname;
		private String _phone_id;
		private int _weight;
		private int _amount;
		private long _timestamp;

		public void load(File file) throws IOException {
			InputStream in = new FileInputStream(file);
			Reader rdr = new InputStreamReader(in, "UTF-8");
			char[] buf = new char[128];
			StringBuilder sb = new StringBuilder();
			for (;;) {
				int cc = rdr.read(buf);
				if (cc < 0)
					break;
				sb.append(buf, 0, cc);
			}
			rdr.close();
			in.close();
			JSONObject json = JSON.parseObject(sb.toString());

			this._nickname = json.getString(Key.column_nickname);
			this._phone_id = json.getString(Key.column_phone_id);
			this._weight = json.getIntValue(Key.column_weight);
			this._amount = json.getIntValue(Key.column_amount);
			this._timestamp = json.getLongValue(Key.timestamp);

		}

		public String headString() {

			StringBuilder sb = new StringBuilder();

			sb.append("\ttimestamp:");
			sb.append("\tid:");
			sb.append("\tnickname:");
			sb.append("\tweight:");
			sb.append("\tamount:");

			return sb.toString();
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();

			sb.append("\t");
			sb.append(this._timestamp);
			sb.append("\t");
			sb.append(this._phone_id);
			sb.append("\t");
			sb.append(this._nickname);
			sb.append("\t");
			sb.append(this._weight);
			sb.append("\t");
			sb.append(this._amount);

			return sb.toString();
		}

		/**
		 * 
		 <tr>
		 * <td>徐公子</td>
		 * <td>572127</td>
		 * <td>1</td>
		 * <td>10</td>
		 * </tr>
		 * */

		public String toHtmlString() {

			StringBuilder sb = new StringBuilder();

			sb.append("<tr><td>");
			sb.append(this._nickname);
			sb.append("</td><td>*****");
			sb.append(this._phone_id);
			sb.append("</td><td>");
			sb.append(this._weight);
			sb.append("</td><td>");
			sb.append(this._amount);
			sb.append("</td></tr>");

			return sb.toString();

		}
	}

	class MyFilenameFilter implements FilenameFilter {

		private final String suffix;

		public MyFilenameFilter(String suffix) {
			this.suffix = suffix;
		}

		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(this.suffix);
		}
	}

	static class Helper {

		public static String getFileNameSuffix(String name) {
			int i = name.lastIndexOf('.');
			if (i < 0)
				return name;
			return name.substring(i);
		}
	}

}
