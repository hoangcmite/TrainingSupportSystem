/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.AdminDAO;
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
 * @author Admin
 */
@WebServlet(name = "AddEval", urlPatterns = {"/admin/AddEval"})
public class AddEval extends HttpServlet {

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
        AdminDAO adao = new AdminDAO();
        ArrayList<Assignment1> alist = adao.getAllAssignment();
        request.setAttribute("alist", alist);
        request.getRequestDispatcher("../view/admin/AddEval.jsp").forward(request, response);

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
        String name = request.getParameter("name").trim();
        String assId = request.getParameter("assId").trim();
        String evalWeight = request.getParameter("evalWeight").trim();
        String maxLoc = request.getParameter("maxLoc");
        String isTeamEval = request.getParameter("isTeamEval").trim();
        String status = request.getParameter("status").trim();
        String description = request.getParameter("description").trim();
        AdminDAO adao = new AdminDAO();
        response.getWriter().println(name);
        response.getWriter().println(assId);
        response.getWriter().println(evalWeight);
        response.getWriter().println(maxLoc);
        response.getWriter().println(isTeamEval);
        response.getWriter().println(status);
        response.getWriter().println(description);
        adao.addEval(name, assId, evalWeight, maxLoc, isTeamEval, status, description);
//        processRequest(request, response);
        response.sendRedirect("./EvalList");
        processRequest(request, response);
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
