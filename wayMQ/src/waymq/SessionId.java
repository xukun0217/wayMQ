package waymq;

public class SessionId extends ObjId {

	public SessionId(String s) {
		super(s);
	}

	public static SessionId idForHashString(String s) {
		s = __hashString(s);
		return new SessionId(s);
	}

}
