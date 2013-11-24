package ananas.waymq.orm.dao;

import ananas.waymq.orm.BaseDAO;
import ananas.waymq.orm.model.Group;
import ananas.waymq.orm.model.Member;
import ananas.waymq.orm.model.Phone;

public class MemberDAO extends BaseDAO<Member> {

	public MemberDAO() {
		super(Member.class);
	}

	public Member create(Group group, Phone phone) {
		Member member = new Member();
		member.setPhoneId(phone.getId());
		member.setGroupId(group.getId());
		return this.put(member);
	}

}
