package ananas.waymq.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ananas.waymq.AppAgent;

/**
 * Servlet implementation class Endpoint
 */
@WebServlet("/Endpoint")
public class Endpoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppAgent _agent;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Endpoint() {
		super();
		this._agent = new AppAgent();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher disp = this._agent.getRequestDispatcher();
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher disp = this._agent.getRequestDispatcher();
		disp.forward(request, response);
	}

}
