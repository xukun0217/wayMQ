package ananas.waymq.orm.dao;

import ananas.waymq.orm.BaseDAO;
import ananas.waymq.orm.model.Phone;
import ananas.waymq.orm.model.User;

public class UserDAO extends BaseDAO<User> {

	public UserDAO() {
		super(User.class);
	}

	public User create(Phone phone) {
		String uid = phone.getUserId();
		User user = null;
		if (uid != null) {
			user = this.get(uid);
		}
		if (user != null) {
			return user;
		}
		user = new User();
		user.setPhoneId(phone.getId());
		user = this.put(user);

		// update phone
		PhoneDAO dao_phone = new PhoneDAO();
		phone.setUserId(user.getId());
		dao_phone.put(phone);

		return user;
	}

}
