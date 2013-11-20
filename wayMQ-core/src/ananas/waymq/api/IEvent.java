package ananas.waymq.api;

import ananas.waymq.core.ISessionElement;

public interface IEvent extends ISessionElement {

	IJoinEvent[] listJoins();

	IGroup getCreatorGroup();

	IMember getCreator();

}
