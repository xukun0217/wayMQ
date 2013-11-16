package ananas.waymq.core.impl;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ananas.waymq.core.EventId;
import ananas.waymq.core.IEvent;
import ananas.waymq.core.IJoinship;
import ananas.waymq.core.IMember;
import ananas.waymq.core.IMutableSnapshot;
import ananas.waymq.core.Log;
import ananas.waymq.core.MemberId;
import ananas.waymq.core.Money;
import ananas.waymq.core.Point;
import ananas.waymq.core.logs.AddEventLog;

public class DefaultMutableSnap implements IMutableSnapshot {

	final Map<MemberId, IMember> _map_member;
	final Map<EventId, IEvent> _map_event;
	final List<Log> _list_log;

	public DefaultMutableSnap() {
		this._map_event = new Hashtable<EventId, IEvent>();
		this._map_member = new Hashtable<MemberId, IMember>();
		this._list_log = new Vector<Log>();
	}

	@Override
	public IMember[] listMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJoinship[] listJoinship(IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvent[] listEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IMember getMember(MemberId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEvent getEvent(EventId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log makeSnapshot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log addMember(IMember newMember) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log addEvent(IEvent newEvent) {
	
		
		return new AddEventLog() ;
		
	}

	@Override
	public Log closeEvent(IEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log join(IMember member, IEvent event) {
		// TODO Auto-generated method stub
		return null;
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
