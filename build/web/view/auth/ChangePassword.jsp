<%-- 
    Document   : ChangePassword
    Created on : Sep 21, 2022, 8:16:23 PM
    Author     : ADMIN
--%>
<%-- 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <span>${denied}</span>
        <form action="ChangePassword" method="POST">
            Current password: <input type="password" name="curPass"/> <br/>
            New Password: <input type="password" name="newPass"/> <br/>
            Verifying password: <input type="password" name="verPass"/> <br/>
            <input type= "text" hidden name="mail" value="${mail}">
            <input type="submit" value="Save" /> 
        </form>
    </body>
</html>
--%>
<%-- 
    Document   : temp
    Created on : Sep 17, 2022, 4:11:51 PM
    Author     : Legon
--%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dal.DAO"%>
<!DOCTYPE html>
<html>
  <head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Register</title>
<style> 
    .red{
        color:red;
    }
  body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.hero-image {
  background-image: url("https://png.pngtree.com/background/20210712/original/pngtree-cyber-security-technology-background-picture-image_1186472.jpg");
  background-color: #cccccc;
  height: 500px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.hero-text {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}
</style>
  <!-- Custom fonts for this template-->

  <link href="css/sb-admin.css" rel="stylesheet" type="text/css"/>
  <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
</head>
  <body class="hero-image bg-dark">
<%
  
             String id ="";
         String flag = "tyugyugy4";
                HttpServletRequest req = (HttpServletRequest) request;
        if(req.getParameter("mail")!=null){
                 
                 
                       id =(String) req.getParameter("mail");
                   }

        if(req.getAttribute("mail")!=null){
                 
                 
                       id =(String)req.getAttribute("mail");
                   }
        if(req.getParameter("flag")!=null){
                 
                 
                       flag =(String) req.getParameter("flag");
                   }

        if(req.getAttribute("flag")!=null){
                 
                 
                       flag =(String)req.getAttribute("flag");
                   }
                  
                   %>

  <div class="container">
     
    <div class="card card-register mx-auto mt-5">
      <div class="card-header">Change Password</div>
      <div style="text-align: center">${denied}</div>
      <div class="card-body">
          <form action="/WebApplication2/ChangePassword" method="post">
              <input type="text" hidden name ="mail" value="<%=id%>">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-12">
                <div class="form-label-group">
                  <input type="password" name="curPass" id="fullname" class="form-control" placeholder="Full name" required="required" autofocus="autofocus">
                  <label for="fullname">old password</label>
                </div>
              </div>
              
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-12">
                <div class="form-label-group">
                  <input type="password" name="newPass" id="fullname" class="form-control" placeholder="Full name" required="required" autofocus="autofocus">
                  <label for="fullname">New password</label>
                </div>
              </div>
              
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
                <input type="password" name="verPass" id="inputEmail" class="form-control" placeholder="Email address" required="required">
              <label for="inputEmail">Confirm Password</label>
            </div>
          </div>
         
          <!--<a class="btn btn-primary btn-block" href="login.html">Register</a>-->
              <input class="btn btn-primary btn-block"  type="submit" value="Update new pass">

        </form>
     
      </div>
                 
      
      
      <%
                   if(flag.equals("1")){
%>
<div class="red card-header">Confirm Password not correct to New Password please enter again!</div>
                 <%}%>
                      
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->



<script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>

<script src="js/jquery.easing.min.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>

</body>
</html>