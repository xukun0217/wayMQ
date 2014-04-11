package ananas.waymq.ht5.litegroup;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class LiteGroup2
 */
@WebServlet("/LiteGroup2/*")
public class LiteGroup2Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LiteGroup2Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String path1 = request.getContextPath();
		String pathf = request.getRequestURI();
		String path2 = pathf.substring(path1.length());
		String[] array = path2.split("/");
		array = this.normalStringArray(array);

		RequestContext rc = new RequestContext(request, response);
		rc._do = request.getParameter("do");
		rc._target_path = path2;
		rc._repo = LG2Repo.Factory.getInstance();

		switch (array.length) {
		case 1: {
			this.procAsSystem(rc);
			break;
		}
		case 2: {
			this.procAsGroup(rc);
			break;
		}
		case 3: {
			this.procAsEvent(rc);
			break;
		}
		default:
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private void procAsEvent(RequestContext rc) throws ServletException,
			IOException {
		if (rc._do == null) {
			// html
			String path = "/lg2/event.html";
			rc._request.getRequestDispatcher(path).forward(rc._request,
					rc._response);
		} else {
			// json
			JSONResponder resp = JSONResponderForEvent.getInstance();
			this.processJSON(rc, resp);
		}
	}

	private void processJSON(RequestContext rc, JSONResponder resp)
			throws IOException {

		JSONObject json = rc._json;
		try {
			resp.process(rc);
		} catch (Exception e) {
			e.printStackTrace();
			String str = e + "";
			json.put("exception", str);
		}
		String err = json.getString("error");
		String exp = json.getString("exception");
		json.put("success", (err == null && exp == null));
		String str = JSON.toJSONString(json, true);
		rc._response.setContentType("application/json");
		rc._response.setCharacterEncoding("UTF-8");
		OutputStream out = rc._response.getOutputStream();
		Writer wtr = new OutputStreamWriter(out, "UTF-8");
		wtr.write(str);
		wtr.flush();
		System.out.println(str);
	}

	private void procAsSystem(RequestContext rc) throws ServletException,
			IOException {
		if (rc._do == null) {
			// html
			String path = "/lg2/system.html";
			rc._request.getRequestDispatcher(path).forward(rc._request,
					rc._response);
		} else {
			// json
			JSONResponder resp = JSONResponderForSystem.getInstance();
			this.processJSON(rc, resp);
		}
	}

	private void procAsGroup(RequestContext rc) throws ServletException,
			IOException {
		if (rc._do == null) {
			// html
			String path = "/lg2/group.html";
			rc._request.getRequestDispatcher(path).forward(rc._request,
					rc._response);
		} else {
			// json
			JSONResponder resp = JSONResponderForGroup.getInstance();
			this.processJSON(rc, resp);
		}
	}

	private String[] normalStringArray(String[] array) {
		List<String> list = new ArrayList<String>();
		for (String s : array) {
			if (s.length() > 0) {
				list.add(s);
			}
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);

	}

}
