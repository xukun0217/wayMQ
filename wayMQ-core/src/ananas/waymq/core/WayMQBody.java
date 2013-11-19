package ananas.waymq.core;

import ananas.objectbox.ctrl.json.AbsJsonCtrl;

import com.alibaba.fastjson.JSONObject;

public abstract class WayMQBody extends AbsJsonCtrl {

	@Override
	public void onLoad(JSONObject root) {
		// TODO Auto-generated method stub

	}

	@Override
	public JSONObject onSave(JSONObject root) {
		// TODO Auto-generated method stub
		return root;
	}

}
