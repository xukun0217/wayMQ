package ananas.waymq.api;

public interface IGroup {

	interface Key {

	}

	IJoinGroup[] listMembers();

	IHoldEvent[] listEvents();

}
