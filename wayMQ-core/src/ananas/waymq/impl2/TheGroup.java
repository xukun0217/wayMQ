package ananas.waymq.impl2;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IHoldEventList;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class TheGroup extends TheElement implements IGroup {

	private ObjectId _creator;
	private String _name;
	private ObjectId _event_list;
	private ObjectId _member_list;

	public TheGroup(IDocument doc, ObjectId id) {
		super(doc, id);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IDocument doc = this.getDocument();
		this._event_list = doc.newHoldEventList(this, null).getId();
		this._member_list = doc.newJoinGroupList(null, this).getId();
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("creator", "" + this._creator);
		json.put("name", "" + this._name);
		json.put("event_list", "" + this._event_list);
		json.put("member_list", "" + this._member_list);
		super.onSave(json);
	}

	@Override
	public IJoinGroup[] listMembers() {
		return this.getJoinGroupList().toArray();
	}

	@Override
	public IHoldEvent[] listEvents() {
		return this.getHoldEventList().toArray();
	}

	@Override
	public IJoinGroupList getJoinGroupList() {
		ObjectId id = this._member_list;
		return this.getDocument().getJoinGroupList(id);
	}

	@Override
	public IHoldEventList getHoldEventList() {
		ObjectId id = this._event_list;
		return this.getDocument().getHoldEventList(id);
	}

	@Override
	public IMember getCreator() {
		return this.getDocument().getMember(this._creator);
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public void setName(String name) {
		this._name = name;
	}

	public void setCreator(IMember creator) {
		this._creator = creator.getId();
	}

}
