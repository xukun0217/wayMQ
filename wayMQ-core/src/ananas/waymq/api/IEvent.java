package ananas.waymq.api;

public interface IEvent {

	IJoinEvent[] listMembers();

	IGroup getCreatorGroup();

	IMember getCreator();

}
