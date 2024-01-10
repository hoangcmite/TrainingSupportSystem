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
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <!-- Custom fonts for this template-->
        <link href="all.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom styles for this template-->
        <link href="sb-admin.css" rel="stylesheet" type="text/css"/>

        <link href="css/sb-admin.css" rel="stylesheet" type="text/css"/>
        <link href="css/all.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="bg-dark hero-image">
        <%
            String captcha="asdasdqw33vrq";
            DAO dao = new DAO();
            captcha= dao.getRandomChars(10);
             
                 String flag = "tyugyugy4";
                 
                  
                        HttpServletRequest req = (HttpServletRequest) request;
        

                if(req.getAttribute("flag")!=null){
                 
                 
                               flag =(String)req.getAttribute("flag");
                           }
                           


        %>

        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Register an Account</div>

                <div class="card-body">
                    <form action="Register" method="post">
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-12">
                                    <div class="form-label-group">
                                        <% if(req.getAttribute("fullname")!=null){
                                        %>

                                        <input type="text" value="<%=(String)req.getAttribute("fullname")%>" name="fullname" id="fullname" class="form-control" placeholder="Full name" required="required" autofocus="autofocus">
                                        <label for="fullname">Full name</label>
                                        <%
                 
                              
                           }else{

                                        %>
                                        <input type="text" name="fullname" id="fullname" class="form-control" placeholder="Full name" required="required" autofocus="autofocus">

                                        <label for="fullname">Full name</label><%}%>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-label-group">
                                <% if(req.getAttribute("mail")!=null){
                                %>

                                <input type="email" value="<%=(String)req.getAttribute("mail")%>" name="mail" id="inputEmail" class="form-control" placeholder="Email address" required="required">
                                <label for="inputEmail">Email address</label>
                                <%
                 
                              
                   }else{

                                %>
                                <input type="email" name="mail" id="inputEmail" class="form-control" placeholder="Email address" required="required">
                                <label for="inputEmail">Email address</label><%}%>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-md-6">
                          <div class="g-recaptcha" data-sitekey="6LdDRBAiAAAAAMes9G_SP05_iteGqDaA2xODgxDm"></div>
                                </div>
                         
                            </div>
                        </div>
                        <!--<a class="btn btn-primary btn-block" href="login.html">Register</a>-->
                        <input class="btn btn-primary btn-block"  type="submit" value="Create an Account">

                    </form>
                    <div class="text-center">

                        <a class="d-block small mt-3" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/WebApplication2/login-google&response_type=code&client_id=516136248040-tl720shd66lpslge5lheh0pd6b7sbc3m.apps.googleusercontent.com&approval_prompt=force">Sign in With Google</a>  

                        <a class="d-block small mt-3" href="Login.jsp">Login Page</a>
                        <a class="d-block small" href="forgotpass.jsp">Forgot Password?</a>
                    </div>
                </div>



                <%
                             if(flag.equals("1")){
                %>
                <div class="red card-header">Please check Google Captcha!</div>
                <%}%>
                <%
            if(flag.equals("2")){
                %>
                <div class="red card-header">This mail had sign for another account!</div>
                <%}%>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="jquery.easing.min.js" type="text/javascript"></script>



        <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>

        <script src="js/jquery.easing.min.js" type="text/javascript"></script>
        <script src="js/jquery.min.js" type="text/javascript"></script>

    </body>
</html>
