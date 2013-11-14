package waymq;

public class DefaultDataModel implements IDataModel {

	private final ISessionManager _session_man = new DefaultSessionManager();
	private final IUserManager _user_man = new DefaultUserManager();

	@Override
	public IUser getUser(UserId uid) {
		return this._user_man.getUser(uid);
	}

	@Override
	public ISession getSession(SessionId sid) {
		return this._session_man.getSession(sid);
	}

	@Override
	public ISession newSession() {
		return this._session_man.newSession();
	}

	@Override
	public IUser newUser(String phone) {
		return this._user_man.newUser(phone);
	}

}
