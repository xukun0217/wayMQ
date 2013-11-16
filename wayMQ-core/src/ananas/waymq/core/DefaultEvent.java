package ananas.waymq.core;

public class DefaultEvent implements IEvent {

	private final EventId _id;

	private long _time_create;
	private long _time_lock;
	private long _time_open;
	private long _time_close;

	public DefaultEvent() {
		long time = System.currentTimeMillis();
		this._id = __gen_id(time);
	}

	private static EventId __gen_id(long time) {
		String s = "event@" + time;
		return EventId.newInstance(s);
	}

	public DefaultEvent(EventId id) {
		this._id = id;
	}

	@Override
	public long getTimeCreate() {
		return this._time_create;
	}

	@Override
	public long getTimeLock() {
		return this._time_lock;
	}

	@Override
	public long getTimeOpen() {
		return this._time_open;
	}

	@Override
	public long getTimeClose() {
		return this._time_close;
	}

	@Override
	public EventId getId() {
		return this._id;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
