package ananas.waymq.api;

public interface IEvent extends IElement {

	IJoinEvent[] listJoins();

	IGroup getCreatorGroup();

	IMember getCreator();

	String getName();

	// setter

	void setName(String name);
}
