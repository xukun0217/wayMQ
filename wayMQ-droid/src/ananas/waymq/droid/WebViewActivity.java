package ananas.waymq.droid;

import ananas.waymq.droid.gap.Event;
import ananas.waymq.droid.gap.EventListener;
import ananas.waymq.droid.gap.GapConst;
import ananas.waymq.droid.gap.GapCore;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class WebViewActivity extends Activity implements EventListener {

	private ProgressBar _progress;
	private ImageButton _button_home;
	private WebView _web_view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_webview);

		this._progress = (ProgressBar) this.findViewById(R.id.progress_bar);
		this._button_home = (ImageButton) this.findViewById(R.id.button_home);
		this._web_view = (WebView) this.findViewById(R.id.web_view);

		// progress
		this._progress.setVisibility(View.INVISIBLE);
		// home button
		this._button_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				WebViewActivity.this.onClickHome();
			}
		});
		// web view
		this._web_view.setWebViewClient(new MyWebViewClient());
		this._web_view.setWebChromeClient(new MyWebChromeClient());
		this._web_view.getSettings().setJavaScriptEnabled(true);

		// service
		{
			String uri = "conf://123/456/";
			Intent service = new Intent(this, WebViewService.class);
			service.setData(Uri.parse(uri));
			this.startService(service);
		}

		// reset config url
		{
			GapCore gc = new GapCore(this);
			SharedPreferences sp = gc.getSharedPreferences();
			Editor edit = sp.edit();
			edit.remove(GapConst.Key.config_json);
			edit.commit();
		}
		this.onClickHome();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.web_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int id = item.getItemId();
		if (id == R.id.action_refresh) {
			String url = this._web_view.getUrl();
			this._web_view.loadUrl(url);
		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	protected void onClickHome() {
		MyLoadHomeTask task = new MyLoadHomeTask();
		task.start();
	}

	class MyWebViewClient extends WebViewClient {

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			WebViewActivity.this._progress.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			WebViewActivity.this._progress.setVisibility(View.VISIBLE);
		}

	}

	class MyWebChromeClient extends WebChromeClient {
	}

	class MyLoadHomeTask implements Runnable {

		public void run() {

			final WebViewActivity pthis = WebViewActivity.this;

			GapCore ldr = new GapCore(pthis);
			{
				ConfigEvent event = new ConfigEvent();
				event.loader = ldr;
				event.isBegin = true;
				Event.dispatch(pthis, event, pthis);
			}
			ldr.loadConfig();
			{
				ConfigEvent event = new ConfigEvent();
				event.loader = ldr;
				event.isEnd = true;
				Event.dispatch(pthis, event, pthis);
			}
		}

		public void start() {
			(new Thread(this)).start();
		}

	}

	class ConfigEvent extends Event {
		public GapCore loader;
		public boolean isBegin;
		public boolean isEnd;
	}

	public void onEvent(Event event) {

		if (event instanceof ConfigEvent) {
			ConfigEvent e2 = (ConfigEvent) event;
			this.onConfigEvent(e2);
		}

	}

	private void onConfigEvent(ConfigEvent event) {

		this._progress.setVisibility(event.isEnd ? View.INVISIBLE
				: View.VISIBLE);

		if (event.isEnd) {

			GapCore ldr = event.loader;
			if (ldr.getError() == null) {
				String url = ldr.getHomePage();
				this._web_view.loadUrl(url);
			} else {
			}

		}

	}

}
