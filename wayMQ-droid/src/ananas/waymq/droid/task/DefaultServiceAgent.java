package ananas.waymq.droid.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DefaultServiceAgent implements ServiceAgent {

	private final Activity _activity;
	private HttpClient _http_client;
	private static String s_base_url;

	public DefaultServiceAgent(Activity activity) {
		this._activity = activity;
	}

	@Override
	public void runInBackground(BackgroundTask task) {
		BackgroundTaskContext context = new BackgroundTaskContext();
		context.task = task;
		context.activity = _activity;
		context.agent = this;
		context.start();
	}

	@Override
	public JSONObject requestJSON(String httpMethod, Map<String, String> param) {

		try {

			// get client
			HttpClient httpclient = this._http_client;
			if (httpclient == null) {
				httpclient = new DefaultHttpClient();
				this._http_client = httpclient;
			}
			// proc method
			if (httpMethod == null) {
				httpMethod = "GET";
			}
			httpMethod = httpMethod.toUpperCase();
			// make URI
			StringBuilder sb = new StringBuilder();
			List<String> keys = new ArrayList<String>(param.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				String value = param.get(key);
				key = URLEncoder.encode(key, "UTF-8");
				value = URLEncoder.encode(value, "UTF-8");
				if (sb.length() == 0) {
					sb.append('?');
				} else {
					sb.append('&');
				}
				sb.append(key);
				sb.append('=');
				sb.append(value);
			}
			String url = this.getBaseURL(httpclient);
			url = url + sb;
			// make request
			HttpUriRequest request = null;
			if (httpMethod.equals("")) {
			} else if (httpMethod.equals("GET")) {
				HttpGet htget = new HttpGet(url);
				request = htget;
			} else if (httpMethod.equals("POST")) {
				HttpPost htpost = new HttpPost(url);
				request = htpost;
			}

			// execute
			HttpResponse response = httpclient.execute(request);

			// parse reuslt
			HttpEntity entity = response.getEntity();

			JSONObject json = Helper.loadJSON(entity);
			System.out.println(url);
			System.out.println(JSON.toJSONString(json, true));
			return json;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	static class Helper {

		public static JSONObject loadJSON(HttpEntity entity) throws IOException {
			String type = entity.getContentType().getValue();

			if (type == null) {
				type = "null";
			} else if (type.equals("text/json")) {
				type = null;
			} else if (type.equals("application/javascript")) {
				type = null;
			} else {
				// do nothing
			}

			if (type == null) {
				StringBuilder sb = new StringBuilder();
				InputStream in = entity.getContent();
				Reader reader = new InputStreamReader(in, "UTF-8");
				char[] buf = new char[512];
				for (;;) {
					int cc = reader.read(buf);
					if (cc < 0)
						break;
					sb.append(buf, 0, cc);
				}
				reader.close();
				in.close();
				JSONObject json = JSON.parseObject(sb.toString());
				return json;
			} else {
				JSONObject json = new JSONObject();
				json.put("Content-Type", type);
				return json;
			}
		}

		public static void checkKV(String key, String value) {
			if (key == null | value == null) {
				throw new RuntimeException("need value for key : " + key);
			}
		}

	}

	private String getBaseURL(HttpClient httpclient) {

		String url = s_base_url;
		if (url != null)
			return url;
		try {
			// load local properties

			InputStream in = this.getClass().getResourceAsStream(
					"config.properties");
			Properties prop = new Properties();
			prop.load(in);
			in.close();
			String key = "remote.config.json";
			String value = prop.getProperty(key);
			Helper.checkKV(key, value);
			String conf_url = value;

			// load remote json
			HttpGet htget = new HttpGet(conf_url);
			HttpResponse resp = httpclient.execute(htget);
			JSONObject json = Helper.loadJSON(resp.getEntity());

			key = "endpoint";
			value = json.getString(key);
			Helper.checkKV(key, value);
			url = value;

			s_base_url = url;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub

	}

}
