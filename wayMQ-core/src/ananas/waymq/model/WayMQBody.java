package ananas.waymq.model;

import ananas.objectbox.IObject;
import ananas.objectbox.json.JsonLS;
import ananas.waymq.core.ISession;
import ananas.waymq.core.ISessionElement;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public abstract class WayMQBody implements ISessionElement {

	private final ISession _session;
	private final IObject _object;
	private boolean _is_mod;

	public WayMQBody(ISession session, IObject obj) {
		this._session = session;
		this._object = obj;
	}

	@Override
	public ISession getSession() {
		return this._session;
	}

	@Override
	public IObject getObject() {
		return this._object;
	}

	@Override
	public final void load() {
		JSONObject json = JsonLS.load(this.getObject());
		if (json == null)
			return;
		this.onLoad(json);
	}

	@Override
	public final void save() {
		if (this._is_mod) {
			this._is_mod = false;
		} else {
			return;
		}
		JSONObject json = new JSONObject();
		this.onSave(json);
		JsonLS.save(this.getObject(), json);
	}

	@Override
	public boolean isModified() {
		return this._is_mod;
	}

	@Override
	public void setModified(boolean mod) {
		this._is_mod = mod;
	}

	public ObjectId getId() {
		return this._object.getId();
	}

}
