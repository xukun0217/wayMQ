package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IMemberPhone extends ISessionElement {

	interface Key {

		// head keys
		String phone_num = "phone_number";

		// body keys
		String member_id = "member_id";
	}

	void setMember(IMember member);

	String getPhoneNumber();

	IMember getMember();
}
