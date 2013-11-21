package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IEvent extends ISessionElement {

	IJoinEvent[] listJoins();

	IGroup getCreatorGroup();

	IMember getCreator();

}
