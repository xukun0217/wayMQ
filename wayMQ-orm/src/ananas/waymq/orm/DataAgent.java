package ananas.waymq.orm;

import java.util.List;

import ananas.waymq.orm.model.Group;
import ananas.waymq.orm.model.Member;
import ananas.waymq.orm.model.Phone;
import ananas.waymq.orm.model.User;

public interface DataAgent {

	// create

	Group createGroup(User user, String name);

	Member addMember(Group group, String phoneNum, String name);

	User createUser(String phoneNum, String name, String password);

	// update

	// get

	User getUser(String id);

	Group getGroup(String id);

	Member getMember(String id);

	User getUserByPhone(String phoneNum);

	Member getMemberByPhone(Group group, String phoneNum);

	Phone getPhoneByNumber(String number);

	Phone getPhoneByNumber(String number, boolean create);

	// list

	List<Group> listGroups(User user);

	List<Member> listMembers(Group group);

}
