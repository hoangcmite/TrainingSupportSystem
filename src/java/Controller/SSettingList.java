/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.SubjectSetting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import utils.SubjectSettingDBContext;

/**
 *
 * @author dell
 */
public class SSettingList extends HttpServlet {
   
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
            out.println("<title>Servlet SSettingList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SSettingList at " + request.getContextPath () + "</h1>");
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
        SubjectSettingDBContext sets = new SubjectSettingDBContext();
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        ArrayList<SubjectSetting> lsubject = sets.getSubjectList();
        ArrayList<SubjectSetting> sslist = sets.getSubjectSettingList(index);
        
        request.setAttribute("lsubject", lsubject);
        request.setAttribute("slist", sslist);
        int count = sets.totalPage();
        int total = count / 4;
        if (total % 4 != 0) {
            total++;
        }
        
        request.setAttribute("endP", total);
        request.getRequestDispatcher("/view/manager/SubjectSettings.jsp").forward(request, response);
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
        SubjectSettingDBContext sets = new SubjectSettingDBContext();
        String raw_title = request.getParameter("title");
        String raw_status = request.getParameter("status");
        String raw_txt = request.getParameter("txt");
        
        Integer scode = (raw_title != null&&raw_title.length()>0)?new Integer(raw_title):null;
        Integer status = (raw_status != null&&raw_status.length()>0)?new Integer(raw_status):null;
        String titletxt = (raw_txt != null && raw_txt.length() > 0) ? raw_txt : null;
        
        
        ArrayList<SubjectSetting> lsubject = sets.getSubjectList();
        request.setAttribute("lsubject", lsubject);
        ArrayList<SubjectSetting> sfilter = sets.filter(scode, status, titletxt);
        request.setAttribute("slist", sfilter);
        request.getRequestDispatcher("/view/manager/SubjectSettings.jsp").forward(request, response);
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
