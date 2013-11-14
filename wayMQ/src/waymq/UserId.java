package waymq;

public class UserId extends ObjId {

	public UserId(String s) {
		super(s);
	}

	public static UserId idByPhoneNumber(String s) {
		s = __hashString(s);
		return new UserId(s);
	}

}
