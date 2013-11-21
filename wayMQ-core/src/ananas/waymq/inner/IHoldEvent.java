package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IHoldEvent extends ISessionElement {

	IGroup getGroup();

	IEvent getEvent();

	long getTime();
}
