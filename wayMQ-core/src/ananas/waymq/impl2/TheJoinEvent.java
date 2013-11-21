package ananas.waymq.impl2;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IEvent;
import ananas.waymq.api.IJoinEvent;
import ananas.waymq.api.IMember;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class TheJoinEvent extends TheElement implements IJoinEvent {

	private ObjectId _event;
	private ObjectId _member;
	private long _time;

	public TheJoinEvent(IDocument doc, ObjectId id) {
		super(doc, id);
	}

	@Override
	public IEvent getEvent() {
		return this.getDocument().getEvent(this._event);
	}

	@Override
	public IMember getMember() {
		return this.getDocument().getMember(this._member);
	}

	@Override
	public long getTime() {
		return this._time;
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("time", this._time);
		json.put("member", this._member.toString());
		json.put("event", this._event.toString());
		super.onSave(json);
	}

	@Override
	public void onLoad(JSONObject json) {
		super.onLoad(json);
		IDocument doc = this.getDocument();
		this._time = json.getLongValue("time");
		this._member = doc.parseId(json.getString("member"));
		this._event = doc.parseId(json.getString("event"));
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public void setEvent(IEvent event) {
		if ((event != null) && (this._event == null)) {
			this._event = event.getId();
		}
	}

	public void setMember(IMember member) {
		if ((member != null) && (this._member == null)) {
			this._member = member.getId();
		}
	}

	public void setTime(long time) {
		if (this._time == 0) {
			this._time = time;
		}
	}

}
