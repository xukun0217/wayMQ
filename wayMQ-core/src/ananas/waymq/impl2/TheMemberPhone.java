package ananas.waymq.impl2;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.xgit.repo.ObjectId;

public class TheMemberPhone extends TheElement implements IMemberPhone {

	private final String _phone_num;
	private ObjectId _member_id;

	public TheMemberPhone(IDocument doc, String phone) {
		super(doc, doc.genId(phone));
		this._phone_num = phone;
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("phone", this._phone_num + "");
		json.put("member", this._member_id + "");
		super.onSave(json);
	}

	@Override
	public void setMember(IMember member) {

		if (member != null)
			this._member_id = member.getId();

	}

	@Override
	public String getPhoneNumber() {
		return this._phone_num;
	}

	@Override
	public IMember getMember() {
		return this.getDocument().getMember(this._member_id);
	}

}
