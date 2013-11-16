package ananas.waymq.core;

/**
 * this is the 'read-only' snapshot
 * */

public interface ISnapshot {

	IMember[] listMembers();

	IJoinship[] listJoinship(IEvent event);

	IEvent[] listEvents();

	IMember getMember(MemberId id);

	IEvent getEvent(EventId id);

}
