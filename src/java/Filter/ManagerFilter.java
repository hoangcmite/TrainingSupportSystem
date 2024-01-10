/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import Model.SettingValue;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.UserAccount;
import Model.permission;
import dal.DAO;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Legon
 */
public class ManagerFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ManagerFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AdminFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        DAO dao;
        int count = 0;

        dao = new DAO();
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession ses = req.getSession();

        String url = req.getContextPath() + "/Login";

        String requestedUri = ((HttpServletRequest) request).getRequestURI();
          if (requestedUri.matches(".*(css|jpg|png|gif|js|assets)")) {
                    chain.doFilter(request, response);
                    return;
                }
        if (req.getRequestURI().equals(url) || req.getRequestURI().equals(req.getContextPath() + "/")
                || req.getRequestURI().contains("Login.jsp") || req.getRequestURI().contains("Login")) {

//                      req.setAttribute("type", 2);
//              req.getRequestDispatcher("/Home.jsp").forward(req, response);
            if (ses.getAttribute("user") != null) {
                req.setAttribute("type", 2);
                req.getRequestDispatcher("/Home.jsp").forward(req, response);
            } else {
                chain.doFilter(request, response);
            }

        } else {
            int settingID = -2;
        

            ArrayList<SettingValue> lstsv = dao.getSettingValueStatus();
            SettingValue st = null;
            for (int i = 0; i < lstsv.size(); i++) {
                if (req.getRequestURI().toLowerCase().contains(lstsv.get(i).getValue().toLowerCase())) {
                    settingID = lstsv.get(i).getTitle();
                    st = lstsv.get(i);
                    
                }
            }

            if (settingID == -2) {
              
                req.setAttribute("type", 5);
                req.getRequestDispatcher("/Home.jsp").forward(req, response);
                //   chain.doFilter(request, response);
                 //request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                if (st.getStatus() == 1) {
                    chain.doFilter(request, response);
                } else if(st.getStatus() == 2) {
                    if (ses.getAttribute("user") != null) {
                        UserAccount uac = new UserAccount();
                        uac = (UserAccount) ses.getAttribute("user");
                      
                            int id = uac.getId();

                            int role = dao.getUserRole(id);

                            permission p = dao.getPermis(settingID, role);
                            String s = p.toString();
                            String[] k1 = s.split("/");
                            if (k1[0].equals("true") || k1[0].equals("True")) {
                                int edit = 0;
                                int add = 0;
                                int delete = 0;
                                if (k1[1].equals("true") || k1[1].equals("True")) {
                                    edit = 1;
                                }
                                if (k1[2].equals("true") || k1[2].equals("True")) {
                                    add = 1;
                                }
                                if (k1[3].equals("true") || k1[3].equals("True")) {
                                    delete = 1;
                                }
                                request.setAttribute("edit", edit);
                                request.setAttribute("add", add);
                                request.setAttribute("delete", delete);
                                chain.doFilter(request, response);

                            } else {
                                req.setAttribute("type", 3);
                                req.getRequestDispatcher("/Home.jsp").forward(req, response);
                            }
                        } else {
                            req.setAttribute("type", 1);
                            req.getRequestDispatcher("/Home.jsp").forward(req, response);
                        }
                    } else {
                        req.setAttribute("type", 4);
                        req.getRequestDispatcher("/Home.jsp").forward(req, response);
                    }
                

            }
        }
//            } else {
//
//                int settingID = -2;
//                String test = "";
//                ArrayList<SettingValue> lstsv = dao.getSettingValue();
//                for (int i = 0; i < lstsv.size(); i++) {
//                    test += lstsv.get(i).getValue() + "//";
//
//                    if (req.getRequestURI().contains(lstsv.get(i).getValue())) {
//                        settingID = lstsv.get(i).getTitle();
//
//                    }
//                }
//                if (settingID == -2) {
//                    request.setAttribute("test", req.getRequestURI());
//                    chain.doFilter(request, response);
//                } else {
//                    req.setAttribute("test", settingID);
//                    req.setAttribute("type", 1);
//                    req.getRequestDispatcher("/Home.jsp").forward(req, response);
//                }
//            }
        //  }

//        if(ses.getAttribute("user")!=null){
//            UserAccount us= (UserAccount) ses.getAttribute("user");
//            int role = Integer.parseInt(us.getRole());
//             if (req.getRequestURI().equals(url) || req.getRequestURI().equals(req.getContextPath() + "/")
//                     || req.getRequestURI().contains("Login.jsp")|| req.getRequestURI().contains("Login")) {
//               
//                      req.setAttribute("type", 2);
//              req.getRequestDispatcher("/Home.jsp").forward(req, response);
//               
//            } else {
//                if (role <= 5) {
//                      req.setAttribute("id", req.getRequestURI());
//                       
//                    chain.doFilter(request, response);
//                } else if (req.getRequestURI().contains("/edit")) {
////                    req.setAttribute("user", us.getUserName());
//                    res.sendRedirect(req.getContextPath() + "/Views/Member/index.jsp");
//                } else {
////                    req.setAttribute("user", us.getUserName());
//                     
//                     chain.doFilter(request, response);
//                }
//    
//         
//            }
//        }
//        else{
//             req.setAttribute("type", 1);
//             req.getRequestDispatcher("/Home.jsp").forward(req, response);
//         }
//        if (debug) {
//            log("AdminFilter:doFilter()");
//        }
//        
//        doBeforeProcessing(request, response);
//        
//        Throwable problem = null;
//        try {
//            chain.doFilter(request, response);
//        } catch (Throwable t) {
//            // If an exception is thrown somewhere down the filter chain,
//            // we still want to execute our after processing, and then
//            // rethrow the problem after that.
//            problem = t;
//            t.printStackTrace();
//        }
//        
//        doAfterProcessing(request, response);
//
//        // If there was a problem, we want to rethrow it if it is
//        // a known type, otherwise log it.
//        if (problem != null) {
//            if (problem instanceof ServletException) {
//                throw (ServletException) problem;
//            }
//            if (problem instanceof IOException) {
//                throw (IOException) problem;
//            }
//            sendProcessingError(problem, response);
//        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AdminFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AdminFilter()");
        }
        StringBuffer sb = new StringBuffer("AdminFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
