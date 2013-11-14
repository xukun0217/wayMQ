package waymq;

public class DefaultTempUser implements IUser {

	private final UserId _id;
	private final String _phone;
	private final String _name;

	public DefaultTempUser() {
		this._id = UserId.idByPhoneNumber("0");
		this._name = "";
		this._phone = "";
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public boolean isTemp() {
		return true;
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
		return this._id;
	}

	@Override
	public void setName(String name) {
	}

}
