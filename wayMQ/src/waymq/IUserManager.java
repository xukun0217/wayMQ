package waymq;

public interface IUserManager {

	IUser getUser(UserId uid);

	IUser newUser(String phone);

}
