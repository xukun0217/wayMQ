package waymq.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import waymq.DefaultRespDriver;
import waymq.IJsonRequestContext;
import waymq.IJsonResponder;
import waymq.IJsonResponderDriver;
import waymq.IUser;

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
			} else if ("sms".equals(doName)) {
				this.__do_sms(context);
			} else /* state */{
				this.__do_state(context);
			}
			return context.getResponse();
		}

		private void __do_sms(IJsonRequestContext context) {
			// TODO Auto-generated method stub

		}

		private void __do_in(IJsonRequestContext context) {
			// TODO Auto-generated method stub

		}

		private void __do_out(IJsonRequestContext context) {
			// TODO Auto-generated method stub

		}

		private void __do_up(IJsonRequestContext context) {
			// TODO Auto-generated method stub

		}

		private void __do_state(IJsonRequestContext context) {
			// TODO Auto-generated method stub

			IUser user = context.getUser();

		}

	}

}
