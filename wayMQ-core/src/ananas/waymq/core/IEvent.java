package ananas.waymq.core;

public interface IEvent {

	long getTimeCreate();

	long getTimeLock();

	long getTimeOpen();

	long getTimeClose();

	EventId getId();

	String getDescription();

	String getTitle();

}
