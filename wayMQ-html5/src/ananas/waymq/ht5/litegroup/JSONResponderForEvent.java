package ananas.waymq.ht5.litegroup;

import java.io.File;
import java.util.Calendar;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONResponderForEvent implements JSONResponder {

	public void process(RequestContext rc) {

		DaoForEvent dao = new DaoForEvent(rc._repo, rc._target_path);
		File path = dao.getPath();
		if (!path.exists()) {
			System.err.println("no path " + path);
			rc._json.put("error", "no_event");
			return;
		}

		String todo = rc._do;
		if (todo.equals("get")) {
			this.doGet(rc, dao);
		} else if (todo.equals("join")) {
			this.doJoin(rc, dao);
		} else if (todo.equals("leave")) {
			this.doLeave(rc, dao);
		}
	}

	private void doGet(RequestContext rc, DaoForEvent dao) {
		JSONObject out = dao.loadOutput();
		if (out == null) {
			out = this.buildOutput(rc, dao);
		}
		Set<String> keys = out.keySet();
		for (String key : keys) {
			Object value = out.get(key);
			rc._json.put(key, value);
		}
		rc._json.put("success", true);
	}

	private JSONObject buildOutput(RequestContext rc, DaoForEvent dao) {
		JSONObject group = dao.getGroupDao().loadInfo();
		JSONObject json = dao.loadInfo();
		JSONArray joinList = new JSONArray();
		File[] jfs = dao.listJoinFile();
		for (File jf : jfs) {
			JSONObject join = dao.loadJoinShip(jf);
			joinList.add(join);
		}
		// adds
		json.put("join", joinList);
		Helper.genStringByTime(json, "time_open");
		{
			String groupName = group.getString("title");
			json.put("group_title", groupName);
		}
		// save
		dao.saveOutput(json);
		return json;
	}

	private void doLeave(RequestContext rc, DaoForEvent dao) {
		String phone = rc._request.getParameter("phone");
		JSONObject json = dao.loadJoinShip(phone);
		if (json == null) {
			return;
		}
		json.put("join", false);
		dao.saveJoinShip(phone, json);
		dao.clearOutput();
		rc._json.put("success", true);
	}

	private void doJoin(RequestContext rc, DaoForEvent dao) {
		String phone = rc._request.getParameter("phone");
		String nickname = rc._request.getParameter("nickname");
		if (!Util.checkPhoneNumber(phone)) {
			rc._json.put("error", "bad_phone_number");
			return;
		}
		if (nickname == null) {
			nickname = "unnamed";
		} else {
			if (nickname.trim().length() == 0) {
				nickname = "unnamed";
			}
		}
		JSONObject json = dao.loadJoinShip(phone);
		if (json == null) {
			json = new JSONObject();
			json.put("phone", phone);
			json.put("nickname", nickname);
		}
		json.put("join", true);
		dao.saveJoinShip(phone, json);
		dao.clearOutput();
		rc._json.put("success", true);
	}

	private static JSONResponderForEvent _inst;

	public static JSONResponderForEvent getInstance() {
		if (_inst == null) {
			_inst = new JSONResponderForEvent();
		}
		return _inst;
	}

	static class Helper {

		public static void genStringByTime(JSONObject json, String key) {
			Object obj = json.get(key);
			if (obj == null)
				return;
			long time = json.getLongValue(key);
			json.put(key + "_text", timeToString(time));
		}

		final static String[] weekday = { "日", "一", "二", "三", "四", "五", "六" };

		public static String timeToString(long time) {

			StringBuilder sb = new StringBuilder();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time);

			int h, m, s, yy, mm, dd;
			yy = cal.get(Calendar.YEAR);
			mm = cal.get(Calendar.MONTH) + 1;
			dd = cal.get(Calendar.DAY_OF_MONTH);
			int week = cal.get(Calendar.DAY_OF_WEEK);
			h = cal.get(Calendar.HOUR_OF_DAY);
			m = cal.get(Calendar.MINUTE);
			s = cal.get(Calendar.SECOND);

			sb.append(yy);
			sb.append('-');
			sb.append(mm);
			sb.append('-');
			sb.append(dd);

			sb.append("(" + weekday[week % weekday.length] + ")");

			sb.append(h);
			sb.append(':');
			sb.append(m);
			sb.append(':');
			sb.append(s);

			return sb.toString();
		}

	}

}
