package ananas.waymq.api;

public interface IMemberPhone {

	interface Key {

		String member_id = "member_id";
		String phone_num = "phone_number";
	}

	IMember getMember();

	void setMember(IMember member);

	String getPhoneNumber();
}
