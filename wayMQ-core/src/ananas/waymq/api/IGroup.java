package ananas.waymq.api;

import ananas.waymq.core.ISessionElement;

public interface IGroup extends ISessionElement {

	interface Key {

	}

	IJoinGroup[] listMembers();

	IHoldEvent[] listEvents();

}
