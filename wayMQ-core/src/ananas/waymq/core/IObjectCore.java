package ananas.waymq.core;

import ananas.objectbox.IObject;
import ananas.waymq.api.IDocument;
import ananas.xgit.repo.ObjectId;

public interface IObjectCore {

	ObjectId getId();

	IObject getObject();

	IWrapper getWrapper();

	void setWrapper(IWrapper wrapper);

	void setModified(boolean is_mod);

	boolean isModified();

	void save();

	IDocument getDocument();

}
