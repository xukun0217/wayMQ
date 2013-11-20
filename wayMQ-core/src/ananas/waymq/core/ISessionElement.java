package ananas.waymq.core;

import ananas.objectbox.IObject;
import ananas.xgit.repo.ObjectId;

import com.alibaba.fastjson.JSONObject;

public interface ISessionElement {

	ISession getSession();

	IObject getObject();

	ObjectId getId();

	void load();

	void save();

	void onLoad(JSONObject json);

	void onSave(JSONObject json);

	boolean isModified();

	void setModified(boolean mod);

}
