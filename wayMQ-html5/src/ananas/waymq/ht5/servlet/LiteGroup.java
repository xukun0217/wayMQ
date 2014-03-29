package ananas.waymq.ht5.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class LiteGroup
 */
@WebServlet("/LiteGroup/*")
public class LiteGroup extends HttpServlet {

	private static final long serialVersionUID = 1L;

	interface Param {
		String do_ = "do";
	}

	interface Result {
		String timestamp = "timestamp";
		String error = "error";
		String exception = "exception";
		String success = "success";
		String request = "request";
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LiteGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGetFinal(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doGetFinal(request, response);
	}

	private final void doGetFinal(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String do_ = request.getParameter(Param.do_);
		if (do_ == null) {
			RequestDispatcher disp = request
					.getRequestDispatcher("/lite-final.html");
			disp.forward(request, response);
		} else {
			JSONObject json = new JSONObject();
			try {
				this.echoRequestParam(request, json);
				this.responseJSON(do_, request, response, json);
			} catch (Exception e) {
				e.printStackTrace();
				String s = e.getMessage();
				if (s == null) {
					s = (e + "");
				}
				json.put(Result.exception, s);
			}
			{
				String err = json.getString(Result.error);
				String exp = json.getString(Result.exception);
				json.put(Result.success, (err == null && exp == null));
			}
			String str = JSON.toJSONString(json);
			response.setContentType("application/json");
			Writer wtr = new OutputStreamWriter(response.getOutputStream(),
					"UTF-8");
			wtr.write(str);
			wtr.flush();
		}

	}

	private void echoRequestParam(HttpServletRequest request, JSONObject json) {
		JSONObject req = new JSONObject();
		Enumeration<String> names = request.getParameterNames();
		for (; names.hasMoreElements();) {
			String name = names.nextElement();
			String val = request.getParameter(name);
			req.put(name, val);
		}
		json.put(Result.request, req);
	}

	private void responseJSON(String do_, HttpServletRequest request,
			HttpServletResponse response, JSONObject json) {

		Context context = new Context();
		context.request = request;
		context.response = response;
		context.store = _store;
		context.json = json;
		context.groupSpace = _store.getGroupSpace(request);

		json.put(Result.timestamp, System.currentTimeMillis());

		if (do_ == null) {
		} else if (do_.equals("createNewEvent")) {
			Func.createNewEvent(context);
		} else if (do_.equals("getGroupInfo")) {
			Func.getGroupInfo(context);
		} else if (do_.equals("getEventInfo")) {
			Func.getEventInfo(context);
		} else {
			json.put(Result.error, "no method(do) : '" + do_ + "'");
		}

	}

	static class Context {

		public JSONObject json;
		public Store store;
		public HttpServletRequest request;
		public HttpServletResponse response;
		public GroupSpace groupSpace;
	}

	static class Func {

		public static void getEventInfo(Context context) {
			// TODO Auto-generated method stub

		}

		public static void getGroupInfo(Context context) {
			// TODO Auto-generated method stub

			GroupSpace space = context.groupSpace;
			if (!space.exists()) {
				String s = "the group not exists: " + space._path;
				throw new RuntimeException(s);
			}

		}

		public static void createNewEvent(Context context) {
			// TODO Auto-generated method stub

		}
	}

	private final static Store _store;

	static {
		_store = new Store();
	}

	static class Store {

		final static String repo_conf = ".waymq";
		final static String repo_key = "repo";

		File getRepoPath() {
			try {
				URL loc = this.getClass().getProtectionDomain().getCodeSource()
						.getLocation();
				File p = new File(loc.toURI());
				for (; p != null; p = p.getParentFile()) {
					File file = new File(p, repo_conf);
					if (file.exists()) {
						p = file;
						break;
					}
				}
				if (p == null) {
					String s = "cannot find repo-conf file '.waymq' in the path ";
					throw new RuntimeException(s + loc);
				}
				InputStream in = new FileInputStream(p);
				Properties prop = new Properties();
				prop.load(in);
				in.close();
				String path = prop.getProperty(repo_key);
				if (path == null) {
					throw new RuntimeException("need property key: " + repo_key);
				}
				return new File(path);
			} catch (Exception e) {
				e.printStackTrace();
				return new File("/home/waymq");
			}
		}

		public GroupSpace getGroupSpace(HttpServletRequest request) {
			String uri = request.getRequestURI();
			// String url = request.getRequestURL().toString();
			// System.out.println("url = " + url);
			System.out.println("uri = " + uri);
			int i = uri.lastIndexOf('/');
			String name = uri.substring(i + 1);
			File repo = this.getRepoPath();
			File path = new File(repo, name);
			return new GroupSpace(path);
		}

	}

	static class GroupSpace {

		private final File _path;
		private final String _name;

		public GroupSpace(File path) {
			this._path = path;
			this._name = path.getName();
		}

		public boolean exists() {
			return this._path.exists();
		}
	}

}
