package ananas.waymq.api;

public interface IHoldEvent {

	IGroup getGroup();

	IEvent getEvent();

	long getTime();
}
