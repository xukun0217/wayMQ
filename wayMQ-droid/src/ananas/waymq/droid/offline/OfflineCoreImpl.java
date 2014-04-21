package ananas.waymq.droid.offline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

final class OfflineCoreImpl extends OfflineCore implements IOfflineCore {

	interface FileName {

		String member_overview_set = "member_overview_set.map";
		String member_detail_set = "member_detail_set.map";

		String event_overview_set = "event_overview_set.map";
		String event_detail_set = "event_detail_set.map";

		String meta = "meta.map";

	}

	private final Activity _act;

	public OfflineCoreImpl(Activity activity) {
		this._act = activity;
	}

	@Override
	public IMemberInfo[] findMembers(String id) {

		id = this.normalizeId(id);
		if (id == null)
			return null;
		IMemberInfo info = this.getMemberInfo(id);
		if (info == null) {
			IMemberInfo[] array = {};
			return array;
		} else {
			IMemberInfo[] array = { info };
			return array;
		}
	}

	static class MyMemberInfo implements IMemberInfo {

		private String _id;
		private String _name;

		public MyMemberInfo(String id, String name) {
			_id = id;
			_name = name;
		}

		@Override
		public String getId() {
			return this._id;
		}

		@Override
		public String getName() {
			return this._name;
		}
	}

	@Override
	public String normalizeId(String id) {
		if (id == null)
			return null;
		StringBuilder sb = new StringBuilder();
		char[] chs = id.toCharArray();
		for (char ch : chs) {
			if ('0' <= ch && ch <= '9') {
				sb.append(ch);
			}
		}
		if (sb.length() < 6) {
			return null;
		}
		id = sb.substring(sb.length() - 6);
		return id;
	}

	@Override
	public String addMember(String id, String name) {
		id = this.normalizeId(id);
		if (id == null)
			return null;
		if (name == null)
			return null;
		if (name.length() < 1)
			name = "unnamed";
		SharedPreferences sp = _act.getSharedPreferences(
				FileName.member_overview_set, 0);
		Editor edit = sp.edit();
		edit.putString(id, name);
		edit.commit();
		return id;
	}

	@Override
	public IMemberInfo getMemberInfo(String id) {
		id = this.normalizeId(id);
		if (id == null)
			return null;
		// find now
		SharedPreferences sp = _act.getSharedPreferences(
				FileName.member_overview_set, 0);
		String name = sp.getString(id, null);
		if (name == null) {
			return null;
		}
		return new MyMemberInfo(id, name);
	}

	@Override
	public void doMemberSign(String id, String name, int count, int money) {

		/**
		 * 
		 <th class="column_nickname">绰号</th> <th class="column_phone_id">
		 * id(电话号码最后六位数)</th> <th class="column_weight">人数</th> <th class="column_amount">
		 * 支付金额</th> *
		 * 
		 * */

		long now = System.currentTimeMillis();

		JSONObject json = new JSONObject();
		json.put("column_phone_id", id);
		json.put("column_nickname", name);
		json.put("column_weight", count);
		json.put("column_amount", money);
		json.put("timestamp", now);

		File path = this.getSignPathByMemberId(id);

		try {
			Helper.saveJSON(path, json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private File getSignPathByMemberId(String id) {
		String today = "no_impl_today";
		File path = this.getWorkingPath();
		path = new File(path, today + "/" + id + ".json");
		return path;
	}

	static class Helper {

		public static void saveJSON(File path, JSONObject json)
				throws IOException {

			if (!path.exists()) {
				path.getParentFile().mkdirs();
				path.createNewFile();
			}
			String str = JSON.toJSONString(json);
			OutputStream out = new FileOutputStream(path);
			Writer wtr = new OutputStreamWriter(out, "UTF-8");
			wtr.write(str);
			wtr.flush();
			wtr.close();
			out.close();
		}

		public static JSONObject loadJSON(File file) throws IOException {
			if (!file.exists())
				return null;
			InputStream in = new FileInputStream(file);
			Reader rdr = new InputStreamReader(in, "UTF-8");
			StringBuilder sb = new StringBuilder();
			char[] buf = new char[128];
			for (;;) {
				int cc = rdr.read(buf);
				if (cc < 0)
					break;
				sb.append(buf, 0, cc);
			}
			rdr.close();
			in.close();
			return JSON.parseObject(sb.toString());
		}

	}

	@Override
	public File getWorkingPath() {
		File dir = android.os.Environment.getExternalStorageDirectory();
		return new File(dir, "ananas/waymq/offline");
	}

	@Override
	public IEvent[] listEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvent getCurrentEvent() {
		// long ctime = event.createTime();
		// File dir = this.getWorkingPath();
		// dir = new File(dir, "event/" + ctime);

		return null;
	}

	@Override
	public void setCurrentEvent(IEvent event) {
		// long ctime = event.createTime();
		// SharedPreferences sp = this._act.getSharedPreferences(FileName.meta,
		// 0);
		// Editor edit = sp.edit();

	}

	@Override
	public ISign[] listSigns() {
		class FF implements FilenameFilter {

			private String _suf;

			public FF(String suffix) {
				this._suf = suffix;
			}

			@Override
			public boolean accept(File dir, String filename) {
				return filename.endsWith(_suf);
			}
		}

		File path = this.getSignPathByMemberId("");
		File dir = path.getParentFile();
		String suffix = path.getName();

		FilenameFilter filter = new FF(suffix);
		String[] list = dir.list(filter);
		Arrays.sort(list);
		// load
		List<ISign> list2 = new ArrayList<ISign>();
		for (String fname : list) {
			File file = new File(dir, fname);
			JSONObject json = null;
			try {
				json = Helper.loadJSON(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SignImpl rec = new SignImpl();
			rec.load(json);
			list2.add(rec);
		}
		return list2.toArray(new ISign[list2.size()]);
	}

}
