package ananas.waymq.api;

public interface IJoinEvent extends IElement {

	// getter
	IEvent getEvent();

	IMember getMember();

	long getTime();

}
