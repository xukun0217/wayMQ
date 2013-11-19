package waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.core.WayMQBody;

public class JoinGroupList extends WayMQBody implements IJoinGroupList {

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
	public IJoinGroup[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(IGroup group, IMember member) {
		// TODO Auto-generated method stub
		
	}

}
