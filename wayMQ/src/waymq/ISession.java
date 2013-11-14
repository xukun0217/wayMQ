package waymq;

public interface ISession {

	IUser getUser();

	void bindUser(IUser user);

	SessionId getId();

	ITempPassword getTempPassword();

	void setTempPassword(ITempPassword tp);

}
