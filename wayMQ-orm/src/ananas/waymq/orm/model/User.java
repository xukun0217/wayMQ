package ananas.waymq.orm.model;

import ananas.waymq.orm.OrmObject;

public class User extends OrmObject {

	private String password;
	private String nickname;
	private String phoneId;
	private String myGroupId;

	public User() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMyGroupId() {
		return myGroupId;
	}

	public void setMyGroupId(String myGroupId) {
		this.myGroupId = myGroupId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

}
