package ananas.waymq.core;

public class MemberId extends ObjectId {

	public MemberId(String id) {
		super(id);
	}

	public MemberId(PhoneNum pn) {
		super(__id_from_pn(pn));
	}

	private static String __id_from_pn(PhoneNum pn) {
		String s = "mobile:" + pn;
		return ObjectId.sha1ForString(s);
	}

}
