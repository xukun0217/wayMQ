package ananas.waymq.orm;

import java.util.List;

import ananas.waymq.orm.dao.GroupDAO;
import ananas.waymq.orm.dao.MemberDAO;
import ananas.waymq.orm.dao.PhoneDAO;
import ananas.waymq.orm.dao.UserDAO;
import ananas.waymq.orm.id_gen.MemberIdGen;
import ananas.waymq.orm.id_gen.PhoneIdGen;
import ananas.waymq.orm.model.Group;
import ananas.waymq.orm.model.Member;
import ananas.waymq.orm.model.Phone;
import ananas.waymq.orm.model.User;

public class DefaultDataAgent implements DataAgent {

	private static final GroupDAO dao_group = new GroupDAO();
	private static final MemberDAO dao_member = new MemberDAO();
	private static final PhoneDAO dao_phone = new PhoneDAO();
	private static final UserDAO dao_user = new UserDAO();

	@Override
	public Group createGroup(User user, String name) {
		Group group = dao_group.create(user);
		group.setName(name);
		return dao_group.put(group);
	}

	@Override
	public Member addMember(Group group, String phoneNum, String name) {
		Phone phone = this.getPhoneByNumber(phoneNum, true);
		// create
		Member member = dao_member.create(group, phone);
		member.setNickname(name);
		return dao_member.put(member);
	}

	@Override
	public User createUser(String phoneNum, String name, String password) {
		Phone phone = this.getPhoneByNumber(phoneNum, true);
		// create
		User user = dao_user.create(phone);
		user.setNickname(name);
		user.setPassword(password);
		return dao_user.put(user);
	}

	@Override
	public User getUser(String id) {
		return dao_user.get(id);
	}

	@Override
	public Group getGroup(String id) {
		return dao_group.get(id);
	}

	@Override
	public Member getMember(String id) {
		return dao_member.get(id);
	}

	@Override
	public User getUserByPhone(String phoneNum) {
		Phone phone = this.getPhoneByNumber(phoneNum, false);
		if (phone == null)
			return null;
		return this.getUser(phone.getUserId());
	}

	@Override
	public Member getMemberByPhone(Group group, String phoneNum) {

		Phone phone = this.getPhoneByNumber(phoneNum, false);
		if (phone == null)
			return null;

		String id = MemberIdGen.getIdOf(group, phone);
		return dao_member.get(id);
	}

	@Override
	public List<Group> listGroups(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> listMembers(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Phone getPhoneByNumber(String number) {
		String id = PhoneIdGen.getIdByNumber(number);
		return dao_phone.get(id);
	}

	@Override
	public Phone getPhoneByNumber(String number, boolean create) {
		String id = PhoneIdGen.getIdByNumber(number);
		Phone phone = dao_phone.get(id);
		if (phone == null) {
			if (create) {
				phone = dao_phone.create(number);
			}
		}
		return phone;
	}

}
