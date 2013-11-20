package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.core.ISession;

public class HoldEvent extends WayMQBody implements IHoldEvent {

	 

	public HoldEvent(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IGroup getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvent getEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
