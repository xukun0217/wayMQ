package waymq;

public class DefaultSession implements ISession {

	private final SessionId _id;
	private IUser _user;
	private ITempPassword _tmp_passwd;

	public DefaultSession(SessionId sid) {
		this._id = sid;
	}

	@Override
	public IUser getUser() {
		return this._user;
	}

	@Override
	public void bindUser(IUser user) {
		this._user = user;
	}

	@Override
	public SessionId getId() {
		return this._id;
	}

	@Override
	public ITempPassword getTempPassword() {
		return this._tmp_passwd;
	}

	@Override
	public void setTempPassword(ITempPassword tp) {
		this._tmp_passwd = tp;
	}

}
