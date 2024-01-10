/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import utils.WebContactDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class WebContactDetail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WebContactDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WebContactDetail at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         ArrayList<Model.WebContact> webcs = new ArrayList<>();
        WebContactDBContext db = new WebContactDBContext();
       String page = request.getParameter("page");
        if(page == null)
            page = "1";
        int count = db.getTotalWebContact();
        int endPage = count/5;
        if(count%5 != 0)
            endPage++;
        request.setAttribute("endP", endPage);
        webcs = db.getWebContact(Integer.parseInt(page)-1);
        request.setAttribute("webcs", webcs);
        request.getRequestDispatcher("view/supporter/WebContactDetail.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         ArrayList<Model.WebContact> webcs = new ArrayList<>();
         String[] ids =request.getParameterValues("id") ;
        String[] res = request.getParameterValues("response");
        int userID =Integer.parseInt(request.getParameter("name")) ;
        WebContactDBContext db = new WebContactDBContext();
        db.updateWebContact( userID, ids, res);
         for(int i = 0; i< ids.length ; i++){
             System.out.println(ids[i]);
             System.out.println(res[i]);
         }
           String page = request.getParameter("page");
        if(page == null)
            page = "1";
        int count = db.getTotalWebContact();
        int endPage = count/5;
        if(count%5 != 0)
            endPage++;
        request.setAttribute("endP", endPage);
        webcs = db.getWebContact(Integer.parseInt(page)-1);
        request.setAttribute("webcs", webcs);
        request.setAttribute("message", "Response save successfully" );
        request.getRequestDispatcher("view/supporter/WebContactDetail.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
