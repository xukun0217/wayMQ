package waymq;

public interface IUser {

	boolean isAdmin();

	boolean isTemp();

	String getName();

	String getPhone();

	UserId getId();

	// setter

	void setName(String name);

}
