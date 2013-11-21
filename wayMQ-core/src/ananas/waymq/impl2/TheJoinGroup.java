package ananas.waymq.impl2;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IMember;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class TheJoinGroup extends TheElement implements IJoinGroup {

	private ObjectId _member;
	private ObjectId _group;
	private long _create_time;

	public TheJoinGroup(IDocument doc, ObjectId id) {
		super(doc, id);
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("member", "" + this._member);
		json.put("group", "" + this._group);
		json.put("timestamp", this._create_time);
		super.onSave(json);
	}

	@Override
	public IGroup getGroup() {
		return this.getDocument().getGroup(this._group);
	}

	@Override
	public IMember getMember() {
		return this.getDocument().getMember(this._member);
	}

	public void setGroup(IGroup group) {
		if ((group != null) && (this._group == null)) {
			this._group = group.getId();
		}
	}

	public void setMember(IMember member) {
		if ((member != null) && (this._member == null)) {
			this._member = member.getId();
		}
	}

	@Override
	public long getTime() {
		return this._create_time;
	}

	public void setTime(long time) {
		if (_create_time == 0) {
			_create_time = time;
		}
	}

}
