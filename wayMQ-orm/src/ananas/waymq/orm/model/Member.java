package ananas.waymq.orm.model;

import ananas.waymq.orm.OrmObject;

public class Member extends  OrmObject  {

	private String phoneId;
	private String groupId;
	private String nickname;

	public Member() {
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
