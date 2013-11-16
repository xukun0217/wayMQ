package ananas.waymq.core.logs;

import ananas.waymq.core.IEvent;
import ananas.waymq.core.IMember;
import ananas.waymq.core.Log;
import ananas.waymq.core.LogMaker;
import ananas.waymq.core.Money;
import ananas.waymq.core.Point;

public class DefaultLogFactory implements LogMaker {

	@Override
	public Log makeSnapshot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log addMember(IMember newMember) {
		AddMemberLog log = new AddMemberLog();
		log.set(newMember);
		return log;
	}

	@Override
	public Log addEvent(IEvent newEvent) {
		AddEventLog log = new AddEventLog();
		log.setEvent(newEvent);
		return log;
	}

	@Override
	public Log closeEvent(IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log join(IMember member, IEvent event) {
		JoinLog log = new JoinLog();
		log.setEvent(event);
		log.setMember(member);
		return log;
	}

	@Override
	public Log cancel(IMember member, IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log charge(IMember member, Money money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log charge(IMember member, Point point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log pay(IMember member, Money money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log pay(IMember member, Point point) {
		// TODO Auto-generated method stub
		return null;
	}

}
