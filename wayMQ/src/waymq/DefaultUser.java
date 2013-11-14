package waymq;

public class DefaultUser implements IUser {

	private final UserId _uid;
	private final String _phone;
	private String _name;

	public DefaultUser(UserId uid, String phone) {
		this._uid = uid;
		this._phone = phone;
	}

	@Override
	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTemp() {
		return false;
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public String getPhone() {
		return this._phone;
	}

	@Override
	public UserId getId() {
		return this._uid;
	}

	@Override
	public void setName(String name) {
		this._name = name;
	}

}
