package ananas.waymq.api;

public interface IHoldEvent extends IElement {

	// getter

	IGroup getGroup();

	IEvent getEvent();

	long getTime();

}
