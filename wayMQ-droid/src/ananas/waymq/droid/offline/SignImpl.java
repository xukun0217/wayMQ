package ananas.waymq.droid.offline;

import com.alibaba.fastjson.JSONObject;

public class SignImpl implements ISign {

	private String _id;
	private String _name;
	private int _count;
	private int _money;
	private long _time;

	public void load(JSONObject json) {
		if (json == null)
			return;
		this._id = json.getString(Key.phone_id);
		this._name = json.getString(Key.nickname);
		this._money = json.getIntValue(Key.amount);
		this._count = json.getIntValue(Key.weight);
		this._time = json.getLongValue(Key.timestamp);
	}

	@Override
	public String id() {
		return this._id;
	}

	@Override
	public String name() {
		return this._name;
	}

	@Override
	public int count() {
		return this._count;
	}

	@Override
	public int money() {
		return this._money;
	}

	@Override
	public long timestamp() {
		return this._time;
	}

}
