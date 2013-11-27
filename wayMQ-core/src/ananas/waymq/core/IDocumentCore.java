package ananas.waymq.core;

import java.util.Map;

import ananas.objectbox.IBox;
import ananas.waymq.api.IDocument;
import ananas.xgit.repo.ObjectId;

public interface IDocumentCore {

	void save();

	IBox getBox();

	IObjectCore getObject(ObjectId id);

	IObjectCore createObject(Class<?> type, Map<String, String> param);

	IDocument getDocument();

}
