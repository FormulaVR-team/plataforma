package com.fvr._comun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Emilio Estecha 2013
 *
 */
public class UploadServlet extends HttpServlet {
    public static final long serialVersionUID = 1L; // Para evitar "warning: [serial] serializable class...""
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        new Upload().proceso(request,response,getServletContext().getRealPath("/importados"));
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet UploadServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Finalizado envio al servidor.</h3>");
	    out.println("<br/>");
	
	Enumeration<String> e = request.getParameterNames();
	while(e.hasMoreElements()) {
	    out.println("<br/>");
	    String n = (String) e.nextElement();
	    String v = request.getParameter(n);
	    out.println( n + "=" + v );
	}
        out.println("<br/>");
        out.println("<br/>");
        out.println("<input type=\"button\" value=\"Salir\" onclick=\"javascript:window.close();\">");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
