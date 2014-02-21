package ananas.waymq.droid;

import java.util.List;

import ananas.waymq.droid.api.CoreAgent;
import ananas.waymq.droid.api.FindMembersHandler;
import ananas.waymq.droid.api.IMember;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MemberListActivity extends Activity implements FindMembersHandler,
		TextWatcher {

	private CoreAgent _ctrl;

	private Button _btn_add;
	private ListView _list_members;
	private EditText _edit_search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_list);
		this._ctrl = new CoreAgent(this);
		this._ctrl.onCreate(savedInstanceState);
		// ui components
		this._list_members = (ListView) this.findViewById(R.id.list_members);
		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._edit_search = (EditText) this.findViewById(R.id.edit_search);
		// event handler
		this._btn_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				MemberListActivity.this.onClickButtonAdd();
			}
		});
		this._edit_search.addTextChangedListener(this);
		// init
		this._ctrl.getCore().load();
	}

	protected void onClickButtonAdd() {
		Intent intent = new Intent(this, MemberAddActivity.class);
		this.startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void __find_members(String keyword) {
		FindMembersHandler callback = this;
		this._ctrl.findMembers(keyword, callback);
	}

	class MyItem {

		private final IMember _mem;

		public MyItem(IMember mem) {
			this._mem = mem;
		}

		public String toString() {
			String phone = _mem.getPhone();
			String name = _mem.getName();
			return phone + " " + name;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onFindMembers(String keyword, List<IMember> rlt) {
		MyItem[] array = new MyItem[rlt.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = new MyItem(rlt.get(i));
		}
		ListAdapter adapter = new ArrayAdapter<MyItem>(this,
				android.R.layout.simple_list_item_1, array);
		this._list_members.setAdapter(adapter);
		this._list_members.refreshDrawableState();
	}

	@Override
	protected void onPause() {
		super.onPause();
		this._ctrl.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		this._ctrl.onResume();

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

}
