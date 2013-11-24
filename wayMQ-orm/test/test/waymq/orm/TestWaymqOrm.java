package test.waymq.orm;

import java.util.List;

import org.junit.Test;

import ananas.waymq.orm.DataAgent;
import ananas.waymq.orm.DefaultDataAgent;
import ananas.waymq.orm.model.Group;
import ananas.waymq.orm.model.Member;
import ananas.waymq.orm.model.User;

public class TestWaymqOrm {

	@Test
	public void test() {

		DataAgent da = new DefaultDataAgent();

		String pn = "2553919";
		// String id = PhoneIdGen.getIdByNumber(num);

		User user = da.getUserByPhone(pn);
		if (user == null) {
			user = da.createUser(pn, "li ming", "12345678");
		}

		Group group = null;
		List<Group> groups = da.listGroups(user);
		if (groups.size() > 0) {
			group = groups.get(0);
		} else {
			group = da.createGroup(user, "wo ai yu mao qiu");
			if (group != null) {
				groups.add(group);
			}
		}

		Member member = null;
		List<Member> members = da.listMembers(group);
		if (members.size() > 0) {
			member = members.get(0);
		} else {
			member = da.addMember(group, pn, user.getNickname());
		}
		member.getPhoneId();

	}

	public static void main(String[] arg) {

		(new TestWaymqOrm()).test();

		for (int i = 0; i > 0; i--) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
