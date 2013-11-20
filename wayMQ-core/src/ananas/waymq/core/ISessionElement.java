package ananas.waymq.core;

import ananas.objectbox.IObject;

import com.alibaba.fastjson.JSONObject;

public interface ISessionElement {

	ISession getSession();

	IObject getObject();

	void load();

	void save();

	void onLoad(JSONObject json);

	void onSave(JSONObject json);

	boolean isModified();

	void setModified(boolean mod);

}
