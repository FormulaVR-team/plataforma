package com.fvr._servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fvr.FuentesDeDatos.BDConexion;
import com.fvr._comun.Subrutinas;
import com.fvr._comun._K;

/**
 * Servlet implementation class Off
 */
public class Off extends HttpServlet {

	private static final long serialVersionUID = 1595290979325559661L;

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {

		BDConexion dataBase = new Subrutinas().getBDConexion(request);

        request.getSession().invalidate();
        
        try {
//            PrintWriter out= response.getWriter();
//            response.setContentType("text/html");
//            HttpSession session= request.getSession();
//            session.invalidate();
//            out.println("<font color=red><br/><br/><b>You have been successfully logged out</b></font>");
//            out.println("<font color=green><br/><br/><b>Login again</b></font>");        


//        	getServletContext().getRequestDispatcher("/Logon.jsp").include(request, response);


			String link = Subrutinas.getDBValueFromKey(dataBase, _K.PA_KEY_URL_WEB_PAGE); 
			if ( link == null || link.trim().length() < 1 ) {
				link = Subrutinas.get_urlBase(request) + "/";
			}
			response.sendRedirect( link );	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

//        } catch (ServletException e1) {
//			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public Off() {super();}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	processRequest(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	processRequest(request, response);
    }

}
