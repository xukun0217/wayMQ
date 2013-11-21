package ananas.waymq.api;

public interface IEvent extends IElement {

	IJoinEvent[] listJoins();

	IJoinEventList getJoinEventList();

	IGroup getCreatorGroup();

	IMember getCreator();

	String getName();

	// setter

	void setName(String name);
}
