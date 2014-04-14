package ananas.waymq.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		// startActivity(new Intent(this, DebugActivity.class));
		startActivity(new Intent(this, WebViewActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final int id = item.getItemId();
		if (id == R.id.action_debug) {

			startActivity(new Intent(this, DebugActivity.class));

		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;

	}

}
