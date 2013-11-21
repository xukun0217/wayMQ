package ananas.waymq.impl2;

import ananas.waymq.api.IDocument;
import ananas.waymq.api.IElement;
import ananas.waymq.api.IElementCtrl;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public class TheElement implements IElement, IElementCtrl {

	private final IDocument _doc;
	private final ObjectId _id;

	public TheElement(IDocument doc, ObjectId id) {
		this._doc = doc;
		this._id = id;
	}

	@Override
	public final ObjectId getId() {
		return this._id;
	}

	@Override
	public final IDocument getDocument() {
		return this._doc;
	}

	@Override
	public void onSave(JSONObject json) {
		json.put("id", this._id.toString());
		json.put("type", this.getClass().getSimpleName());
	}

	@Override
	public void onLoad(JSONObject json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public IElement getElement() {
		return this;
	}

}
