package ananas.waymq.droid;

import ananas.waymq.droid.offline.IOfflineCore;
import ananas.waymq.droid.offline.OfflineCore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OfflineMemberAddActivity extends Activity {

	public interface Param {

		// key

		String id_ = "id";
		String name_ = "name";

		// value

	}

	private IOfflineCore _core;

	private Button _btn_add;
	private EditText _edit_name;
	private TextView _text_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_member_add);
		this._core = OfflineCore.newInstance(this);
		// ui
		this._text_title = (TextView) this.findViewById(R.id.text_id);
		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._edit_name = (EditText) this.findViewById(R.id.edit_name);
		// event handler
		this._btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				OfflineMemberAddActivity.this.onClickButtonAdd();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent act = this.getIntent();
		Bundle ext = act.getExtras();
		if (ext == null)
			return;
		String id = ext.getString(Param.id_);
		id = this._core.normalizeId(id);
		this._text_title.setText(id);
	}

	protected void onClickButtonAdd() {

		String name = this._edit_name.getText().toString().trim();
		String id = this._text_title.getText().toString();

		String res = this._core.addMember(id, name);
		if (res == null) {
			res = "failed";
		} else {
			res = "success";
		}
		AlertDialog dlg = (new AlertDialog.Builder(this))
				.setMessage("name:" + name + "\nid:" + id).setTitle(res)
				.setNegativeButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						OfflineMemberAddActivity.this.finish();
					}
				}).create();
		dlg.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
