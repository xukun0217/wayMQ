package ananas.waymq.core;

import ananas.objectbox.body.json.JsonBody;

import com.alibaba.fastjson.JSONObject;

public class ObjectBody implements JsonBody {

	@Override
	public void onLoad(JSONObject root) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject onSave(JSONObject root) {
		// TODO Auto-generated method stub
		return null;
	}

}
