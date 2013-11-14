package waymq.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import waymq.DefaultRespDriver;
import waymq.DefaultTempPassword;
import waymq.IDataModel;
import waymq.IJsonRequestContext;
import waymq.IJsonResponder;
import waymq.IJsonResponderDriver;
import waymq.ISession;
import waymq.ITempPassword;
import waymq.IUser;
import waymq.UserId;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Sign
 */
@WebServlet("/Sign")
public class Sign extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IJsonResponder _resp;
	private final IJsonResponderDriver _driver;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sign() {
		super();
		_resp = new Proc();
		_driver = new DefaultRespDriver(_resp);

		_driver.setNeedLogin(false);
		_driver.setNeedAdmin(false);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this._driver.proc(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this._driver.proc(request, response);
	}

	class Proc implements IJsonResponder {

		@Override
		public JSONObject proc(IJsonRequestContext context) {
			Map<String, String> req = context.getRequest();
			String doName = req.get("do");
			if ("up".equals(doName)) {
				this.__do_up(context);
			} else if ("out".equals(doName)) {
				this.__do_out(context);
			} else if ("in".equals(doName)) {
				this.__do_in(context);
			} else if ("passwd".equals(doName)) {
				this.__do_get_passwd(context);
			} else /* state */{
				this.__do_state(context);
			}
			return context.getResponse();
		}

		private void __do_get_passwd(IJsonRequestContext context) {

			Map<String, String> req = context.getRequest();
			JSONObject resp = context.getResponse();

			String phone = req.get("pn");

			ISession session = context.getSession();
			ITempPassword tp = session.getTempPassword();
			if (tp != null) {
				if (!tp.isUseable(phone))
					tp = null;
			}
			if (tp == null) {
				tp = DefaultTempPassword.gen(phone);
				session.setTempPassword(tp);
			}
			String psw = tp.getPlainText();

			resp.put("timeout", "" + tp.getTimeout());

			System.out.println("get password for phone");
			System.out.println("     phone=" + phone);
			System.out.println("    passwd=" + psw);
		}

		private void __do_in(IJsonRequestContext context) {

			if (!this.__is_psw_ok(context)) {
				return;
			}

			JSONObject resp = context.getResponse();
			ISession session = context.getSession();
			String phone = session.getTempPassword().getPhoneNum();

			UserId uid = UserId.idByPhoneNumber(phone);
			IUser user = context.getModel().getUser(uid);
			if (user == null) {
				resp.put("state", "off");
				return;
			}
			session.bindUser(user);
			session.setTempPassword(null);
			resp.put("state", "on");
		}

		private void __do_out(IJsonRequestContext context) {
			// TODO Auto-generated method stub

		}

		private void __do_up(IJsonRequestContext context) {

			if (!this.__is_psw_ok(context)) {
				return;
			}

			Map<String, String> req = context.getRequest();
			JSONObject resp = context.getResponse();
			ISession session = context.getSession();
			ITempPassword tp = session.getTempPassword();
			String phone = tp.getPhoneNum();

			IDataModel model = context.getModel();

			IUser user = model.newUser(phone);
			{
				String name = req.get("name") + "";
				user.setName(name);
			}
			session.bindUser(user);
			session.setTempPassword(null);
			resp.put("state", "on");
		}

		private boolean __is_psw_ok(IJsonRequestContext context) {

			Map<String, String> req = context.getRequest();
			JSONObject resp = context.getResponse();
			resp.put("state", "off");

			ISession session = context.getSession();
			ITempPassword tp = session.getTempPassword();
			if (tp == null) {
				return false;
			}

			String passwd = req.get("pw");
			if (!tp.getPlainText().equals(passwd)) {
				return false;
			}

			if (!tp.isTimeout()) {
				return false;
			}

			return true;
		}

		private void __do_state(IJsonRequestContext context) {

			JSONObject resp = context.getResponse();
			IUser user = context.getUser();
			String state = "off";
			if (user != null) {
				if (!user.isTemp()) {
					state = "on";
					resp.put("name", user.getName() + "");
				}
			}
			resp.put("state", state);
		}

	}

}
