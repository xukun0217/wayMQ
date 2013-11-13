package waymq;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface IJsonRequestContext {

	Map<String, String> getRequest();

	JSONObject getResponse();

	boolean isLogin();

	boolean isAdmin();

	IDataModel getModel();

}
