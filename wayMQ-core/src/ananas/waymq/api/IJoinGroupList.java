package ananas.waymq.api;

public interface IJoinGroupList {

	IJoinGroup[] toArray();

	void add(IGroup group, IMember member);

}
