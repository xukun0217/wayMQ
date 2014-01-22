package ananas.waymq.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignActivity extends Activity {

	class DataModel {

		;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign);

		__setup_button_listener();
	}

	void __on_click_button_sign() {

		DataModel dm = this.__getModel();

	}

	private DataModel __getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	private void __setModel(DataModel model) {
		// TODO Auto-generated method stub

	}

	void __on_click_button_sel_member() {
	}

	void __setup_button_listener() {

		((Button) this.findViewById(R.id.button_sign))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						__on_click_button_sign();
					}
				});

		((Button) this.findViewById(R.id.button_select_member))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						__on_click_button_sel_member();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final int id = item.getItemId();
		if (id == R.id.action_sel_member) {

			this.startActivity(new Intent(this, MemberListActivity.class));

		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;

	}

}
