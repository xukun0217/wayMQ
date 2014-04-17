package ananas.waymq.droid;

import ananas.waymq.droid.offline.IMemberInfo;
import ananas.waymq.droid.offline.IOfflineCore;
import ananas.waymq.droid.offline.OfflineCore;
import android.app.Activity;
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
import android.widget.ListAdapter;
import android.widget.ListView;

public class OfflineMemberListActivity extends Activity implements TextWatcher,
		OnItemClickListener {

	private Button _btn_add;
	private ListView _list_members;
	private EditText _edit_search;
	private IOfflineCore _core;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_member_list);

		// ui components
		this._list_members = (ListView) this.findViewById(R.id.list_members);
		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._edit_search = (EditText) this.findViewById(R.id.edit_search);
		// event handler
		if (this._btn_add != null)
			this._btn_add.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					OfflineMemberListActivity.this.onClickButtonAdd();
				}
			});
		this._edit_search.addTextChangedListener(this);
		this._list_members.setOnItemClickListener(this);
		// init
		this._core = OfflineCore.newInstance(this);

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

		this._edit_search.setText("");
		this.__find_members(null);

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

}
