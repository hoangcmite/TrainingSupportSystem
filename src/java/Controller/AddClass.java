/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Account;
import Model.Classes;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import utils.ClassDBContext;

/**
 *
 * @author dell
 */
public class AddClass extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddClass</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddClass at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        ClassDBContext classes = new ClassDBContext();
        ArrayList<Account> ltrainer = classes.getTrainerList();
        ArrayList<Classes> lterm = classes.getTermList();
        ArrayList<Classes> lcombo = classes.getComboList();
        request.setAttribute("trainer", ltrainer);
        request.setAttribute("lterm", lterm);
        request.setAttribute("lcombo", lcombo);
        request.getRequestDispatcher("/view/manager/CreateNewClass.jsp").forward(request, response);
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
        ClassDBContext classes = new ClassDBContext();

        String classCode = request.getParameter("ccode");
        int combo = Integer.parseInt(request.getParameter("cid"));
        int trainerid = Integer.parseInt(request.getParameter("tid"));
        int status = Integer.parseInt(request.getParameter("status"));
        int term = Integer.parseInt(request.getParameter("term"));
        String des = request.getParameter("description");

        Classes c = new Classes();
        c.setClassCode(classCode);
        c.setComboId(combo);
        c.setTrainerId(trainerid);
        c.setStatus(status);
        c.setTermId(term);
        c.setDescription(des);
        classes.insertClass(c);

        request.setAttribute("mess", "Update successfully! ");
        request.getRequestDispatcher("/view/manager/CreateNewClass.jsp").forward(request, response);

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
