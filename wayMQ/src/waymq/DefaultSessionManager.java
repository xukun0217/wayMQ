package waymq;

import java.util.Hashtable;
import java.util.Map;

public class DefaultSessionManager implements ISessionManager {

	final Map<SessionId, ISession> _map;
	private int _index;

	public DefaultSessionManager() {
		this._map = new Hashtable<SessionId, ISession>();
	}

	@Override
	public synchronized ISession newSession() {
		this._index += this.hashCode();
		String str = System.currentTimeMillis() + "&&" + this._index;
		SessionId sid = SessionId.idForHashString(str);
		ISession session = new DefaultSession(sid);
		session.bindUser(new DefaultTempUser());
		this._map.put(sid, session);
		return session;
	}

	@Override
	public ISession getSession(SessionId sid) {
		return this._map.get(sid);
	}

}
