package ananas.waymq.model;

import com.alibaba.fastjson.JSONObject;

import ananas.objectbox.IObject;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.ISession;
import ananas.xgit.repo.ObjectId;

public class MemberPhone extends WayMQBody implements IMemberPhone {

	private IMember _member;
	private String _phone_num;

	public MemberPhone(ISession session, IObject obj) {
		super(session, obj);
	}

	@Override
	public void setMember(IMember member) {
		this._member = member;
	}

	@Override
	public String getPhoneNumber() {
		return this._phone_num;
	}

	@Override
	public IMember getMember() {
		return this._member;
	}

	@Override
	public void onLoad(JSONObject json) {

		if (json == null) {
			return;
		}

		String idstr = json.getString(Key.member_id);
		ObjectId id = ObjectId.Factory.create(idstr);
		this._member = this.getSession().getMember(id);
	}

	@Override
	public void onSave(JSONObject json) {
		ObjectId id = this.getMember().getObject().getId();
		json.put(Key.member_id, id + "");
	}

}
