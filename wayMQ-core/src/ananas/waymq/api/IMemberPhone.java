package ananas.waymq.api;

public interface IMemberPhone extends IElement {

	void setMember(IMember member);

	String getPhoneNumber();

	IMember getMember();
}
