package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.waymq.api.IHoldEventList;
import ananas.waymq.core.ISession;

public class HoldEventList extends WayMQBody implements IHoldEventList {

	public HoldEventList(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	 
}
