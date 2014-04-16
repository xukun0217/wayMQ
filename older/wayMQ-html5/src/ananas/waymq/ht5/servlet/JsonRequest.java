package ananas.waymq.ht5.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class JsonRequest
 */
@WebServlet("/json_request")
public class JsonRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JsonRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JSONObject json = new JSONObject();
		JSONObject req = new JSONObject();
		{
			Enumeration<String> attrs = request.getAttributeNames();
			for (; attrs.hasMoreElements();) {
				String key = attrs.nextElement();
				String val = request.getAttribute(key).toString();
				req.put(key, val);
			}
		}
		json.put("request", req);
		String str = JSON.toJSONString(json, true);
		response.getOutputStream().write(str.getBytes("UTF-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
