package waymq;

public interface IDataModel {

	IUser getUser(UserId uid);

	ISession getSession(SessionId sid);

}
