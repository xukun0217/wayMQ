package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IMember;
import ananas.waymq.core.ISession;

public class JoinEvent extends WayMQBody implements IJoinEvent {

	 
	public JoinEvent(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IEvent getEvent() {
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

}