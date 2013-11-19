package ananas.waymq.api;

public interface IMember {

	IJoinGroup[] listGroups();

	IJoinEvent[] listEvents();

	IGroup createGroup(String name);

	IMemberPhone getPhone();

}
