package ananas.waymq.api;

public interface IMember extends IElement {

	// getter

	IJoinGroup[] listGroups();

	IJoinEvent[] listEvents();

	IJoinEventList getJoinEventList();

	IJoinGroupList getJoinGroupList();

	IMemberPhone getPhone();

	// operate
	IGroup createGroup(String name);

	IEvent createEvent(String name, IGroup group);

	void join(IEvent event);

	void join(IGroup group);

}
