package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IGroup extends ISessionElement {

	interface Key {

	}

	IJoinGroup[] listMembers();

	IHoldEvent[] listEvents();

}
