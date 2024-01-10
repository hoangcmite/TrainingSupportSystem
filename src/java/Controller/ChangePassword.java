/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.User;

/**
 *
 * @author ADMIN
 */
public class ChangePassword extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
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
        
      request.getRequestDispatcher("view/auth/ChangePassword.jsp").forward(request, response);
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
        String email = request.getParameter("mail");
        String curPass = request.getParameter("curPass");
        String newPass = request.getParameter("newPass");
        String verPass = request.getParameter("verPass");
        UserDBContext db = new UserDBContext();
        User user= db.getUser(email,curPass);
        if(user==null)
        {request.setAttribute("denied", "Wrong password");
        request.getRequestDispatcher("view/auth/ChangePassword.jsp").forward(request, response);
        }
          
        else {           
             if(newPass.equals(verPass)){
                 db.update(newPass,user.getEmail());
                 request.setAttribute("denied", "Update successfully");
                 request.getRequestDispatcher("view/auth/ChangePassword.jsp").forward(request, response);
             }
             else{
                 request.setAttribute("denied", "Verifying password did not match new password");
                 request.getRequestDispatcher("view/auth/ChangePassword.jsp").forward(request, response);
             }
        }
    }

  
}
