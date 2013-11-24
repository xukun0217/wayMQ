package ananas.waymq.orm.dao;

import ananas.waymq.orm.BaseDAO;
import ananas.waymq.orm.id_gen.PhoneIdGen;
import ananas.waymq.orm.model.Phone;

public class PhoneDAO extends BaseDAO<Phone> {

	public PhoneDAO() {
		super(Phone.class);
	}

	public Phone create(String num) {
		String id = PhoneIdGen.getIdByNumber(num);
		Phone phone = this.get(id);
		if (phone == null) {
			phone = new Phone();
			phone.setNumber(num);
			phone = this.put(phone);
		}
		return phone;
	}

}
