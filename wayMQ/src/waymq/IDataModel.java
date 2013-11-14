package waymq;

public interface IDataModel {

	IUser newUser(String phone);

	IUser getUser(UserId uid);

	ISession getSession(SessionId sid);

	ISession newSession();

}
