package ananas.waymq.core;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface IWrapper {

	IObjectCore getObjectCore();

	void setObjectCore(IObjectCore core);

	void onSave(IObjectCore core);

	void onLoad(IObjectCore core);

	void onLoad(Map<String, String> head, JSONObject body);

	void onSave(JSONObject body);

}
