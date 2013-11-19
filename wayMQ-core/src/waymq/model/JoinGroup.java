package waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IMember;
import ananas.waymq.core.WayMQBody;

public class JoinGroup extends WayMQBody implements IJoinGroup {

	@Override
	public IGroup getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember getMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

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
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

}
