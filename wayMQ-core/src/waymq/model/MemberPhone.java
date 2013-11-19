package waymq.model;

import java.util.Map;

import ananas.objectbox.IObjectBody;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.waymq.core.WayMQBody;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class MemberPhone extends WayMQBody implements IMemberPhone {

	private ObjectId _member;
	private String _phone;

	@Override
	public void onLoad(JSONObject root) {
		Map<String, String> fields = this.getHead().getFields();
		this._member = ObjectId.Factory.create(root.getString(Key.member_id));
		this._phone = fields.get(Key.phone_num);
	}

	@Override
	public JSONObject onSave(JSONObject root) {
		root.put(Key.member_id, this._member.toString());
		return root;
	}

	@Override
	public IMember getMember() {
		return (IMember) this.get(_member);
	}

	@Override
	public void setMember(IMember member) {
		IObjectBody body = (IObjectBody) member;
		this._member = body.getHead().getId();
		this.getHead().setModified(true);
	}

	@Override
	public String getPhoneNumber() {
		return this._phone;
	}

}
