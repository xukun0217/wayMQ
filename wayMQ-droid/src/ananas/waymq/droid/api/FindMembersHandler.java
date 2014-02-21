package ananas.waymq.droid.api;

import java.util.List;

public interface FindMembersHandler {

	void onFindMembers(String keyword, List<IMember> result);

}
