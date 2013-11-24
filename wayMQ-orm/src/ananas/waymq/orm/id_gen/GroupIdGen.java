package ananas.waymq.orm.id_gen;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class GroupIdGen implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor si, Object obj)
			throws HibernateException {

		String s = System.currentTimeMillis() + "" + this;
		s = Util.string_sha1_string(s);
		return s;
	}

}
