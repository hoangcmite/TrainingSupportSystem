/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.DAO;
import Model.UserAccount;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.VerifyRecaptcha;

/**
 *
 * @author Legon
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    DAO dao;
    int count = 0;

    public void init() {
        dao = new DAO();
    }

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        //request.setAttribute("flag", "1");
                request.getRequestDispatcher("register.jsp").forward(request, response);
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

        String fullname = "";
        String mail = "";

        fullname = (String) request.getParameter("fullname");
        mail = (String) request.getParameter("mail");

        String gRecaptchaResponse = request
                .getParameter("g-recaptcha-response");
        System.out.println(gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        String flag = "0";

        ArrayList<UserAccount> list = new ArrayList<UserAccount>();
        list = dao.takeinfor();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMail().equals(mail)) {
                flag = "2";

            }
        }

        if (verify && flag.equals("0")) {
            try {
               String a=  dao.register(fullname, mail);
                 dao.updateCurrent1(mail);
                 request.setAttribute("sad", a);
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("logintrue.jsp").forward(request, response);
        } else {
            if (verify == false) {
                if (mail.length() != 0) {
                    request.setAttribute("mail", mail);
                }
                if (fullname.length() != 0) {
                    request.setAttribute("fullname", fullname);
                }

                request.setAttribute("flag", "1");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if(flag.equals("2")){
                if (mail.length() != 0) {
                    request.setAttribute("mail", mail);
                }
                if (fullname.length() != 0) {
                    request.setAttribute("fullname", fullname);
                }

                request.setAttribute("flag", "2");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }

//        String user = request.getParameter("user");
//        String pwd = request.getParameter("pwd");
//
//        // get reCAPTCHA request param
//        // get servlet config init params
//        String userID = getServletConfig().getInitParameter("user");
//        String password = getServletConfig().getInitParameter("password");
//        // logging example
//        if (userID.equals(user) && password.equals(pwd) && verify) {
//            
//            response.sendRedirect("newjsp.jsp");
//        } else {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(
//                    "/index.html");
//            PrintWriter out = response.getWriter();
//            if (verify == false) {
//                out.println("<font color=red>verify is false</font>");
//            } else {
//                out.println("<font color=red>" + user + ": " + pwd + ": " + verify + "</font>");
//            }
//            rd.include(request, response);
//        }

//        String annount = "";
//        if (captcha.trim().equals(rcaptcha) == false) {
//
//            String flag = "1";
//            if (mail.length() != 0) {
//                request.setAttribute("mail", mail);
//            }
//            if (fullname.length() != 0) {
//                request.setAttribute("fullname", fullname);
//            }
//            if (captcha.length() != 0) {
//                request.setAttribute("captcha", captcha);
//            }
//          
//
//            request.setAttribute("flag", flag);
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//
//        } else {
//            String flag = "0";
//
//            ArrayList<UserAccount> list = new ArrayList<UserAccount>();
//            list = dao.takeinfor();
//            for (int i = 0; i < list.size(); i++) {
//                if (list.get(i).getMail().equals(mail)) {
//                    flag = "2";
//                     
//            if (mail.length() != 0) {
//                request.setAttribute("mail", mail);
//            }
//            if (fullname.length() != 0) {
//                request.setAttribute("fullname", fullname);
//            }
//            if (captcha.length() != 0) {
//                request.setAttribute("captcha", captcha);
//            }
//           
//
//            request.setAttribute("flag", flag);
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//                }
//            }
//
//            if (flag.equals("2") == false) {
//
//                try {
//                    annount = dao.register(fullname, mail);
//                } catch (Exception ex) {
//                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//
//        request.setAttribute("mail", mail);
//        request.setAttribute("fullname", fullname);
//        request.setAttribute("captcha", captcha);
//        request.setAttribute("rcaptcha", annount);
//
//        request.getRequestDispatcher("logintrue.jsp").forward(request, response);
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
