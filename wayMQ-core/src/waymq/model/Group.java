package waymq.model;

import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.core.WayMQBody;

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

}
