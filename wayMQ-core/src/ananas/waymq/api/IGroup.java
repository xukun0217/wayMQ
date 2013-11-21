package ananas.waymq.api;

public interface IGroup extends IElement {

	IJoinGroup[] listMembers();

	IHoldEvent[] listEvents();

	IJoinGroupList getJoinGroupList();

	IHoldEventList getHoldEventList();

	IMember getCreator();

	String getName();

	// setter

	void setName(String name);

}
