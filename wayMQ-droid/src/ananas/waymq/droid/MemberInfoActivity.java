package ananas.waymq.droid;

import ananas.waymq.droid.api.CoreAgent;
import ananas.waymq.droid.api.IMember;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MemberInfoActivity extends Activity {

	private ListView _list;
	private CoreAgent _ca;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_member_info);

		this._ca = new CoreAgent(this);

		this._list = (ListView) this.findViewById(R.id.list_members);

		ListAdapter adapter = this.__find(null);
		this._list.setAdapter(adapter);
	}

	private ListAdapter __find(Object object) {
		// TODO Auto-generated method stub
		return null;
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
