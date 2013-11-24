package ananas.waymq.orm.model;

import ananas.waymq.orm.OrmObject;

public class Phone extends OrmObject {

	private String number;
	private String userId;

	public Phone() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
