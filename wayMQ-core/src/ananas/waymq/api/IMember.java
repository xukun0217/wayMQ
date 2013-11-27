package ananas.waymq.api;

public interface IMember extends IElement {

	// getter

	IJoinGroup getGroup();

	IPhone getPhone();

	IJoinEvent[] listEvents();

	void addEvent(IJoinEvent event);

	// operate
	IGroup createGroup(String name);

	IEvent createEvent(String name, IGroup group);

	void join(IEvent event);

	void join(IGroup group);

}
