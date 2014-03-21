package ananas.waymq.droid;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import ananas.waymq.droid.helper.TokenManager;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SmsLoginActivity extends Activity {

	interface Config {

		String url = "http://192.168.1.217/test/waymq.conf.js";

	}

	private ProgressBar _progress;
	private TextView _info;
	private Button _login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_login);

		this._info = (TextView) this.findViewById(R.id.text_info);
		this._progress = (ProgressBar) this.findViewById(R.id.progress_bar);
		this._login = (Button) this.findViewById(R.id.button_login);

		this._progress.setVisibility(View.INVISIBLE);
		this._info.setText("Press 'Login'");

		__setup_button_listener();
	}

	void __setup_button_listener() {

		((Button) this.findViewById(R.id.button_login))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Thread thread = new Thread(new DoLogin());
						thread.start();
					}
				});

	}

	class DoLogin implements Runnable {

		private String _endpoint;
		private String _open_browser;

		@Override
		public void run() {

			SmsLoginActivity.this.runOnUiThread(new DoLoginBegin());

			try {

				this.output("hello, world");

				this.__load_json_config();

				long beginTime = System.currentTimeMillis();
				for (int timeinterval = 500;; timeinterval *= 2) {
					this.__login_at_endpoint();
					if (this._open_browser == null) {
						timeinterval = Math.min(timeinterval, 10000);
						Thread.sleep(timeinterval);
					} else {
						break;
					}
					long now = System.currentTimeMillis();
					if ((beginTime + 60000) < now) {
						break;
					}
				}
				String url = this._open_browser;
				if (url == null) {
					this.output("timeout");
				} else {
					this.output("success");
				}

			} catch (Exception e) {
				e.printStackTrace();
				this.output(e.getMessage());
			}

			SmsLoginActivity.this.runOnUiThread(new DoLoginEnd(
					this._open_browser));

		}

		private void __login_at_endpoint() throws IOException {

			String token = TokenManager.getToken();
			StringBuilder url = new StringBuilder();
			url.append(this._endpoint);
			url.append("?do=login&token=");
			url.append(token);
			JSONObject json = Helper.loadJSON(url.toString());

			String go_to = Helper.getString(json, "goto");
			token = Helper.getString(json, "token");
			TokenManager.saveToken(token);

			if (go_to == null) {
			} else if (go_to.startsWith("sms:")) {
				// "sms:"
				String content = Helper.getString(json, "content");
				String addr = go_to.substring(go_to.indexOf(':') + 1);
				SmsManager.getDefault().sendTextMessage(addr, null, content,
						null, null);
			} else if (go_to.startsWith("http")) {
				// "http:" or "https:"
				this._open_browser = go_to;
			}

		}

		private void __load_json_config() throws IOException {

			String url = Config.url;
			JSONObject json = Helper.loadJSON(url);
			String ep = Helper.getString(json, "json_request_endpoint");
			output("connect " + ep);
			this._endpoint = ep;

		}

		private void output(String string) {
			SmsLoginActivity.this.runOnUiThread(new DoOutputInfo(string));
		}
	}

	static class Helper {

		public static String getString(JSONObject json, String key) {
			String value = json.getString(key);
			if (value == null) {
				throw new RuntimeException("need for key: " + key);
			}
			return value;
		}

		public static JSONObject loadJSON(String url) throws IOException {

			InputStream in = (new URL(url)).openStream();
			Reader reader = new InputStreamReader(in, "UTF-8");
			char[] buf = new char[512];
			StringBuilder sb = new StringBuilder();
			for (;;) {
				int cc = reader.read(buf);
				if (cc < 0)
					break;
				sb.append(buf, 0, cc);
			}
			in.close();

			int i0 = sb.indexOf("{");
			int i1 = sb.lastIndexOf("}");
			String str = sb.substring(i0, i1 + 1);

			JSONObject json = JSON.parseObject(str);
			return json;
		}

	}

	class DoLoginBegin implements Runnable {

		@Override
		public void run() {

			SmsLoginActivity.this._progress.setVisibility(View.VISIBLE);
			SmsLoginActivity.this._info.setText("login ...");
			SmsLoginActivity.this._login.setEnabled(false);

		}
	}

	class DoLoginEnd implements Runnable {

		private final String _url;

		public DoLoginEnd(String openURL) {
			this._url = openURL;
		}

		@Override
		public void run() {

			SmsLoginActivity.this._progress.setVisibility(View.INVISIBLE);
			SmsLoginActivity.this._login.setEnabled(true);

			if (_url != null) {
				SmsLoginActivity pthis = SmsLoginActivity.this;
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_url));
				pthis.startActivity(intent);
			}

		}
	}

	class DoOutputInfo implements Runnable {

		private String _text;

		public DoOutputInfo(String string) {
			this._text = string;
		}

		@Override
		public void run() {
			SmsLoginActivity.this._info.setText(_text);
		}
	}

}
