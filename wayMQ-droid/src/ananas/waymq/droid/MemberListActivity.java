package ananas.waymq.droid;

import ananas.waymq.droid.api.ICoreApi;
import ananas.waymq.droid.core.CoreAgent;
import ananas.waymq.droid.core.IMember;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MemberListActivity extends Activity {

	private CoreAgent _ca;

	private Button _btn_add;
	private ListView _list_members;
	private EditText _edit_search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_list);
		this._ca = new CoreAgent(this);

		this._list_members = (ListView) this.findViewById(R.id.list_members);
		this._btn_add = (Button) this.findViewById(R.id.btn_add);
		this._edit_search = (EditText) this.findViewById(R.id.edit_search);

		ListAdapter adapter = this.__find_members(null);
		this._list_members.setAdapter(adapter);
	}

	private ListAdapter __find_members(String keyword) {

		ICoreApi core = this._ca.getCore();
		IMember[] rlt = core.getMembers();
		MyItem[] array = new MyItem[rlt.length];
		for (int i = rlt.length - 1; i >= 0; i--) {
			array[i] = new MyItem(rlt[i]);
		}
		int res = android.R.layout.simple_list_item_1;
		return new android.widget.ArrayAdapter<MyItem>(this, res, array);
	}

	class MyItem {

		private final IMember _mem;

		public MyItem(IMember mem) {
			this._mem = mem;
		}

		public String toString() {
			return this._mem.getName();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
