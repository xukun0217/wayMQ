package waymq;

import java.util.Hashtable;
import java.util.Map;

public class DefaultUserManager implements IUserManager {

	private final Map<UserId, IUser> _map;

	public DefaultUserManager() {
		this._map = new Hashtable<UserId, IUser>();
	}

	@Override
	public IUser getUser(UserId uid) {
		return this._map.get(uid);
	}

	@Override
	public IUser newUser(String phone) {
		UserId uid = UserId.idByPhoneNumber(phone);
		IUser user = this.getUser(uid);
		if (user != null)
			return user;
		user = new DefaultUser(uid, phone);
		_map.put(uid, user);
		return user;
	}
}
