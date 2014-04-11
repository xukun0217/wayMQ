package ananas.waymq.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ananas.sf4lib.webserver.ServiceAgent;
import ananas.waymq.web.WaymqWeb;

/**
 * Servlet implementation class Event
 */
@WebServlet("/event")
public class DoEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RequestDispatcher _disp;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoEvent() {
		super();
		ServiceAgent agent = WaymqWeb.getAgent();
		String path = "page-event.html";
		this._disp = agent.getRequestDispatcher(path);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		_disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		_disp.forward(request, response);
	}

}
