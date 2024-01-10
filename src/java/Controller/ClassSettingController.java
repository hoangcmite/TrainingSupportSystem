/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.ClassList;
import Model.ClassSetting;
import Model.Setting;
import Model.UserAccount;
import dal.ClassSettingDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ClassSettingController extends HttpServlet {
   
    ClassSettingDao dao = new ClassSettingDao();
    ArrayList<ClassSetting> listType = dao.listType();
    ArrayList<ClassList> classList = dao.listClass();
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
            String raw_class = request.getParameter("class_id");
            
            String raw_sort = request.getParameter("sort");
            String raw_type = request.getParameter("type");
            String raw_status = request.getParameter("status");
            String raw_title_value = request.getParameter("title_value");
            
            //response.getWriter().print(raw_title+" "+raw_value);
            
            if (raw_class == null || raw_class.length() == 0) {
                raw_class = Integer.toString(classList.get(0).getClass_id());
            }
            
            if (raw_sort == null || raw_sort.length() == 0) {
                raw_sort = "0";
            }
            if (raw_type == null || raw_type.length() == 0) {
                raw_type = "-1";
            }
            if (raw_status == null || raw_status.length() == 0) {
                raw_status = "-1";
            }
            if (raw_title_value == null || raw_title_value.trim().length() == 0) {
                raw_title_value = null;
            }
            
            
            int classId = Integer.parseInt(raw_class);
            
            
            int sort = Integer.parseInt(raw_sort);
            int typeId = Integer.parseInt(raw_type);
            int status = Integer.parseInt(raw_status);
//            String title_search = raw_title_value;
//            String value_search = raw_title_value;
            String title_value = raw_title_value;

            int sortId = 0; // 1
            int sortType = 2; // 3 
            int sortTitle = 4; // 5
            int sortValue = 6; // 7
            int sortOrder = 8; // 9

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
                default:
                    break;
            }

            // Set ngược lại cho function js để lần sau bấm sort 
            request.setAttribute("sortId", sortId);
            request.setAttribute("sortType", sortType);
            request.setAttribute("sortTitle", sortTitle);
            request.setAttribute("sortValue", sortValue);
            request.setAttribute("sortOrder", sortOrder);

            //lấy ra
            
            ArrayList<ClassSetting> listClassSetting = dao.advancedSearchClassSetting(sort, typeId, status, title_value, pageIndex, pageSize, classId);
            int count = dao.countAdvancedSearchClassSetting(sort, typeId, status, title_value, classId);
            int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("listClassSetting", listClassSetting);
            request.setAttribute("listType", listType);
            request.setAttribute("classList", classList);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            // sau khi đã search thì vẫn select những dữ liệu mà mình đã chọn search
            request.setAttribute("classId", classId);
            request.setAttribute("sort", sort);
            request.setAttribute("typeId", typeId);
            request.setAttribute("status", status);
            request.setAttribute("title_value", title_value);
            // lấy Url để phân trang bằng js 
            String url = "ClassSetting?";
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
            
            request.getRequestDispatcher("../view/admin/ClassSettingList.jsp").forward(request, response);
        } 
        else if (go.equalsIgnoreCase("active")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int cID = Integer.parseInt(request.getParameter("class_id"));
            dao.active(id);           
            response.sendRedirect("ClassSetting?class_id="+cID);
        } else if (go.equalsIgnoreCase("inactive")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int cID = Integer.parseInt(request.getParameter("class_id"));
            dao.inactive(id);
            response.sendRedirect("ClassSetting?class_id="+cID);
        }else if(go.equalsIgnoreCase("detail")){
            String raw_id = request.getParameter("id");
            int cID = Integer.parseInt(request.getParameter("class_id"));
            int id = -1;
            if (raw_id != null && raw_id.trim().length() > 0) {
                id = Integer.parseInt(raw_id);
            }
            ClassSetting classSettingDetails = dao.getClassSettingById(id);
//            response.getWriter().print(classSettingDetails.getClassSetting_title());
            request.setAttribute("listType", listType);
            request.setAttribute("classId", cID);
            request.setAttribute("classSettingDetails", classSettingDetails);
            request.getRequestDispatcher("../view/admin/ClassSettingDetails.jsp").forward(request, response);
        }else if (go.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            int cID = Integer.parseInt(request.getParameter("class_id"));
            int n = dao.deleteClassSetting(id);
            response.sendRedirect("ClassSetting?class_id="+cID);
        }
        else if (go.equalsIgnoreCase("update")) {
            ClassSetting cs = new ClassSetting();
            int id = Integer.parseInt(request.getParameter("settingId"));     
            int typeId = Integer.parseInt(request.getParameter("settingType"));
            String classSettingTitle = request.getParameter("settingTitle");
            String classSettingValue = request.getParameter("settingValue");
            int classSettingOrder = Integer.parseInt(request.getParameter("settingOrder"));
            int classId = Integer.parseInt(request.getParameter("class_id"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            
            cs.setClassSetting_id(id);
            cs.setType_id(typeId);
            cs.setClassSetting_title(classSettingTitle);
            cs.setClassSetting_value(classSettingValue);
            cs.setDisplay_order(classSettingOrder);
            cs.setClass_id(classId);
            cs.setStatus(status);
            cs.setDescription(description);
            
            dao.updateClassSetting(id, cs);
            
            request.setAttribute("listType", listType);
            response.sendRedirect("ClassSetting?class_id="+classId);
        }else if(go.equalsIgnoreCase("add")){
            request.setAttribute("listClass", classList);
            
            request.setAttribute("listType", listType);
            
            request.getRequestDispatcher("../view/admin/ClassSettingAdd.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String go = request.getParameter("go");
        if (go.equalsIgnoreCase("add")) {
            ClassSetting cs = new ClassSetting();
                
            int typeId = Integer.parseInt(request.getParameter("settingType"));
            String title = request.getParameter("settingTitle");
            String value = request.getParameter("settingValue");
            int order = Integer.parseInt(request.getParameter("settingOrder"));
            int classId = Integer.parseInt(request.getParameter("classId"));
            int status = Integer.parseInt(request.getParameter("status"));
            String description = request.getParameter("description");
            
            cs.setType_id(typeId);
            cs.setClassSetting_title(title);
            cs.setClassSetting_value(value);
            cs.setDisplay_order(order);
            cs.setClass_id(classId);
            cs.setStatus(status);
            cs.setDescription(description);
            
            dao.addClassSetting(cs);
            request.setAttribute("listClass", classList);
            request.setAttribute("listType", listType);
            response.sendRedirect("ClassSetting");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
