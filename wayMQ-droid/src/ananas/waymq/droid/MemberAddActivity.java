package ananas.waymq.droid;

import ananas.waymq.droid.core.CoreAgent;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MemberAddActivity extends Activity {

	private ListView _list;
	private CoreAgent _ca;
	private Button _btn_add;
	private EditText _edit_name;
	private EditText _edit_phone;
	private EditText _edit_keywd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_add);
		this._ca = new CoreAgent(this);

		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._edit_phone = (EditText) this.findViewById(R.id.edit_phone);
		this._edit_name = (EditText) this.findViewById(R.id.edit_name);
		this._edit_keywd = (EditText) this.findViewById(R.id.edit_key_word);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
