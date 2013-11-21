package ananas.waymq.impl2;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IGroup;
import ananas.waymq.api.IHoldEvent;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class TheHoldEvent extends TheElement implements IHoldEvent {

	private long _timestamp;
	private ObjectId _event;
	private ObjectId _group;

	public TheHoldEvent(IDocument doc, ObjectId id) {
		super(doc, id);
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("timestamp", this._timestamp);
		json.put("event", "" + this._event);
		json.put("group", "" + this._group);
		super.onSave(json);
	}

	@Override
	public IGroup getGroup() {
		return this.getDocument().getGroup(_group);
	}

	@Override
	public IEvent getEvent() {
		return this.getDocument().getEvent(_event);
	}

	@Override
	public long getTime() {
		return this._timestamp;
	}

	public void setGroup(IGroup group) {
		this._group = group.getId();
	}

	public void setEvent(IEvent event) {
		this._event = event.getId();
	}

	public void setTime(long time) {
		this._timestamp = time;

	}

}
