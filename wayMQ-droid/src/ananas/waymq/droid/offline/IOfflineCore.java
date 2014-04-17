package ananas.waymq.droid.offline;

import java.io.File;

public interface IOfflineCore {

	IMemberInfo[] findMembers(String keyword);

	IMemberInfo getMemberInfo(String id);

	String normalizeId(String id);

	/**
	 * @return the id of the new member, or null if failed.
	 * */
	String addMember(String id, String name);

	void doMemberSign(String id, String name, int count, int money);

	// path manager

	File getWorkingPath();

	// event

	IEvent[] listEvents();

	IEvent getCurrentEvent();

	void setCurrentEvent(IEvent event);

}
