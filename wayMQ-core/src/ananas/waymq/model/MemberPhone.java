package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.waymq.core.ElementProxy;
import ananas.waymq.core.ISession;
import ananas.waymq.inner.IMember;
import ananas.waymq.inner.IMemberPhone;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class MemberPhone extends WayMQBody implements IMemberPhone {

	private final String _phone_num;

	private ElementProxy<IMember> _member;

	public MemberPhone(ISession session, IObject obj) {
		super(session, obj);

		this._phone_num = obj.getHeader(Key.phone_num);
		this._member = new ElementProxy<IMember>(session, "");
	}

	@Override
	public void setMember(IMember member) {
		ISession session = this.getSession();
		ObjectId id = member.getObject().getId();
		this._member = new ElementProxy<IMember>(session, id);
	}

	@Override
	public String getPhoneNumber() {
		return this._phone_num;
	}

	@Override
	public IMember getMember() {
		return this._member.get();
	}

	@Override
	public void onLoad(JSONObject json) {

		if (json == null) {
			return;
		}

		// body
		String idstr = json.getString(Key.member_id);
		ISession session = this.getSession();
		this._member = new ElementProxy<IMember>(session, idstr);

	}

	@Override
	public void onSave(JSONObject json) {
		ObjectId id = this.getMember().getObject().getId();
		json.put(Key.member_id, id + "");
	}

}
