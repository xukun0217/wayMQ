package ananas.waymq.inner;

import ananas.waymq.core.ISessionElement;

public interface IMember extends ISessionElement {

	interface Key {

		// head keys
		String phone_id = "phone_id";

		// body keys
		String join_event_list_id = "join_event_list";
		String join_group_list_id = "join_group_list";
	}

	IJoinGroup[] listGroups();

	IJoinEvent[] listEvents();

	IGroup createGroup(String name);

	IMemberPhone getPhone();

}
