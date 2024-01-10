<%@page import="jakarta.servlet.http.HttpServletRequest"%>

</html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title> Successfully!</title>

        <!-- Custom fonts for this template-->

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

    </head>

    <body clas="hero-image">



        <%
            String fullname = "tyugyugy1";
            String mail = "tyugyugy2";
            String captcha = "tyugyugy3";
        String rcaptcha = "tyugyugy4";
                HttpServletRequest req = (HttpServletRequest) request;
      
                        if(req.getParameter("fullname")!=null){
                 
                 
                       fullname =(String) req.getParameter("fullname");
                   }

        if(req.getAttribute("fullname")!=null){
                 
                 
                       fullname =(String)req.getAttribute("fullname");
                   }
               if(req.getParameter("mail")!=null){
                 
                 
                       mail =(String) req.getParameter("mail");
                   }

        if(req.getAttribute("mail")!=null){
                 
                 
                       mail =(String)req.getAttribute("mail");
                   }
               if(req.getParameter("captcha")!=null){
                 
                 
                       captcha =(String) req.getParameter("captcha");
                   }

        if(req.getAttribute("captcha")!=null){
                 
                 
                       captcha =(String)req.getAttribute("captcha");
                   }
                    if(req.getParameter("rcaptcha")!=null){
                 
                 
                       rcaptcha =(String) req.getParameter("rcaptcha");
                   }

        if(req.getAttribute("rcaptcha")!=null){
                 
                 
                       rcaptcha =(String)req.getAttribute("rcaptcha");
                   }
                   String flag = "tyugyugy4";
               
      
                        if(req.getParameter("flag")!=null){
                 
                 
                       flag =(String) req.getParameter("flag");
                   }

        if(req.getAttribute("flag")!=null){
                 
                 
                       flag =(String)req.getAttribute("flag");
                   }
        if(req.getAttribute("flag")==null){
        %>

       
        <!--<h1><%=fullname%></h1>
        <h1><%=mail%></h1>
        
        
        <h1><%=rcaptcha%></h1>-->
        <div class="container">
            <div class="card card-login mx-auto mt-5">
                <div class="card-header">Register</div>
                <div class="card-body">
                    <div class="text-center mb-4">

                        <p>Welcome to S-Course Training System!</p>
                        <p>Your new Verify link had sent to our Mail please return to login page.</p>
                    </div>
                    <div class="text-center">

                        <a class="d-block small" href="Login.jsp">Login Page</a>
                    </div>

                </div>                    </div></div>

<%}else{
%>
                        <div class="container">
            <div class="card card-login mx-auto mt-5">
                <div class="card-header">Register</div>
                <div class="card-body">
                    <div class="text-center mb-4">

                       <p>Welcome to S-Course Training System!</p>
                        <p>Your new Password sent to your mail please check.</p>
                         <p>Now you can come to Home Page</p>
                    </div>
                    <div class="text-center">
                          <a class="d-block small mt-3" href="Home.jsp">Home</a>
                    </div></div></div></div>


<%}%>



                <script src="js/bootstrap.bundle.js" type="text/javascript"></script>
                <script src="js/jquery.easing.min.js" type="text/javascript"></script>
                <script src="js/jquery.min.js" type="text/javascript"></script>
                </body>
                </html>