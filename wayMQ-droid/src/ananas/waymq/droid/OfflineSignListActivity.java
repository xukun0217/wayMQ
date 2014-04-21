package ananas.waymq.droid;

import ananas.waymq.droid.offline.IOfflineCore;
import ananas.waymq.droid.offline.ISign;
import ananas.waymq.droid.offline.OfflineCore;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OfflineSignListActivity extends Activity {

	private IOfflineCore _core;
	private ListView _list_sign;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline_sign_list);

		this._core = OfflineCore.newInstance(this);

		// ui
		this._list_sign = (ListView) this.findViewById(R.id.list_sign);

		// others

	}

	@Override
	protected void onResume() {
		super.onResume();
		this.refresh();
	}

	private void refresh() {
		ISign[] array = this._core.listSigns();
		MyItem[] array2 = this.convert(array);
		ArrayAdapter<MyItem> adapter = new ArrayAdapter<MyItem>(this,
				android.R.layout.simple_list_item_1, array2);
		this._list_sign.setAdapter(adapter);
	}

	private MyItem[] convert(ISign[] array) {
		if (array == null)
			return new MyItem[0];
		MyItem[] a2 = new MyItem[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
			a2[i] = new MyItem(array[i]);
		}
		return a2;
	}

	class MyItem {

		private ISign _tar;
		private String _str;

		public MyItem(ISign tar) {
			this._tar = tar;
		}

		public String toString() {
			if (this._str == null) {
				this._str = this.makeString();
			}
			return this._str;
		}

		private String makeString() {

			String sp = " | ";

			StringBuilder sb = new StringBuilder();

			sb.append(_tar.id());
			sb.append(sp);
			sb.append(_tar.count() + " ren");
			sb.append(sp);
			sb.append(_tar.money() + " rmb");
			sb.append(sp);
			sb.append(_tar.name());

			return sb.toString();
		}
	}
}
