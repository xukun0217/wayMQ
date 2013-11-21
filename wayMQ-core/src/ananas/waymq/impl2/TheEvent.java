package ananas.waymq.impl2;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.api.IMember;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class TheEvent extends TheElement implements IEvent {

	private ObjectId _group;
	private ObjectId _member;
	private ObjectId _join_list;
	private String _name;

	public TheEvent(IDocument doc, ObjectId id) {
		super(doc, id);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IDocument doc = this.getDocument();
		this._join_list = doc.newJoinEventList(null, this).getId();
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("creator_group", "" + this._group);
		json.put("creator", "" + this._member);
		json.put("member_list", "" + this._join_list);
		json.put("name", "" + this._name);
		super.onSave(json);
	}

	@Override
	public IJoinEvent[] listJoins() {
		return this.getJoinEventList().toArray();
	}

	@Override
	public IJoinEventList getJoinEventList() {
		ObjectId id = this._join_list;
		return this.getDocument().getJoinEventList(id);
	}

	@Override
	public IGroup getCreatorGroup() {
		return this.getDocument().getGroup(this._group);
	}

	@Override
	public IMember getCreator() {
		return this.getDocument().getMember(this._member);
	}

	public void setCreator(IGroup group, IMember member) {
		this._group = group.getId();
		this._member = member.getId();
	}

	@Override
	public String getName() {
		return this._name;
	}

	@Override
	public void setName(String name) {
		this._name = name;
	}

}
