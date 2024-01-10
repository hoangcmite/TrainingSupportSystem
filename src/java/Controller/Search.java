/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import utils.SubjectDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import Model.Subject;
import Model.Account;
import dal.UserDBContext1;

/**
 *
 * @author dell
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

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
        String search = request.getParameter("txt");
        String managerName = request.getParameter("Manager");
        String expertName = request.getParameter("expert");
        String status = request.getParameter("status");

        Integer mid = (managerName != null && managerName.length() > 0) ? new Integer(managerName) : null;
        Integer eid = (expertName != null && expertName.length() > 0) ? new Integer(expertName) : null;
        Integer valueStatus = (status != null && status.length() > 0) ? new Integer(status) : null;
        String searchtxt = (search != null && search.length() > 0) ? search : null;

        UserDBContext1 user = new UserDBContext1();
        SubjectDBContext subject = new SubjectDBContext();

        ArrayList<Account> lu = user.getManagerList();
        ArrayList<Account> lm = user.getExpertList();
        ArrayList<Subject> ls = subject.getStatusList();

        ArrayList<Subject> listSubject = subject.Filter(mid, eid, valueStatus, searchtxt);
        request.setAttribute("listSubjects", listSubject);
        request.setAttribute("size", listSubject.size() + "");
        request.setAttribute("user", lu);
        request.setAttribute("manager", lm);
        request.setAttribute("status", ls);

        

        request.setAttribute("search", searchtxt + " ");
        request.getRequestDispatcher("/view/manager/SearchList.jsp").forward(request, response);
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
