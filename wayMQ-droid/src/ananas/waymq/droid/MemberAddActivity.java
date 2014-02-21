package ananas.waymq.droid;

import ananas.waymq.droid.api.CoreAgent;
import ananas.waymq.droid.api.DefaultMember;
import ananas.waymq.droid.api.IMember;
import ananas.waymq.droid.api.IMemberManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MemberAddActivity extends Activity {

	private ListView _list;
	private CoreAgent _ctrl;
	private Button _btn_add;
	private EditText _edit_name;
	private EditText _edit_phone;
	private EditText _edit_keywd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_add);
		this._ctrl = new CoreAgent(this);
		this._ctrl.onCreate(savedInstanceState);
		// ui
		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._edit_phone = (EditText) this.findViewById(R.id.edit_phone);
		this._edit_name = (EditText) this.findViewById(R.id.edit_name);
		this._edit_keywd = (EditText) this.findViewById(R.id.edit_key_word);
		// event handler
		this._btn_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MemberAddActivity.this.onClickButtonAdd();
			}
		});
	}

	protected void onClickButtonAdd() {

		String phone = this._edit_phone.getText().toString().trim();
		String name = this._edit_name.getText().toString().trim();
		String kw = this._edit_keywd.getText().toString().trim();

		// String[] kws = kw.split( "[,; ]" ) ;

		IMember member = new DefaultMember(name, phone, kw);
		IMemberManager mm = this._ctrl.getCore().getMemberManager();
		mm.addMember(member);

		this._ctrl.getCore().save();
		this.setResult(Activity.RESULT_OK);
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
