package ananas.waymq.orm.model;

import ananas.waymq.orm.OrmObject;

public class Set extends OrmObject {

	private String json;

	public Set() {
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
