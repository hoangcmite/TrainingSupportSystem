/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Assignment;
import Model.Setting;
import Model.UserAccount;
import dal.AssignmentDao;
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
 * @author Admin
 */
public class AssignmentController extends HttpServlet {
    
    AssignmentDao dao = new AssignmentDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String go = request.getParameter("go");
        HttpSession ses = request.getSession();
        UserAccount uac = (UserAccount) ses.getAttribute("user");
        int roleId = uac.getId();
        if (go == null) {
            go = "view";
        }
        if (go.equalsIgnoreCase("view")) {
            go = "view";
            int pageSize = 6;
            String page = request.getParameter("page");
            if (page == null || page.trim().length() == 0) {
                page = "1";
            }

            int pageIndex = Integer.parseInt(page);
            if (pageIndex < 1) {
                pageIndex = 1;
            }

            String raw_sort = request.getParameter("sort");
            String raw_subject = request.getParameter("subject");
            String raw_status = request.getParameter("status");
            String raw_title = request.getParameter("title");
            
            if (raw_sort == null || raw_sort.length() == 0) {
                raw_sort = "0";
            }
            if (raw_subject == null || raw_subject.length() == 0) {
                raw_subject = "-1";
            }
            if (raw_status == null || raw_status.length() == 0) {
                raw_status = "-1";
            }
            if (raw_title == null || raw_title.trim().length() == 0) {
                raw_title = null;
            }

            int sort = Integer.parseInt(raw_sort);
            int subjectId = Integer.parseInt(raw_subject);
            int status = Integer.parseInt(raw_status);
            String title = raw_title;

            int sortId = 0; // 1
            int sortSubject = 2; // 3 
            int sortTitle = 4; // 5
            int sortEvalWeight = 6; // 7
            int sortTeamWork = 8; // 9
            int sortOnGoing = 10; // 11
            int sortStatus = 12; // 13

            switch (sort) {
                case 0:
                    sortId = 1;
                    break;
                case 1:
                    sortId = 0;
                    break;
                case 2:
                    sortSubject = 3;
                    break;
                case 3:
                    sortSubject = 2;
                    break;
                case 4:
                    sortTitle = 5;
                    break;
                case 5:
                    sortTitle = 4;
                    break;
                case 6:
                    sortEvalWeight = 7;
                    break;
                case 7:
                    sortEvalWeight = 6;
                    break;
                case 8:
                    sortTeamWork = 9;
                    break;
                case 9:
                    sortTeamWork = 8;
                    break;
                case 10:
                    sortOnGoing = 11;
                    break;
                case 11:
                    sortOnGoing = 10;
                    break;
                case 12:
                    sortStatus = 13;
                    break;
                case 13:
                    sortStatus = 12;
                    break;
                default:
                    break;
            }

            // Set ngược lại cho function js để lần sau bấm sort 
            request.setAttribute("sortId", sortId);
            request.setAttribute("sortSubject", sortSubject);
            request.setAttribute("sortTitle", sortTitle);
            request.setAttribute("sortEvalWeight", sortEvalWeight);
            request.setAttribute("sortTeamWork", sortTeamWork);
            request.setAttribute("sortOnGoing", sortOnGoing);
            request.setAttribute("sortStatus", sortStatus);

            //lấy ra
            
            ArrayList<Assignment> listAss = dao.advancedSearchAss(sort, subjectId, status, title, pageIndex, pageSize);
            ArrayList<Assignment> listSubject = dao.listSubject();
            int count = dao.countAdvancedSearchAss(sort, subjectId, status, title);
            int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("listAss", listAss);
            request.setAttribute("listSubject", listSubject);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            // sau khi đã search thì vẫn select những dữ liệu mà mình đã chọn search
            request.setAttribute("sort", sort);
            request.setAttribute("subjectId", subjectId);
            request.setAttribute("status", status);
            request.setAttribute("title", title);
            // lấy Url để phân trang bằng js 
            String url = "assignment?";
            String url_param = request.getQueryString();
            response.getWriter().print(url_param);
            if (url_param != null && url_param.length() > 0) {
                if (url_param.endsWith("page=" + pageIndex)) {
                    url_param = url_param.replaceAll("page=" + pageIndex, "");
                }
                // nếu nó không rời vào trường hợp book?page=x và thiếu & thì thêm vào
                if (!url_param.equals("") && !url_param.endsWith("&")) {
                    url_param += "&";
                }
                url += (url_param);
            }
            request.setAttribute("url", url);
            request.getRequestDispatcher("../view/manager/AssignmentList.jsp").forward(request, response);
        } else if (go.equalsIgnoreCase("active")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.active(id);           
            response.sendRedirect("assignment");
        } else if (go.equalsIgnoreCase("inactive")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.inactive(id);
            response.sendRedirect("assignment");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
