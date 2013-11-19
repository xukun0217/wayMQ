package waymq.model;

import java.util.HashMap;
import java.util.Map;

import ananas.objectbox.IBox;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.core.WayMQBody;

import com.alibaba.fastjson.JSONObject;

public class Group extends WayMQBody implements IGroup {

	@Override
	public IJoinGroup[] listMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IHoldEvent[] listEvents() {
		// TODO Auto-generated method stub
		return null;
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

	public static IGroup create(IBox box, Member member) {

		Map<String, String> attr = new HashMap<String, String>();
	//	attr.put( Key  , value)
		return (Group) box.newObject(Group.class, attr);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

}
