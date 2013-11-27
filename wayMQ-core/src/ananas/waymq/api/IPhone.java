package ananas.waymq.api;

public interface IPhone extends IElement {

	void addMember(IMember member);

	void removeMember(IMember member);

	String getNumber();

	IMember[] listMembers();

	IUser getUser();

	void setUser(IUser user);

}
