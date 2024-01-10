/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.PageControl;
import Model.SettingValue;
import Model.Settingtitle;
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
@WebServlet(name = "Permission", urlPatterns = {"/Permission"})
public class Permission extends HttpServlet {

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
            out.println("<title>Servlet Permission</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Permission at " + request.getContextPath() + "</h1>");
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
        int cp = 0;
        int nrpp = 2;
        if (ses.getAttribute("nrpp") != null) {
            nrpp = (int) ses.getAttribute("nrpp");
        }
        int flag2 = 0;
        int flag = 0;
        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();
   String valuesearch="";
        if (request.getParameter("search") != null) {
            valuesearch = (String) request.getParameter("search");
            request.setAttribute("Search", valuesearch);
        }
         String valuefillter="";
        if (request.getParameter("filterstatus") != null) {
            valuefillter = (String) request.getParameter("filterstatus");
            request.setAttribute("filter", valuefillter);
        }
        
        if(valuesearch.length() > 0 && valuefillter.length()>0 && valuefillter.contains("Select")==false){
            if(valuefillter.contains("Active")){
                lstst =dao.getSettingtitleAfterFilteraSearch(4, valuesearch);
            }else   lstst =dao.getSettingtitleAfterFilteraSearch(0, valuesearch);
        }
        else if (request.getParameter("sort") != null && valuesearch.length() > 0) {
            int type = Integer.parseInt(request.getParameter("sort"));
            if (type == 1) {
                request.setAttribute("sortID", 0);
                type = 0;
            } else {
                request.setAttribute("sortID", 1);
                type = 1;
            }
            lstst = dao.getSettingtitleAfterSearortaSearch(type, valuesearch);

          

        
        } else if (request.getParameter("sort") != null) {
            int type = Integer.parseInt(request.getParameter("sort"));
            lstst = dao.getSettingtitleAfterSearort(type);
            if (type == 1) {
                request.setAttribute("sortID", 0);
            } else {
                request.setAttribute("sortID", 1);
            }

//            request.setAttribute("list", lstst);
            flag2 = 2;

        } else if (valuesearch.length() > 0) {
            lstst = dao.getSettingtitleAfterSearch(valuesearch);

            flag = 2;
        }else if (valuefillter.length()>0 && valuefillter.contains("Select")==false) {
            if(valuefillter.contains("Active")){
                lstst =dao.getSettingtitleAfterFilteraSearch(3, valuesearch);
            }else   lstst =dao.getSettingtitleAfterFilteraSearch(2, valuesearch);
        }
        else {
            if (flag2 == 0) {
                lstst = dao.getSettingtitle();
//             lstst = dao.getSettingtitleAfterSearort(1);
                flag = 3;
            }
        }
        ArrayList<SettingValue> lststs = dao.getSettingStatus();
        ArrayList<SettingValue> lstshow = new ArrayList<SettingValue>();
        for (int i = 0; i < lststs.size(); i++) {
            SettingValue sts = lststs.get(i);
            if (sts.getStatus() == 1) {
                lstshow.add(sts);
            }

        }
        ArrayList<SettingValue> lstshow2 = lstshow;
        request.setAttribute("lstshow", lstshow);

        int size = lstst.size();
        PageControl pc = new PageControl(nrpp, cp, size);

        pc.calc();
        request.setAttribute("flag", flag);
        request.setAttribute("flag2", flag2);

        request.setAttribute("DAO", dao);
        request.setAttribute("PC", pc);
        request.setAttribute("list", lstst);
        request.getRequestDispatcher("/view/admin/permissionforscreen.jsp").forward(request, response);
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
        String[] ivn = ((String) request.getParameter("nrpp")).split("/");
 String temp11 = "";
        int nrpp = Integer.parseInt(ivn[0]);
        int nrpp2 = Integer.parseInt(ivn[1]);
        ArrayList<SettingValue> lststs = dao.getSettingStatus();
        ArrayList<SettingValue> lstshow = new ArrayList<SettingValue>();
        for (int i = 0; i < lststs.size(); i++) {
            SettingValue sts = lststs.get(i);
            if (sts.getStatus() == 1) {
                lstshow.add(sts);
            }

        }
        request.setAttribute("lstshow", lstshow);
        request.setAttribute("lstshow2", lstshow);
        if (request.getParameter("button") != null) {
            String[] intArray = new String[(nrpp2 * lstshow.size() * 4) + nrpp2];
            int k = 0;
            for (int i = 1; i <= nrpp2; i++) {
                if (request.getParameter("title" + i) != null) {
                    intArray[k] = (String) request.getParameter("title" + i);
                    k++;
                    for (int j = 1; j <= lstshow.size() * 4; j++) {

                        if (request.getParameter("vehicle" + i + "" + j) != null) {
                            intArray[k] = "1";
                        } else {
                            intArray[k] = "0";
                        }
                        k++;

                    }
                }
            }

            //     String[] intArray2 = new String[40];
            request.setAttribute("sizeofarray", k);
            String result = "";
            int index = 0;
            int q = 0;
            int id = 0;
            int[] role2 = new int[lstshow.size()];
            for (int i = 0; i < lstshow.size(); i++) {
                role2[i] = lstshow.get(i).getTitle();
            }
            String temp = "";
            for (int i = 0; i < intArray.length; i++) {
                if (i == 0 || i % ((lstshow.size() * 4) + 1) == 0) {

                    temp += "#" + intArray[i] + " ";

                } else {
                    temp += intArray[i] + " ";
                }
            }

//        request.setAttribute("sizeofarray", k);
            int role = 0;
           
            String[] testsql = new String[50];
            int indext = 0;
            while (q != k) {

                if (q == 0 || q % ((lstshow.size() * 4) + 1) == 0) {

                    id = Integer.parseInt(intArray[q]);
                    q++;

                }

                int a = Integer.parseInt(intArray[q]);
                q++;
                int b = Integer.parseInt(intArray[q]);
                q++;
                int c = Integer.parseInt(intArray[q]);
                q++;
                int d = Integer.parseInt(intArray[q]);
                q++;
                testsql[indext] = dao.updatePermission(id, role2[role], a, b, c, d);
                temp11+= testsql[indext] +"##";
                indext++;
                role++;

//            intArray2[index]=dao.updatePermission(id, role, a, b, c, d);
                //     index++;
                if (role == role2.length) {
                    role = 0;
                }
            }
            if (q != 0) {
                request.setAttribute("annount", "11");
            }
        }
        
