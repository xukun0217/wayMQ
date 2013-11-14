package waymq;

public interface ISessionManager {

	ISession newSession();

	ISession getSession(SessionId sid);

}
