package ananas.waymq.core;

public class DefaultMember implements IMember {

	private final MemberId _id;
	private String _name;

	public DefaultMember(MemberId id) {
		this._id = id;
	}

	public DefaultMember(PhoneNum pn) {
		this._id = new MemberId(pn);
	}

	@Override
	public MemberId getId() {
		return this._id;
	}

	@Override
	public String getName() {
		return this._name;
	}

}
