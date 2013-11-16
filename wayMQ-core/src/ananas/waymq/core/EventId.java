package ananas.waymq.core;

public class EventId extends ObjectId {

	public EventId(String id) {
		super(id);
	}

	public static EventId newInstance(String aString2Hash) {
		String s = ObjectId.sha1ForString(aString2Hash);
		return new EventId(s);
	}

}
