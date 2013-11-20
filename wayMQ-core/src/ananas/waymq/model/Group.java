package ananas.waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.objectbox.IObject;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.core.ISession;

public class Group extends WayMQBody implements IGroup {

	 

	public Group(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

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
	public void onLoad(JSONObject json) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSave(JSONObject json) {
		// TODO Auto-generated method stub
		
	}

}
