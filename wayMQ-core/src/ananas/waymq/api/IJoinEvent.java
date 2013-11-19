package ananas.waymq.api;

public interface IJoinEvent {

	IEvent getEvent();

	IMember getMember();

	long getTime();

}
