package ananas.waymq.droid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class WebViewActivity extends Activity {

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
		// go
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
		// TODO Auto-generated method stub
		this._web_view.loadUrl("http://www.163.com/");
		// this._web_view.lo

		 
		
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

}
