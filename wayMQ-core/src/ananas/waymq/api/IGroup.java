package ananas.waymq.api;

public interface IGroup extends IElement {

	IJoinGroup[] listMembers();

	IHoldEvent[] listEvents();

	IUser getCreator();

	String getName();

	// setter

	void setName(String name);

}
