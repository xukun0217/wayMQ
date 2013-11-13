package waymq;

public interface ISession {

	IUser getUser();

	void setUser(IUser user);

	SessionId getId();
}
