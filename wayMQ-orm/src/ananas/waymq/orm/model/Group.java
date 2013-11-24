package ananas.waymq.orm.model;

import ananas.waymq.orm.OrmObject;

public class Group extends OrmObject {

	private String userId;
	private String name;
	private String discription;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

}
