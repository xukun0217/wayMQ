package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.core.ISession;

public class JoinEventList extends WayMQBody implements IJoinEventList {

	 

	public JoinEventList(ISession session, IObject obj) {
		super(session, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IJoinEvent[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

}
