package ananas.waymq.api;

import ananas.xgit.repo.ObjectId;

public interface IElement {

	ObjectId getId();

	IDocument getDocument();
}
