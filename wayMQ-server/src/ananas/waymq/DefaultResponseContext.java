package ananas.waymq;

import com.alibaba.fastjson.JSONObject;

public class DefaultResponseContext implements ResponseContext {

	private String _class;
	private String _this;
	private String _method;
	private String _token;

	public DefaultResponseContext(JSONObject request, JSONObject response) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getParameter(String method) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTarget(String class_, String method_, String this_,
			String token_) {
		this._class = class_;
		this._this = this_;
		this._method = method_;
		this._token = token_;
	}

	@Override
	public String getParameter(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WayMQ getApp() {
		// TODO Auto-generated method stub
		return null;
	}

}
