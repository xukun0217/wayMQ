package ananas.waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.objectbox.IObject;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IMember;
import ananas.waymq.core.ISession;

public class Event extends WayMQBody implements IEvent {

	 

	public Event(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IJoinEvent[] listJoins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGroup getCreatorGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember getCreator() {
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
