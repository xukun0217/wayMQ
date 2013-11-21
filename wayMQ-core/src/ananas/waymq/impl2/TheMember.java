package ananas.waymq.impl2;

import com.alibaba.fastjson.JSONObject;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IJoinEventList;
import ananas.waymq.api.IJoinGroup;
import ananas.waymq.api.IJoinGroupList;
import ananas.waymq.api.IMember;
import ananas.waymq.api.IMemberPhone;
import ananas.xgit.repo.ObjectId;

public class TheMember extends TheElement implements IMember {

	private ObjectId _phone;
	private ObjectId _event_list;
	private ObjectId _group_list;

	public TheMember(IDocument doc, ObjectId id) {
		super(doc, id);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		IDocument doc = this.getDocument();
		this._event_list = doc.newJoinEventList(this, null).getId();
		this._group_list = doc.newJoinGroupList(this, null).getId();
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("phone", "" + this._phone);
		json.put("event_list", "" + this._event_list);
		json.put("group_list", "" + this._group_list);
		super.onSave(json);
	}

	@Override
	public IJoinGroup[] listGroups() {
		return this.getJoinGroupList().toArray();
	}

	@Override
	public IJoinEvent[] listEvents() {
		return this.getJoinEventList().toArray();
	}

	@Override
	public IGroup createGroup(String name) {
		IDocument doc = this.getDocument();
		IGroup group = doc.newGroup(name, this);
		IJoinGroup join = doc.newJoinGroup(this, group);
		this.getJoinGroupList().add(join);
		return group;
	}

	@Override
	public IMemberPhone getPhone() {
		return this.getDocument().getMemberPhone(this._phone);
	}

	@Override
	public IEvent createEvent(String name, IGroup group) {
		IDocument doc = this.getDocument();
		IEvent event = doc.newEvent(name, group, this);
		IHoldEvent hold = doc.newHoldEvent(group, event);
		group.getHoldEventList().add(hold);
		this.join(event);
		return event;
	}

	@Override
	public void join(IEvent event) {
		IDocument doc = this.getDocument();
		IJoinEvent join = doc.newJoinEvent(this, event);
		this.getJoinEventList().add(join);
		event.getJoinEventList().add(join);
	}

	@Override
	public void join(IGroup group) {
		IDocument doc = this.getDocument();
		IJoinGroup join = doc.newJoinGroup(this, group);
		this.getJoinGroupList().add(join);
		group.getJoinGroupList().add(join);
	}

	public void setPhone(IMemberPhone phone) {
		if (phone != null)
			this._phone = phone.getId();
	}

	@Override
	public IJoinEventList getJoinEventList() {
		return this.getDocument().getJoinEventList(this._event_list);
	}

	@Override
	public IJoinGroupList getJoinGroupList() {
		return this.getDocument().getJoinGroupList(this._group_list);
	}

}
