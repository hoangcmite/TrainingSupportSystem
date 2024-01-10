/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.SettingDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import Model.Setting;
import Model.UserAccount;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class SettingController extends HttpServlet {

    SettingDao dao = new SettingDao();
    ArrayList<Setting> listType = dao.listType();

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
            String raw_type = request.getParameter("type");
            String raw_status = request.getParameter("status");
            String raw_title = request.getParameter("title");
            
            if (raw_sort == null || raw_sort.length() == 0) {
                raw_sort = "0";
            }
            if (raw_type == null || raw_type.length() == 0) {
                raw_type = "-1";
            }
            if (raw_status == null || raw_status.length() == 0) {
                raw_status = "-1";
            }
            if (raw_title == null || raw_title.trim().length() == 0) {
                raw_title = null;
            }

            int sort = Integer.parseInt(raw_sort);
            int typeId = Integer.parseInt(raw_type);
            int status = Integer.parseInt(raw_status);
            String title = raw_title;

            int sortId = 0; // 1
            int sortType = 2; // 3 
            int sortTitle = 4; // 5
            int sortValue = 6; // 7
            int sortOrder = 8; // 9
            int sortStatus = 10; // 11

            switch (sort) {
                case 0:
                    sortId = 1;
                    break;
                case 1:
                    sortId = 0;
                    break;
                case 2:
                    sortType = 3;
                    break;
                case 3:
                    sortType = 2;
                    break;
                case 4:
                    sortTitle = 5;
                    break;
                case 5:
                    sortTitle = 4;
                    break;
                case 6:
                    sortValue = 7;
                    break;
                case 7:
                    sortValue = 6;
                    break;
                case 8:
                    sortOrder = 9;
                    break;
                case 9:
                    sortOrder = 8;
                    break;
                case 10:
                    sortStatus = 11;
                    break;
                case 11:
                    sortStatus = 10;
                    break;
                default:
                    break;
            }

            // Set ngược lại cho function js để lần sau bấm sort 
            request.setAttribute("sortId", sortId);
            request.setAttribute("sortType", sortType);
            request.setAttribute("sortTitle", sortTitle);
            request.setAttribute("sortValue", sortValue);
            request.setAttribute("sortOrder", sortOrder);
            request.setAttribute("sortStatus", sortStatus);

            //lấy ra
            
            ArrayList<Setting> listSetting = dao.advancedSearchSetting(sort, typeId, status, title, pageIndex, pageSize);
            
            int count = dao.countAdvancedSearchSetting(sort, typeId, status, title);
            int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("listSetting", listSetting);
            request.setAttribute("listType", listType);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            // sau khi đã search thì vẫn select những dữ liệu mà mình đã chọn search
            request.setAttribute("sort", sort);
            request.setAttribute("typeId", typeId);
            request.setAttribute("status", status);
            request.setAttribute("title", title);
            // lấy Url để phân trang bằng js 
            String url = "setting?";
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
            request.getRequestDispatcher("../view/admin/SettingList.jsp").forward(request, response);
        } else if (go.equalsIgnoreCase("active")) {
            int id = Integer.parseInt(request.getParameter("id"));
//            String type = request.getParameter("type");
//            String status = request.getParameter("status");
//            String title = request.getParameter("title");
//            String page = request.getParameter("page");
            dao.active(id);           
            response.sendRedirect("setting");
        } else if (go.equalsIgnoreCase("inactive")) {
            int id = Integer.parseInt(request.getParameter("id"));
//            String type = request.getParameter("type");
//            String status = request.getParameter("status");
//            String title = request.getParameter("title");
//            String page = request.getParameter("page");
            dao.inactive(id);
            response.sendRedirect("setting");
        }else if(go.equalsIgnoreCase("detail")){
            String raw_id = request.getParameter("id");
            int id = -1;
            if (raw_id != null && raw_id.trim().length() > 0) {
                id = Integer.parseInt(raw_id);
            }
            Setting settingDetails = dao.getSettingById(id);
            request.setAttribute("listType", listType);
            request.setAttribute("settingDetails", settingDetails);
            request.getRequestDispatcher("../view/admin/SettingDetails.jsp").forward(request, response);
        }else if (go.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int n = dao.deleteSetting(id);
            response.sendRedirect("setting");
        }else if (go.equalsIgnoreCase("update")) {
            Setting s = new Setting();
            int id = Integer.parseInt(request.getParameter("settingId"));     
            int typeId = Integer.parseInt(request.getParameter("settingType"));
            String settingTitle = request.getParameter("settingTitle");
            String settingValue = request.getParameter("settingValue");
            int settingOrder = Integer.parseInt(request.getParameter("settingOrder"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            
            s.setType_id(typeId);
            s.setSetting_title(settingTitle);
            s.setSetting_value(settingValue);
            s.setDisplay_order(settingOrder);
            s.setStatus(status);
            s.setDescription(description);
            
            dao.updateSetting(id, s);
            response.sendRedirect("setting");
        }
        else if(go.equalsIgnoreCase("add")){
            
            
            request.setAttribute("listType", listType);
            
            request.getRequestDispatcher("../view/admin/SettingAdd.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String go = request.getParameter("go");
        if (go.equalsIgnoreCase("add")) {
            Setting s = new Setting();
                
            int typeId = Integer.parseInt(request.getParameter("settingType"));
            String settingTitle = request.getParameter("settingTitle");
            String settingValue = request.getParameter("settingValue");
            int settingOrder = Integer.parseInt(request.getParameter("settingOrder"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            
            s.setType_id(typeId);
            s.setSetting_title(settingTitle);
            s.setSetting_value(settingValue);
            s.setDisplay_order(settingOrder);
            s.setStatus(status);
            s.setDescription(description);
            dao.addSetting(s);
            request.setAttribute("listType", listType);
            response.sendRedirect("setting");
        }
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
