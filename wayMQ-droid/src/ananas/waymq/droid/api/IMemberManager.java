package ananas.waymq.droid.api;

import java.io.File;
import java.util.List;

public interface IMemberManager {

	List<IMember> findMembers(String keyword);

	IMember addMember(IMember member);

	void setModifiedMember(IMember member);

	void save(File path);

	void load(File path);

}
