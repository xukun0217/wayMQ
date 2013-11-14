package waymq;

public interface ITempPassword {

	String getPlainText();

	String getPhoneNum();

	long getTimeout();

	boolean isTimeout();

	boolean isUseable(String phone);
}
