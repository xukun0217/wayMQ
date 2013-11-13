package waymq;

import com.alibaba.fastjson.JSONObject;

public interface IJsonResponder {

	JSONObject proc(IJsonRequestContext context);

}
