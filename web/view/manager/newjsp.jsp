<%-- 
    Document   : newjsp
    Created on : Oct 4, 2022, 2:03:14 PM
    Author     : Legon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Manager role only</h1>
        <%
            int edit=1111; 
            int add=1111; 
            int delete=1111; 
            
        HttpServletRequest req = (HttpServletRequest) request;
        if( req.getAttribute("edit")!=null){
         
            edit = (int) req.getAttribute("edit");  }      
        if( req.getAttribute("add")!=null){
         
            add = (int) req.getAttribute("add");  } 
        if( req.getAttribute("delete")!=null){
         
            delete = (int) req.getAttribute("delete");  } 
        
        %>
        <%=edit%>//
        <%=add%>//
        <%=delete%>//
        
    </body>
</html>
