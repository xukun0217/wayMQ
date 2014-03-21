package ananas.waymq.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DebugActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug);

		// this.startActivity(new Intent(this, MemberListActivity.class));
		this.startActivity(new Intent(this, SmsLoginActivity.class));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.debug, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final int id = item.getItemId();
		if (id == R.id.action_sign) {

			this.startActivity(new Intent(this, SignActivity.class));

		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;

	}

}
