package ananas.waymq.orm.id_gen;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import ananas.waymq.orm.model.Phone;

public class PhoneIdGen implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor si, Object obj)
			throws HibernateException {

		Phone phone = (Phone) obj;
		return getIdByNumber(phone.getNumber());
	}

	public static String getIdByNumber(String number) {
		return Util.string_sha1_phone(number);
	}

}
