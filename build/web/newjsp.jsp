<%-- 
    Document   : newjsp
    Created on : Oct 5, 2022, 2:49:02 PM
    Author     : Legon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
       <% String s = "true/false/false/false";
        String[] k1 = s.split("/");
     %> 
  <input type="checkbox" <%=(k1[0].equals("false")) ? "" :"checked"%> id="vehicle1" name="vehicle1" value="Bike">
  <label for="vehicle2">Can Access</label>
  <input type="checkbox" <%=(k1[1].equals("false")) ? "" :"checked"%>  id="vehicle2" name="vehicle2" value="Car">
  <label for="vehicle2">Can Add</label><br>
  <input type="checkbox" <%=(k1[2].equals("false")) ? "" :"checked"%>  id="vehicle3" name="vehicle3" value="Boat">
  <label for="vehicle3">Can Delete</label>
   <input type="checkbox" <%=(k1[3].equals("false")) ? "" :"checked"%>  id="vehicle4" name="vehicle4" value="Boat2">
  <label for="vehicle4">Can Edit</label>
 
    </body>
</html>
