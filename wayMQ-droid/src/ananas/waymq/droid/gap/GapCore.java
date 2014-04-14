package ananas.waymq.droid.gap;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class GapCore {

	private Exception _error;

	private final Context _context;

	public GapCore(Context context) {

		this._context = context;

	}

	public void loadConfig() {
		try {
			String url = null;
			for (int timeout = 5; timeout > 0; timeout--) {
				url = this.doLoadConfigStep(url);
				if (url == null)
					break;
			}
		} catch (Exception e) {
			this._error = e;
		}

	}

	private String doLoadConfigStep(String url) throws IOException {

		SharedPreferences sp = this.getSharedPreferences();

		if (url == null)
			url = GapConst.Default.config_json;
		url = sp.getString(GapConst.Key.config_json, url);

		String str = Helper.loadString(url);
		JSONObject json = JSON.parseObject(str);
		str = JSON.toJSONString(json, true);
		System.out.println(url + " response :");
		System.out.println(str);

		Editor edit = sp.edit();
		Set<String> keys = json.keySet();
		for (String key : keys) {
			Object val = json.get(key);
			if (val instanceof String) {
				edit.putString(key, (String) val);
			}
		}
		edit.commit();
		url = json.getString(GapConst.Key.config_json);
		return url;
	}

	static class Helper {

		private static String loadString(String url) throws IOException {

			HttpURLConnection conn = (HttpURLConnection) (new URL(url))
					.openConnection();
			int code = conn.getResponseCode();
			if (code != 200) {
				String msg = conn.getResponseMessage();
				conn.disconnect();
				msg = "HTTP " + code + " " + msg + " while " + url;
				throw new RuntimeException(msg);
			}
			InputStream in = conn.getInputStream();
			Reader rdr = new InputStreamReader(in);
			StringBuilder sb = new StringBuilder();
			char[] buf = new char[128];
			for (;;) {
				int cb = rdr.read(buf);
				if (cb < 0)
					break;
				sb.append(buf, 0, cb);
			}
			rdr.close();
			in.close();
			conn.disconnect();
			return sb.toString();
		}
	}

	public Exception getError() {
		return this._error;
	}

	public String getHomePage() {
		String value = GapConst.Default.home_page;
		SharedPreferences sp = this.getSharedPreferences();
		value = sp.getString(GapConst.Key.home_page, value);
		return value;
	}

	public SharedPreferences getSharedPreferences() {
		String name = this.getClass().getSimpleName();
		return this._context.getSharedPreferences(name, 0);
	}
}
