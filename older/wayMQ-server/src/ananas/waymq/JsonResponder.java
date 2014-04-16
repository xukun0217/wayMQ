package ananas.waymq;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonResponder implements RequestDispatcher {

	@Override
	public void forward(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		JSONObject req = new JSONObject();

		Enumeration<String> keys = request.getParameterNames();
		for (; keys.hasMoreElements();) {
			String key = keys.nextElement();
			String val = request.getParameter(key);
			req.put(key, val);
		}

		response.setContentType("application/json");

		JSONObject json = new JSONObject();

		try {
			this.procJSON(req, json);
		} catch (Exception e) {
			e.printStackTrace();
			String str = e.getMessage();
			if (str == null) {
				str = e.toString();
			}
			json.put(Protocol.Resp.exception, str);
		}

		String error = json.getString(Protocol.Resp.error);
		String exception = json.getString(Protocol.Resp.exception);
		json.put(Protocol.Resp.success, (error == null && exception == null));

		json.put("request", req);

		ServletOutputStream out = response.getOutputStream();
		Writer wtr = new OutputStreamWriter(out, "UTF-8");
		wtr.append(JSON.toJSONString(json, true));
		wtr.close();
		out.close();
	}

	private void procJSON(JSONObject request, JSONObject response) {

		DefaultResponseContext rc2 = new DefaultResponseContext(request,
				response);
		ResponseContext rc = rc2;
		final String class_ = rc.getParameter(Protocol.Attr.class_);
		final String method_ = rc.getParameter(Protocol.Attr.method_);
		final String this_ = rc.getParameter(Protocol.Attr.this_, null);
		final String token_ = rc.getParameter(Protocol.Attr.token_, null);
		rc2.setTarget(class_, method_, this_, token_);

		JsonResponderClass cls = rc.getApp().getClassRegistrar()
				.getClass(class_);
		if (cls == null) {
			String msg = "cannot find responder for class: ";
			throw new RuntimeException(msg + class_);
		}
		cls.invoke(rc);
	}

	@Override
	public void include(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		throw new RuntimeException("no impl");
	}

}
