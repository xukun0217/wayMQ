package ananas.waymq.tools.counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordSet {

	final Map<Long, myAccount> _accounts;

	public RecordSet() {
		this._accounts = new HashMap<Long, myAccount>();
	}

	public void add(BasePaymentItem item) {
		myAccount account = this.getAccount(item.id);
		account.add(item);
	}

	private myAccount getAccount(long id) {
		myAccount item = this._accounts.get(id);
		if (item == null) {
			item = new myAccount(id);
			this._accounts.put(item._id, item);
		}
		return item;
	}

	class myAccount {

		private final Long _id;
		private String _name;
		private final List<BasePaymentItem> _records;

		public myAccount(Long id) {
			this._id = id;
			this._records = new ArrayList<BasePaymentItem>();
		}

		public List<BasePaymentItem> listBasePaymentItems() {
			return this._records;
		}

		public Long getId() {
			return _id;
		}

		public String getName() {
			return _name;
		}

		public void add(BasePaymentItem item) {
			// if (_name == null)
			_name = item.nickname;
			_records.add(item);
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (BasePaymentItem rec : this._records) {
				sb.append(rec);
				sb.append("\n");
			}
			return sb.toString();
		}

	}

	public String toString() {
		String bar = "-----------------------------------------------";
		StringBuilder sb = new StringBuilder();
		List<Long> keys = new ArrayList<Long>(this._accounts.keySet());
		Collections.sort(keys);
		for (Long key : keys) {
			myAccount acc = this._accounts.get(key);
			sb.append(bar);
			sb.append("\n");
			sb.append(acc);
			sb.append("\n");
		}
		sb.append(bar);
		sb.append("\n");
		return sb.toString();
	}

}
