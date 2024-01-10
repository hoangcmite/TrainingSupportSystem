/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Classes;
import Model.Team;
import Model.UserAccount;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Legon
 */
@WebServlet(name = "TeamList", urlPatterns = {"/TeamList"})
public class TeamList extends HttpServlet {

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
            out.println("<title>Servlet TeamList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeamList at " + request.getContextPath() + "</h1>");
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
        HttpSession ses = request.getSession();
        UserAccount uac = (UserAccount) ses.getAttribute("user");
        int id = uac.getId();
        ArrayList<Classes> lstcl = new ArrayList<Classes>();
        lstcl = dao.getClassList(id);
        //lay classid neu lan dau thi mac dinh la lay cai dau tien
        int classid = 0;
        if(request.getParameter("teamID") != null){
            
            int teamid= Integer.parseInt(request.getParameter("teamID"));
            if(request.getParameter("joinerID") != null){
               int joind = Integer.parseInt(request.getParameter("joinerID"));
               dao.addMembertoTeam(teamid, joind);
            }
            if(request.getParameter("memberID") != null){
               int mid = Integer.parseInt(request.getParameter("memberID"));
               dao.deleteMember(teamid, mid);
            }
            if(request.getParameter("leaderID") != null){
               int leaderID = Integer.parseInt(request.getParameter("leaderID"));
               dao.updateLeader(teamid, leaderID);
            }  
            if(request.getParameter("doa") != null){
            int doa = Integer.parseInt(request.getParameter("doa"));
            dao.updateteam(teamid, doa);}
            
            if (request.getParameter("classid") != null) {
            classid = Integer.parseInt(request.getParameter("classid"));
            request.setAttribute("classidselect", classid);

        } else {
            if(lstcl.size()>0){
            
            classid = lstcl.get(0).getClassId();
            request.setAttribute("classidselect", classid);
        }}
        request.setAttribute("classes", lstcl);

        
      int[] msid = dao.getMilestoneList(classid);
        int msids =0;
        if (request.getParameter("milestoneid") != null) {
            msids = Integer.parseInt(request.getParameter("milestoneid"));
            request.setAttribute("milestoneidselect", msids);

        } else {
          
            if(msid.length>0){
            msids = msid[0];
              request.setAttribute("milestoneidselect", msids);
        }}
        request.setAttribute("milestone", msid);
        int status=1;
         if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
           

        }request.setAttribute("status", status);
         int[] tid = dao.getTeamList(classid, msids,status);
           ArrayList<Team> lstteam = new ArrayList<Team>();
         for(int j=0;j<tid.length;j++){
              int ti = tid[j];
              if(ti!=0)
              lstteam.add(dao.getTeamName(ti));
         }
         
         request.setAttribute("lstteam", lstteam);
        }else{
        if (request.getParameter("classid") != null) {
            classid = Integer.parseInt(request.getParameter("classid"));
            request.setAttribute("classidselect", classid);

        } else {
            if(lstcl.size()>0){
            
            classid = lstcl.get(0).getClassId();
            request.setAttribute("classidselect", classid);
        }}
        request.setAttribute("classes", lstcl);

        
      int[] msid = dao.getMilestoneList(classid);
        int msids =0;
        if (request.getParameter("milestoneid") != null) {
            msids = Integer.parseInt(request.getParameter("milestoneid"));
            request.setAttribute("milestoneidselect", msids);

        } else {
          
            if(msid.length>0){
            msids = msid[0];
              request.setAttribute("milestoneidselect", msids);
        }}
        request.setAttribute("milestone", msid);
        int status=1;
         if (request.getParameter("status") != null) {
            status = Integer.parseInt(request.getParameter("status"));
           

        }request.setAttribute("status", status);
         int[] tid = dao.getTeamList(classid, msids,status);
           ArrayList<Team> lstteam = new ArrayList<Team>();
         for(int j=0;j<tid.length;j++){
              int ti = tid[j];
              if(ti!=0)
              lstteam.add(dao.getTeamName(ti));
         }
         
         request.setAttribute("lstteam", lstteam);
        }
         
         
           
        
        

        //dung classid de lay id cua milestone
        request.getRequestDispatcher("/view/trainer/TeamList.jsp").forward(request, response);
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
