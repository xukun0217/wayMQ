package waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IHoldEventList;
import ananas.waymq.core.WayMQBody;

public class HoldEventList extends WayMQBody implements IHoldEventList {

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
