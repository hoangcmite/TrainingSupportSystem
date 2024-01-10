/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Assignment;
import dal.AdminDAO;
import dal.AssignmentDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import utils.Assignment1;

/**
 *
 * @author asus
 */
@WebServlet(name = "AssignmentAdd", urlPatterns = {"/manager/AssignmentAdd"})
public class AssignmentAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String aid = request.getParameter("aid");
        AdminDAO adao = new AdminDAO();
        AssignmentDao dao = new AssignmentDao();
        Assignment1 a = adao.getAssignment(aid);
        ArrayList<Assignment> listSubject = dao.listSubject();
        request.setAttribute("listSubject", listSubject);
//        request.setAttribute("a", a);
        request.getRequestDispatcher("../view/admin/AssignmentAdd.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String status = request.getParameter("status");
        String isTeamWork = request.getParameter("isTeamWork");
        String isOngoing = request.getParameter("isOngoing");
        String eval = request.getParameter("eval");

        String subject = request.getParameter("subject");
        AssignmentDao dao = new AssignmentDao();
      dao.addAssignment(title, body, status, isTeamWork, isOngoing, subject, eval);
        response.sendRedirect("assignment");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