    request.setAttribute("sizeofarray", temp11);
        HttpSession ses = request.getSession();

        int cp = 0;
        try {
            cp = Integer.parseInt((String) request.getParameter("cp"));
        } catch (Exception e) {
            cp = 0;
        }

//            if (ses.getAttribute("reload") != null) {
//                reload = (boolean) ses.getAttribute("reload");
//            }
//            if (reload) {
//                reload = false;
//                ses.setAttribute("reload", false);
//            }
        if (request.getParameter("home") != null) {
            cp = 0;
        }
        if (request.getParameter("pre") != null) {
            cp = cp - 1;
        }
        if (request.getParameter("next") != null) {
            cp = cp + 1;
        }

        int flag2 = 0;
        int flag = 0;
        ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();
     String valuesearch="";
        if (request.getParameter("search") != null) {
            valuesearch = (String) request.getParameter("search");
            request.setAttribute("Search", valuesearch);
        }
         String valuefillter="";
        if (request.getParameter("filterstatus") != null) {
            valuefillter = (String) request.getParameter("filterstatus");
            request.setAttribute("filter", valuefillter);
        }
        
        if(valuesearch.length() > 0 && valuefillter.length()>0 && valuefillter.contains("Select")==false){
            if(valuefillter.contains("Active")){
                lstst =dao.getSettingtitleAfterFilteraSearch(4, valuesearch);
            }else   lstst =dao.getSettingtitleAfterFilteraSearch(0, valuesearch);
        }
        else if (request.getParameter("sort") != null && valuesearch.length() > 0) {
             int type = Integer.parseInt(request.getParameter("sort"));
            if (type == 1) {
                request.setAttribute("sortID", 1);
                type = 1;
            } else {
                request.setAttribute("sortID", 0);
                type = 0;
            }
            lstst = dao.getSettingtitleAfterSearortaSearch(type, valuesearch);
 
        } else if (request.getParameter("sort") != null) {
            int type = Integer.parseInt(request.getParameter("sort"));
            lstst = dao.getSettingtitleAfterSearort(type);
            if (type == 1) {
                request.setAttribute("sortID", 1);
            } else {
                request.setAttribute("sortID", 0);
            }

//            request.setAttribute("list", lstst);
            flag2 = 2;

        } else if (valuesearch.length() > 0) {
            lstst = dao.getSettingtitleAfterSearch(valuesearch);

            flag = 2;
        }else if (valuefillter.length()>0 && valuefillter.contains("Select")==false) {
            if(valuefillter.contains("Active")){
                lstst =dao.getSettingtitleAfterFilteraSearch(3, valuesearch);
            }else   lstst =dao.getSettingtitleAfterFilteraSearch(2, valuesearch);
        }
        else {
            if (flag2 == 0) {
                lstst = dao.getSettingtitle();
//             lstst = dao.getSettingtitleAfterSearort(1);
                flag = 3;
            }
        }

        int size = lstst.size();
        PageControl pc = new PageControl(nrpp, cp, size);
        pc.calc();
        if (request.getParameter("end") != null) {
            cp = pc.getNp() - 1;
        }
        for (int i = 0; i < pc.getNp(); i++) {
            if (request.getParameter("btn" + i) != null) {
                cp = i;
            }
        }

        pc.setCp(cp);
        pc.calc();
        request.setAttribute("flag", flag);
        request.setAttribute("flag2", flag2);
        ses.setAttribute("nrpp", nrpp);
        //      request.setAttribute("listS", intArray2);
        request.setAttribute("DAO", dao);
        request.setAttribute("list", lstst);
        request.setAttribute("PC", pc);

        request.getRequestDispatcher("/view/admin/permissionforscreen.jsp").forward(request, response);
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
