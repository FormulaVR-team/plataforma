package com.fvr._servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartUpServlet
 */
public class StartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartUpServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try { super.init(); } catch (ServletException ex) { ex.printStackTrace(); }
		System.out.println("**** StartUpServlet.init() >>>>>>");
		System.out.println("**** StartUpServlet.init() <<<<<<");
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		/////////////////////
		out.println("<html>");
		out.println("<head>");
		out.println("<title>StartUpServlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>StartUpServlet: este servlet se ejecutará solo durante el deploy de la aplicación...<br />SIEMPRE que no esté ya deployado en el server</h1>");
		out.println("</body>");
		out.println("</html>");
		/////////////////////

		out.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return super.getServletInfo();
	}

}
