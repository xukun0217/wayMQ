package ananas.waymq.impl;

import ananas.objectbox.IObject;
import ananas.waymq.api.IDocument;
import ananas.waymq.core.IObjectCore;
import ananas.waymq.core.IWrapper;
import ananas.xgit.repo.ObjectId;

public class ObjectCoreImpl implements IObjectCore {

	private final IDocument _doc;
	private final IObject _obj;
	private IWrapper _wrapper;
	private boolean _is_mod;

	public ObjectCoreImpl(IDocument doc, IObject obj) {
		this._obj = obj;
		this._doc = doc;
	}

	@Override
	public ObjectId getId() {
		return this._obj.getId();
	}

	@Override
	public IWrapper getWrapper() {
		return this._wrapper;
	}

	@Override
	public void setWrapper(IWrapper wrapper) {
		this._wrapper = wrapper;
	}

	@Override
	public void setModified(boolean is_mod) {
		this._is_mod = is_mod;
	}

	@Override
	public boolean isModified() {
		return this._is_mod;
	}

	@Override
	public void save() {
		if (this._is_mod) {
			this._is_mod = false;
		} else {
			return;
		}
		this._wrapper.onSave(this);
	}

	@Override
	public IObject getObject() {
		return this._obj;
	}

	@Override
	public IDocument getDocument() {
		return this._doc;
	}

}
