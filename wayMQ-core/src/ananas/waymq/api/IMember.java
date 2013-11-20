package ananas.waymq.api;

import ananas.waymq.core.ISessionElement;

public interface IMember extends ISessionElement {

	interface Key {

		// head keys
		String phone_id = "phone_id";

		// body keys
	}

	IJoinGroup[] listGroups();

	IJoinEvent[] listEvents();

	IGroup createGroup(String name);

	IMemberPhone getPhone();

}
