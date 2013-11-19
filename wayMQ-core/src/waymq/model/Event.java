package waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IMember;
import ananas.waymq.core.WayMQBody;

public class Event extends WayMQBody implements IEvent {

	@Override
	public IJoinEvent[] listMembers() {
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


}
