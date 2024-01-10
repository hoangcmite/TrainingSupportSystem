<%@page import="jakarta.servlet.http.HttpServletRequest"%>
</html>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title> Error page</title>

  <!-- Custom fonts for this template-->

  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet" type="text/css"/>
  <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
</head>


<body>
<!--  <h1>login</h1>
  <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/AccessGoogle/login-google&response_type=code&client_id=352140522561-vpmetjr6bjce1vod9b0cppihhbcgdesh.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>  -->


        <%String flag = "tyugyugy";
                HttpServletRequest req = (HttpServletRequest) request;
                if(req.getAttribute("id")==null){
        %>
<!--        <h1>enter your mail</h1>
        <form action="CheckExpire" method="post">
            <input type="email" name="email">
            <input type="submit" value="Submit">

        </form>-->

    <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          
          <p>Error at identified reset link </p>
        </div>
                  <div class="text-center">
          <a class="d-block small mt-3" href="register.jsp">Register an Account</a>
          <a class="d-block small" href="Login.jsp">Login Page</a>
        </div>
        
  </div>
        <%}
        else{ 
                        if(req.getParameter("id")!=null){
                 
                 
                       flag =(String) req.getParameter("id");
                   }

        if(req.getAttribute("id")!=null){
                 
                 
                       flag =(String)req.getAttribute("id");
                   }
        
       


if(flag.equals("0")){

%>

 <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          
          <p>Link error let try another way or send link again!</p>
        </div>
                  <div class="text-center">
          <a class="d-block small mt-3" href="forgotpass.jsp">Forgot password</a>
          <a class="d-block small" href="Login.jsp">Login Page</a>
        </div>
        
  </div>
<%}
        if(flag.equals("1")){
%>
 <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          
          <p>This link had expired try sending another link !</p>
        </div>
                  <div class="text-center">
            <a class="d-block small mt-3" href="forgotpass.jsp">Forgot password</a>
          <a class="d-block small" href="Login.jsp">Login Page</a>
        </div>
        
  </div>
<%}
}%>


<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="js/jquery.easing.min.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
</body>
</html>