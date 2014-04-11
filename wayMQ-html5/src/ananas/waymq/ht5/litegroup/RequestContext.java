package ananas.waymq.ht5.litegroup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class RequestContext {

	public String _do;
	public final HttpServletResponse _response;
	public final HttpServletRequest _request;
	public String _target_path;
	public LG2Repo _repo;
	public final JSONObject _json;

	public RequestContext(HttpServletRequest request,
			HttpServletResponse response) {
		this._request = request;
		this._response = response;
		this._json = new JSONObject();
	}

}
