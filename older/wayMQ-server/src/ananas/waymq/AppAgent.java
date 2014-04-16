package ananas.waymq;

import javax.servlet.RequestDispatcher;

public class AppAgent {

	private RequestDispatcher _request_disp;

	public AppAgent() {
		this._request_disp = new JsonResponder();
	}

	public RequestDispatcher getRequestDispatcher() {
		return this._request_disp;
	}

}
