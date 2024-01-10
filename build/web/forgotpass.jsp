<%@page import="jakarta.servlet.http.HttpServletRequest"%>

</html>
<html>
<head>
 
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title> Forgot Password</title>

 
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet" type="text/css"/>
  <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
<style> 
    .red{
        color:red;
    }
  body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.hero-image {
  background-image: url("https://png.pngtree.com/background/20210711/original/pngtree-dark-vector-abstract-background-picture-image_1159556.jpg");
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
	  <script>
        function onRecaptchaSubmit(token) {
            alert("reCaptcha challenge response:\n" + token);
            window.grecaptcha.reset();
        }
    </script>
<script src="https://www.google.com/recaptcha/api.js" async defer>
</script>
</head>

<body class="hero-image">



        <%String flag = "tyugyugy";
                HttpServletRequest req = (HttpServletRequest) request;
                if(req.getAttribute("flag")==null){
        %>
<!--        <h1>enter your mail</h1>
        <form action="CheckExpire" method="post">
            <iput type="email" name="email">
            <input type="submit" value="Submit">

        </form>-->
	
<!--<img src="https://www.w3schools.com/html/workplace.jpg" alt="Workplace" usemap="#workmap" width="400" height="379">-->
   

<div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          <h4>Forgot your password?</h4>
          <p>Enter your email address and we will send a link to reset your password.</p>
        </div>
                          
        <form  	 action="CheckExpire" method="post">
          <div class="form-group">
            <div class="form-label-group">
                <input type="email" name="mail" id="inputEmail" class="form-control" placeholder="Enter email address" required="required" autofocus="autofocus">
              <label for="inputEmail">Enter email address</label>
            </div>
          </div>
    

            <input class="btn btn-primary btn-block" type="submit" value="Reset Password">
          
        </form>
 

        <div class="text-center">
          <a class="d-block small mt-3" href="register.jsp">Register an Account</a>
          <a class="d-block small" href="Login.jsp">Login Page</a>
        </div>
      </div>
    </div>
  </div>
        <%}
        else{ 
                        if(req.getParameter("flag")!=null){
                 
                 
                       flag =(String) req.getParameter("flag");
                   }

        if(req.getAttribute("flag")!=null){
                 
                 
                       flag =(String)req.getAttribute("flag");
                   }
        
       


if(flag.equals("1")){

%>

 <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          
          <p>Send link successful please check your mail!</p>
          <p>If you don't receive our mail so you should check your email again and send another link!</p>
        </div>
                  <div class="text-center">
                      <a class="d-block small mt-3" href="forgotpass.jsp">Forgot password</a>
          <a class="d-block small mt-3" href="register.jsp">Register an Account</a>
          <a class="d-block small" href="Login.jsp">Login Page</a>
        </div>
        
  </div>
<%}
        if(flag.equals("0")){
%>
<h1></h1>
 <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">Reset Password</div>
      <div class="card-body">
        <div class="text-center mb-4">
          <h4>Forgot your password?</h4>
          <p style="color:red;">Failure to vertify please enter your email address and we will send you link to reset your password.</p>
        </div>
 
        <form action="CheckExpire" method="post">
          <div class="form-group">
            <div class="form-label-group">
                
            <% if(req.getAttribute("mail")!=null){
                                        %>

                                        <input type="email" value="<%=(String)req.getAttribute("mail")%>" name="mail" id="inputEmail" class="form-control" placeholder="Enter email address" required="required" autofocus="autofocus">
              <label for="inputEmail">Enter email address</label>
                                        <%
                 
                              
                           }else{

                                        %>
                                        <input type="email" name="mail" id="inputEmail" class="form-control" placeholder="Enter email address" required="required" autofocus="autofocus">
              <label for="inputEmail">Enter email address</label><%}%>    
                
                
            </div>
          </div>
       

            <input class="btn btn-primary btn-block" type="submit" value="Reset Password">
          <!--<a class="btn btn-primary btn-block" href="login.html">Reset Password</a>-->
                	
        </form>
        <div class="text-center">
          <a class="d-block small mt-3" href="register.jsp">Register an Account</a>
          <a class="d-block small" href="Login.jsp">Login Page</a>
        </div>
      </div>
    </div>
  </div>
<%}
}%>


<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="js/jquery.easing.min.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>

</body>
</html>