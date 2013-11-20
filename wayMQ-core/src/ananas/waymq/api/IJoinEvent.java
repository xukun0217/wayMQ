package ananas.waymq.api;

import ananas.waymq.core.ISessionElement;

public interface IJoinEvent extends ISessionElement {

	IEvent getEvent();

	IMember getMember();

	long getTime();

}
