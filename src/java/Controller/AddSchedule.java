/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Schedule;
import Model.SubjectSetting;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import utils.ScheduleDBContext;
import utils.SubjectSettingDBContext;

/**
 *
 * @author dell
 */
public class AddSchedule extends HttpServlet {
   
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
            out.println("<title>Servlet AddSchedule</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSchedule at " + request.getContextPath () + "</h1>");
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
        ScheduleDBContext schedule = new ScheduleDBContext();
        SubjectSettingDBContext subjects = new SubjectSettingDBContext();
        
        ArrayList<Schedule> cl = schedule.getClassList();
        ArrayList<SubjectSetting> lsubject = subjects.getSubjectList();
        ArrayList<Schedule> rl = schedule.getRoomList();
        request.setAttribute("clist", cl);
        request.setAttribute("lsubject", lsubject);
        request.setAttribute("rl", rl);
        
        request.getRequestDispatcher("/view/supporter/AddSchedule.jsp").forward(request, response);
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
        ScheduleDBContext schedule = new ScheduleDBContext();
        
        int sid = Integer.parseInt(request.getParameter("sid"));
        int cid = Integer.parseInt(request.getParameter("cid"));
        Date dateStudy = Date.valueOf(request.getParameter("dateStudy"));
        int slot = Integer.parseInt(request.getParameter("slot"));
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String room = request.getParameter("room");
        
        Schedule s = new Schedule();
        s.setSlot(slot);
        s.setSubjectId(sid);
        s.setClassId(cid);
        s.setDates(dateStudy);
        s.setFrom(from);
        s.setTo(to);
        s.setRoom(room);
        
        schedule.insertClass(s);
        request.setAttribute("mess", "Create successfully! ");
        request.getRequestDispatcher("/view/supporter/AddSchedule.jsp").forward(request, response);
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
