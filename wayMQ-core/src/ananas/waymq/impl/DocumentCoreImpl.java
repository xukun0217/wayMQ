package ananas.waymq.impl;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import ananas.lib.io.vfs.VFile;
import ananas.objectbox.DefaultBox;
import ananas.objectbox.IBox;
import ananas.objectbox.IObject;
import ananas.waymq.api.IDocument;
import ananas.waymq.core.IDocumentCore;
import ananas.waymq.core.IObjectCore;
import ananas.waymq.core.IWrapper;
import ananas.waymq.core.wrapper.WrapDocument;
import ananas.xgit.repo.ObjectId;

final class DocumentCoreImpl implements IDocumentCore {

	private final IBox _box;
	private final Map<ObjectId, IObjectCore> _table;
	private IDocument _doc;

	public DocumentCoreImpl(VFile file) {
		this._box = new DefaultBox(file);
		this._table = new Hashtable<ObjectId, IObjectCore>();
	}

	@Override
	public void save() {
		Collection<IObjectCore> coll = this._table.values();
		for (IObjectCore oc : coll) {
			oc.save();
		}
	}

	@Override
	public IBox getBox() {
		return this._box;
	}

	@Override
	public IObjectCore getObject(ObjectId id) {

		IObjectCore core = this._table.get(id);
		if (core != null) {
			return core;
		}

		IObject obj = this._box.getObject(id);
		if (!obj.getHeadFile().exists()) {
			return null;
		}

		IDocument doc = this.getDocument();
		core = new ObjectCoreImpl(doc, obj);
		Class<?> type = obj.getTypeClass();
		IWrapper wrapper = null;
		try {
			wrapper = (IWrapper) type.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		wrapper.setObjectCore(core);
		core.setWrapper(wrapper);

		this._table.put(id, core);
		return core;
	}

	@Override
	public IObjectCore createObject(Class<?> type, Map<String, String> param) {
		IObject obj = this._box.newObject(type, param);
		ObjectId id = obj.getId();
		return this.getObject(id);
	}

	@Override
	public IDocument getDocument() {
		if (this._doc == null) {
			this._doc = new WrapDocument(this);
		}
		return this._doc;
	}

}
