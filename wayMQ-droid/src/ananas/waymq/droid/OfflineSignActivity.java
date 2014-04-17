package ananas.waymq.droid;

import ananas.waymq.droid.offline.IMemberInfo;
import ananas.waymq.droid.offline.IOfflineCore;
import ananas.waymq.droid.offline.OfflineCore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class OfflineSignActivity extends Activity {

	public interface Param {

		// key

		String do_ = "do";
		String id_ = "id";

		// value

		String do_insert = "do_insert";
		String do_update = "do_update";

	}

	static class DataModel {

		public String id;
		public String name;
		public int count;
		public int money;
	}

	private TextView _wnd_phone;
	private Button _wnd_name;
	private Button _wnd_sign;
	private NumberPicker _wnd_num_head;
	private NumberPicker _wnd_num_money10;
	private NumberPicker _wnd_num_money1;
	private IOfflineCore _core;

	private final DataModel _dm = new DataModel();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_sign);

		this._core = OfflineCore.newInstance(this);

		// ui

		this._wnd_phone = (TextView) this.findViewById(R.id.text_phone_number);
		this._wnd_name = (Button) this.findViewById(R.id.button_select_member);
		this._wnd_sign = (Button) this.findViewById(R.id.button_sign);
		this._wnd_num_head = (NumberPicker) this
				.findViewById(R.id.numberPicker_head_x1);
		this._wnd_num_money10 = (NumberPicker) this
				.findViewById(R.id.numberPicker_money_x10);
		this._wnd_num_money1 = (NumberPicker) this
				.findViewById(R.id.numberPicker_money_x1);

		this.setupNumPicker(this._wnd_num_head);
		this.setupNumPicker(this._wnd_num_money1);
		this.setupNumPicker(this._wnd_num_money10);

		this._wnd_sign.setOnClickListener(new MySignButtonListener());

		// others

		Intent act = this.getIntent();
		Bundle ext = act.getExtras();
		if (ext != null) {
			String id = ext.getString(Param.id_);
			String do_ = ext.getString(Param.do_);
			System.out.println("do:" + do_ + " id:" + id);
		}

	}

	private void setupNumPicker(NumberPicker tp) {
		tp.setMinValue(0);
		tp.setMaxValue(9);
	}

	class MySignButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			OfflineSignActivity.this.__on_click_button_sign();
		}
	}

	void __on_click_button_sign() {

		int head = this._wnd_num_head.getValue();
		int money = this._wnd_num_money1.getValue()
				+ (this._wnd_num_money10.getValue() * 10);
		String id = _dm.id;
		String name = _dm.name;

		StringBuilder sb = new StringBuilder();
		sb.append("\nid   : " + id);
		sb.append("\nname : " + name);
		sb.append("\ncount: " + head);
		sb.append("\nmoney: " + money);

		_dm.count = head;
		_dm.money = money;

		DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				OfflineSignActivity.this.__do_sign();
			}
		};
		DialogInterface.OnClickListener btnCancel = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do nothing
			}
		};

		AlertDialog dlg = (new AlertDialog.Builder(this))

		.setNegativeButton("OK", btnOk).setPositiveButton("Cancel", btnCancel)

		.setTitle("Sign").setMessage(sb).create();
		dlg.show();
	}

	protected void __do_sign() {

		_core.doMemberSign(_dm.id, _dm.name, _dm.count, _dm.money);

		this.finish();

	}

	@Override
	protected void onStart() {

		super.onStart();

		String id = null;
		Bundle ext = this.getIntent().getExtras();
		if (ext != null) {
			id = ext.getString(Param.id_);
		}
		IMemberInfo info = _core.getMemberInfo(id);
		String name = null;
		if (info != null) {
			id = info.getId();
			name = info.getName();
		}

		this._wnd_phone.setText("*****" + id);
		this._wnd_name.setText(name);

		this._wnd_num_head.setValue(1);
		this._wnd_num_money10.setValue(1);
		this._wnd_num_money1.setValue(0);

		this._dm.id = id;
		this._dm.name = name;

	}
}
