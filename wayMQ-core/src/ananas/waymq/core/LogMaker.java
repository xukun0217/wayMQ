package ananas.waymq.core;


public interface LogMaker {

	Log makeSnapshot();

	Log addMember(IMember newMember);

	Log addEvent(IEvent newEvent);

	Log closeEvent(IEvent event);

	Log join(IMember member, IEvent event);

	Log cancel(IMember member, IEvent event);

	Log charge(IMember member, Money money);

	Log charge(IMember member, Point point);

	Log pay(IMember member, Money money);

	Log pay(IMember member, Point point);

}
