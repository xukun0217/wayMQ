package waymq.model;

import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.WayMQBody;

public class Member extends WayMQBody implements IMember {

	@Override
	public IJoinGroup[] listGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinEvent[] listEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IGroup createGroup(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMemberPhone getPhone() {
		// TODO Auto-generated method stub
		return null;
	}

}
