package ananas.waymq.droid;

import ananas.waymq.droid.offline.IMemberInfo;
import ananas.waymq.droid.offline.IOfflineCore;
import ananas.waymq.droid.offline.OfflineCore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class OfflineMemberListActivity extends Activity implements TextWatcher,
		OnItemClickListener {

	private Button _btn_add;
	private ListView _list_members;
	private EditText _edit_search;
	private IOfflineCore _core;
	private Button _btn_clr_edit;
	private ImageButton _btn_sign_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_member_list);

		// ui components
		this._list_members = (ListView) this.findViewById(R.id.list_members);
		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._btn_clr_edit = (Button) this.findViewById(R.id.button_clear_edit);
		this._btn_sign_list = (ImageButton) this.findViewById(R.id.button_back);
		this._edit_search = (EditText) this.findViewById(R.id.edit_search);
		// event handler
		if (this._btn_add != null)
			this._btn_add.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					OfflineMemberListActivity.this.onClickButtonAdd();
				}
			});
		if (this._btn_clr_edit != null) {
			this._btn_clr_edit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					OfflineMemberListActivity.this.onClickButtonClearEdit();
				}
			});
		}
		if (this._btn_sign_list != null) {
			this._btn_sign_list.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					OfflineMemberListActivity.this.onClickButtonSignList();
				}
			});
		}
		this._edit_search.addTextChangedListener(this);
		this._list_members.setOnItemClickListener(this);
		// init
		this._core = OfflineCore.newInstance(this);

	}

	protected void onClickButtonSignList() {
		Intent intent = new Intent(this, OfflineSignListActivity.class);
		this.startActivity(intent);
	}

	protected void onClickButtonClearEdit() {

		this.__find_members(null);
		this._edit_search.setText("");
		this._edit_search.forceLayout();

	}

	protected void onClickButtonAdd() {
		Intent intent = new Intent(this, OfflineMemberAddActivity.class);
		this.startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void __find_members(String keyword) {
		IMemberInfo[] list = this._core.findMembers(keyword);
		if (list == null)
			return;
		if (list.length == 0) {
			// add button
			this.onFindMembers(keyword, list);
		} else {
			// show items
			this.onFindMembers(keyword, list);
		}
	}

	class MyItem {

		private final IMemberInfo _mem;

		public MyItem() {
			this._mem = null;
		}

		public MyItem(IMemberInfo mem) {
			this._mem = mem;
		}

		public String toString() {

			if (_mem == null) {
				return "add new";
			}

			String phone = _mem.getId();
			String name = _mem.getName();
			return phone + " " + name;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void onFindMembers(String keyword, IMemberInfo[] rlt) {
		MyItem[] array = new MyItem[rlt.length + 1];
		for (int i = 0; i < array.length; i++) {
			if (i < rlt.length)
				array[i] = new MyItem(rlt[i]);
			else
				array[i] = new MyItem();
		}
		ListAdapter adapter = new ArrayAdapter<MyItem>(this,
				android.R.layout.simple_list_item_1, array);
		this._list_members.setAdapter(adapter);
		this._list_members.refreshDrawableState();
	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onResume() {
		super.onResume();

		String id = this._edit_search.getText().toString();
		this.__find_members(id);

	}

	@Override
	public void afterTextChanged(Editable edit) {
		String s = edit.toString();
		this.__find_members(s);
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Object item = parent.getItemAtPosition(position);
		if (item instanceof MyItem) {
			MyItem myit = (MyItem) item;
			System.out.println("click : " + myit);

			IMemberInfo member = myit._mem;

			if (member == null) {
				// add new
				String mid = this._edit_search.getText().toString();
				Intent act = new Intent(this, OfflineMemberAddActivity.class);
				act.putExtra(OfflineMemberAddActivity.Param.id_, mid);
				// act.putExtra(OfflineMemberAddActivity.Param.name_, null);
				this.startActivity(act);
			} else {
				// open exists
				Intent act = new Intent(this, OfflineSignActivity.class);
				act.putExtra(OfflineSignActivity.Param.do_,
						OfflineSignActivity.Param.do_update);
				act.putExtra(OfflineSignActivity.Param.id_, member.getId());
				this.startActivity(act);
			}

		}

	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();

		DialogInterface.OnClickListener hYes = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		};

		DialogInterface.OnClickListener hNo = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// do nothing
			}
		};

		AlertDialog dlg = (new AlertDialog.Builder(this))
				.setNegativeButton("No", hNo).setPositiveButton("Yes", hYes)
				.setMessage("Are you sure to exit now ?").create();
		dlg.show();
	}

}
