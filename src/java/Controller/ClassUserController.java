/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.ClassSetting;
import Model.ClassUser;
import Model.UserAccount;
import dal.ClassUserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ClassUserController extends HttpServlet {
   
    ClassUserDao dao = new ClassUserDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String go = request.getParameter("go");
        HttpSession ses = request.getSession();
        UserAccount uac = (UserAccount) ses.getAttribute("user");
        int id = uac.getId();
        //int roleId = Integer.parseInt(request.getParameter("roleId"));
        int roleId = id;
        //int roleId = 95;
        ArrayList<ClassUser> classList = dao.listClassByRole(roleId);
        response.getWriter().print(classList.size()+"");
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
            String raw_class = request.getParameter("class_id");
            
            String raw_sort = request.getParameter("sort");
            String raw_status = request.getParameter("status");
            String raw_nameUser = request.getParameter("nameUser");
            
            if (raw_class == null || raw_class.length() == 0) {
                raw_class = Integer.toString(classList.get(0).getClass_id());
            }
            
            if (raw_sort == null || raw_sort.length() == 0) {
                raw_sort = "0";
            }
            if (raw_status == null || raw_status.length() == 0) {
                raw_status = "-1";
            }
            if (raw_nameUser == null || raw_nameUser.trim().length() == 0) {
                raw_nameUser = null;
            }
            
            
            int classId = Integer.parseInt(raw_class);
            int sort = Integer.parseInt(raw_sort);
            int status = Integer.parseInt(raw_status);
            String nameU = raw_nameUser;

            int sortFullName = 0; // 1
            int sortEmail = 2; // 3 
            int sortStatus = 4; // 5
            int sortOutDate = 6; // 7

            switch (sort) {
                case 0:
                    sortFullName = 1;
                    break;
                case 1:
                    sortFullName= 0;
                    break;
                case 2:
                    sortEmail = 3;
                    break;
                case 3:
                    sortEmail = 2;
                    break;
                case 4:
                    sortStatus = 5;
                    break;
                case 5:
                    sortStatus = 4;
                    break;
                case 6:
                    sortOutDate = 7;
                    break;
                case 7:
                    sortOutDate = 6;
                    break;
                default:
                    break;
            }

            // Set ngược lại cho function js để lần sau bấm sort 
            request.setAttribute("sortFullName", sortFullName);
            request.setAttribute("sortEmail", sortEmail);
            request.setAttribute("sortStatus", sortStatus);
            request.setAttribute("sortOutDate", sortOutDate);

            //lấy ra
            ArrayList<ClassUser> listTrainee = dao.listTraineeOfClass(sort, roleId, classId, status, nameU, pageIndex, pageSize);
            
            int count = dao.countListTraineeOfClass(sort, roleId, classId, status, nameU);
            int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("listTrainee", listTrainee);
            request.setAttribute("classList", classList);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            // sau khi đã search thì vẫn select những dữ liệu mà mình đã chọn search
            request.setAttribute("classId", classId);
            request.setAttribute("roleID", roleId);
            request.setAttribute("status", status);
            request.setAttribute("nameU", nameU);
            request.setAttribute("sort", sort);
            // lấy Url để phân trang bằng js 
            String url = "ClassUser?";
            String url_param = request.getQueryString();
            response.getWriter().print(url_param);
            //response.getWriter().print(listTrainee.size()+" "+count);
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
            
            request.getRequestDispatcher("../view/supporter/TraineeList.jsp").forward(request, response);
        } else if (go.equalsIgnoreCase("active")) {
            int uID = Integer.parseInt(request.getParameter("id"));
            int cID = Integer.parseInt(request.getParameter("class_id"));
            int role = Integer.parseInt(request.getParameter("roleId"));
            dao.active(uID, cID);           
            response.sendRedirect("ClassUser?class_id="+cID+"&roleId="+role);
        } else if (go.equalsIgnoreCase("inactive")) {
            int uID = Integer.parseInt(request.getParameter("id"));
            int cID = Integer.parseInt(request.getParameter("class_id"));
            int role = Integer.parseInt(request.getParameter("roleId"));
            dao.inactive(uID, cID);
            response.sendRedirect("ClassUser?class_id="+cID+"&roleId="+role);
        }else if(go.equalsIgnoreCase("detail")){
            String raw_id = request.getParameter("id");
            int cID = Integer.parseInt(request.getParameter("class_id"));
            int role = Integer.parseInt(request.getParameter("roleId"));
            int uID = -1;
            if (raw_id != null && raw_id.trim().length() > 0) {
                uID = Integer.parseInt(raw_id);
            }
            
            ClassUser traineeDetail = dao.getTraineeDetail(cID, uID);
            request.setAttribute("roleID", role);
            request.setAttribute("classId", cID);
            request.setAttribute("traineeDetail", traineeDetail);
            request.getRequestDispatcher("../view/supporter/TraineeDetails.jsp").forward(request, response);
        }else if (go.equalsIgnoreCase("update")) {
            LocalDate date2 = LocalDate.now();
            
            String raw_id = request.getParameter("user_id");
            int cID = Integer.parseInt(request.getParameter("class_id"));
            int uID = -1;
            if (raw_id != null && raw_id.trim().length() > 0) {
                uID = Integer.parseInt(raw_id);
            }
            int role = Integer.parseInt(request.getParameter("roleId"));
            ClassUser cu = new ClassUser();
            boolean status = request.getParameter("status").equals("1");
            String raw_date = request.getParameter("dropout");
            String note = request.getParameter("note");
            
            Date date = null;
            if (raw_date == null || raw_date.trim().length() == 0) {
                date = Date.valueOf(date2);
            }else{
                
                date = Date.valueOf(raw_date);
            }
            
            cu.setStatus(status);
            cu.setDropout_date(date);
            cu.setNote(note);
            
            dao.updateTrainee(cID, uID, cu);
            response.sendRedirect("ClassUser?class_id="+cID+"&roleId="+role);
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
