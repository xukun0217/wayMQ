package ananas.waymq.orm.id_gen;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import ananas.waymq.orm.model.Group;
import ananas.waymq.orm.model.Member;
import ananas.waymq.orm.model.Phone;

public class MemberIdGen implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor si, Object obj)
			throws HibernateException {

		Member member = (Member) obj;
		String phone = member.getPhoneId();
		String group = member.getGroupId();
		return MemberIdGen.getIdOf(group, phone);
	}

	public static String getIdOf(Group group, Phone phone) {
		String s1 = group.getId();
		String s2 = phone.getId();
		return getIdOf(s1, s2);
	}

	public static String getIdOf(String group, String phone) {
		String s1 = group + "&" + phone;
		String s2 = Util.string_sha1_string(s1);
		System.out.println("memberId:" + s2 + " = sha1:" + s1);
		return s2;
	}

}
