/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.ClassSetting;
import Model.User;
import dal.ClassSettingDao;
import dal.UserListDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserListController extends HttpServlet {
   
    UserListDao dao = new UserListDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String go = request.getParameter("go");

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
            String raw_status = request.getParameter("status");
            String raw_name_email = request.getParameter("name_email");
            
            if (raw_sort == null || raw_sort.length() == 0) {
                raw_sort = "0";
            }
            if (raw_status == null || raw_status.length() == 0) {
                raw_status = "-1";
            }
            if (raw_name_email == null || raw_name_email.trim().length() == 0) {
                raw_name_email = null;
            }
                      
            
            
            int sort = Integer.parseInt(raw_sort);
            int status = Integer.parseInt(raw_status);
            String name_email = raw_name_email;

            int sortId = 0; // 1
            int sortName = 2; // 3 
            int sortEmail = 4; // 5
            int sortMobile = 6; // 7

            switch (sort) {
                case 0:
                    sortId = 1;
                    break;
                case 1:
                    sortId = 0;
                    break;
                case 2:
                    sortName = 3;
                    break;
                case 3:
                    sortName = 2;
                    break;
                case 4:
                    sortEmail = 5;
                    break;
                case 5:
                    sortEmail = 4;
                    break;
                case 6:
                    sortMobile= 7;
                    break;
                case 7:
                    sortMobile = 6;
                    break;
                default:
                    break;
            }

            // Set ngược lại cho function js để lần sau bấm sort 
            request.setAttribute("sortId", sortId);
            request.setAttribute("sortName", sortName);
            request.setAttribute("sortEmail", sortEmail);
            request.setAttribute("sortMobile", sortMobile);

            //lấy ra
            
            ArrayList<User> listUser = dao.advancedSearchUser(sort, status, name_email, pageIndex, pageSize);
            int count = dao.countAdvancedSearchUser(sort, status, name_email);
            int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("listUser", listUser);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            // sau khi đã search thì vẫn select những dữ liệu mà mình đã chọn search
            request.setAttribute("sort", sort);
            request.setAttribute("status", status);
            request.setAttribute("name_email", name_email);
            // lấy Url để phân trang bằng js 
            String url = "UserList?";
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
            
            request.getRequestDispatcher("../view/admin/UserList.jsp").forward(request, response);
        } 
        else if (go.equalsIgnoreCase("active")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.active(id);           
            response.sendRedirect("UserList");
        } else if (go.equalsIgnoreCase("inactive")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.inactive(id);
            response.sendRedirect("UserList");
        }
        else if (go.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteUser(id);
            response.sendRedirect("UserList");
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
