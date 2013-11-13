package waymq;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class DefaultRespDriver implements IJsonResponderDriver {

	private final IJsonResponder _resper;
	private boolean _need_login;
	private boolean _need_admin;

	public DefaultRespDriver(IJsonResponder resper) {
		this._resper = resper;
	}

	@Override
	public void proc(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {

			IJsonRequestContext context = new MyContext(request, response);
			if (!this.__check_user(context, request, response)) {
				return;
			}
			JSONObject json = this._resper.proc(context);
			OutputStream out = response.getOutputStream();
			Writer wtr = new OutputStreamWriter(out, "UTF-8");
			JSON.writeJSONStringTo(json, wtr);

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getOutputStream().println("" + e);
		}
	}

	private boolean __check_user(IJsonRequestContext context,
			HttpServletRequest request, HttpServletResponse response) {

		IUser user = context.getUser();
		if (this._need_login) {
			if (user.isTemp()) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}
		if (this._need_admin) {
			if (!user.isAdmin()) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
		}
		return true;
	}

	@Override
	public void setNeedAdmin(boolean need) {
		this._need_admin = need;
	}

	@Override
	public void setNeedLogin(boolean need) {
		this._need_login = need;
	}

	interface Key {

		String user_id = "waymq.uid";
		String user_token = "waymq.token";
		String session_id = "waymq.sid";

	}

	class MyContext implements IJsonRequestContext {

		private final HttpServletRequest _req;
		private final HttpServletResponse _resp;
		private final JSONObject _resp_json;
		private Map<String, String> _req_map;
		private IUser _user;

		public MyContext(HttpServletRequest request,
				HttpServletResponse response) {

			this._req = request;
			this._resp = response;
			this._resp_json = JSON.parseObject("{}");

		}

		@Override
		public Map<String, String> getRequest() {
			Map<String, String> map = this._req_map;
			if (map != null)
				return map;
			{
				map = new HashMap<String, String>();
				HttpServletRequest req = this._req;
				Enumeration<String> enu = req.getParameterNames();
				for (; enu.hasMoreElements();) {
					String name = enu.nextElement();
					String value = req.getParameter(name);
					map.put(name, value);
				}
			}
			this._req_map = map;
			return map;
		}

		@Override
		public JSONObject getResponse() {
			return this._resp_json;
		}

		@Override
		public boolean isLogin() {
			return (!this.getUser().isTemp());
		}

		@Override
		public boolean isAdmin() {
			return (this.getUser().isAdmin());
		}

		@Override
		public IUser getUser() {
			IUser user = this._user;
			if (user != null)
				return user;

			String uid = null;
			String token = null;
			String session = null;

			Cookie[] cookies = this._req.getCookies();
			for (Cookie coo : cookies) {
				String name = coo.getName();
				if (name.equals(Key.user_id)) {
					uid = coo.getValue();
				} else if (name.equals(Key.user_token)) {
					token = coo.getValue();
				} else if (name.equals(Key.session_id)) {
					session = coo.getValue();
				}
			}

			if (uid != null) {
			}

			return user;
		}

		@Override
		public IDataModel getModel() {
			IDataModel model = _model;
			if (model == null) {
				model = new DefaultDataModel();
				_model = model;
			}
			return model;
		}

	}

	private static IDataModel _model;

}
