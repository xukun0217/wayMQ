package waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.core.WayMQBody;

public class JoinEventList extends WayMQBody implements IJoinEventList {

	@Override
	public void onLoad(JSONObject root) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject onSave(JSONObject root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinEvent[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
