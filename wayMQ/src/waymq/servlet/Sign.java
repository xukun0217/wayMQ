package waymq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import waymq.DefaultRespDriver;
import waymq.IJsonRequestContext;
import waymq.IJsonResponder;
import waymq.IJsonResponderDriver;

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

		_driver.setNeedLogin(true);
		_driver.setNeedAdmin(true);

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

			return context.getResponse();
		}

	}

}
