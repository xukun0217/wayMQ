package ananas.waymq.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpJsonRequestTester {

	private final String _base_url = "http://localhost:18080/wayMQ-server/Endpoint";

	public void test(String http_method, Map<String, String> param) {
		try {
			String url = this.buildURL(param);
			System.out.println(http_method + "\t" + url);
			String str = this.loadString(http_method, url);
			JSONObject json = JSON.parseObject(str);
			str = JSON.toJSONString(json, true);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String loadString(String http_method, String url)
			throws IOException {
		URLConnection conn = (new URL(url)).openConnection();
		HttpURLConnection htconn = (HttpURLConnection) conn;
		htconn.setRequestMethod(http_method);

		int code = htconn.getResponseCode();
		if (code != 200) {
			String msg = htconn.getResponseMessage();
			throw new RuntimeException("http " + code + " " + msg);
		}
		String type = htconn.getContentType();
		if (!this.acceptType(type)) {
			throw new RuntimeException("unsupported Content-Type: " + type);
		}
		InputStream in = htconn.getInputStream();
		Reader rdr = new InputStreamReader(in, "UTF-8");
		StringBuilder sb = new StringBuilder();
		char[] buf = new char[256];
		for (;;) {
			int cc = rdr.read(buf);
			if (cc < 0)
				break;
			sb.append(buf, 0, cc);
		}
		rdr.close();
		in.close();
		htconn.disconnect();
		return sb.toString();
	}

	private boolean acceptType(String type) {

		if (type == null)
			return false;

		if (type.equals("text/json"))
			return true;
		if (type.equals("application/json"))
			return true;
		if (type.equals("application/javascript"))
			return true;

		return false;
	}

	private String buildURL(Map<String, String> param)
			throws UnsupportedEncodingException {
		List<String> keys = new ArrayList<String>(param.keySet());
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			String val = param.get(key);
			key = URLEncoder.encode(key, "UTF-8");
			val = URLEncoder.encode(val, "UTF-8");
			sb.append((sb.length() > 0) ? '&' : '?');
			sb.append(key);
			sb.append('=');
			sb.append(val);
		}
		String base = this._base_url;
		return (base + sb.toString());
	}
}
