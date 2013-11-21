package ananas.waymq.api;

import com.alibaba.fastjson.JSONObject;

public interface IElementCtrl {

	IElement getElement();

	void onSave(JSONObject json);

	void onLoad(JSONObject json);

	void onCreate();

}
