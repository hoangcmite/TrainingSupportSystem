<%-- 
    Document   : userprofile
    Created on : Sep 21, 2022, 8:43:25 PM
    Author     : Legon
--%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="dal.DAO"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link href="css/userprofile.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">



    </head>

    <body style="background-color: #eee;">

        <%
        String userid = "";
        String fullname = "";
        String mail = "";
        String mobie = "";
        String password = "";
        String avatar_url = "";
        String status = "";
        String note = "";
        String createat = "";
        String code = "";
        String company = "";
        String position = "";
        String address = "";
        String sql="";
        String role="";
        DAO dao = new DAO();
         HttpServletRequest req = (HttpServletRequest) request;
      
       
        if(req.getParameter("userid")!=null){
                       userid =(String) req.getParameter("userid");
            }
        if(req.getAttribute("userid")!=null){
                       userid =(String)req.getAttribute("userid");
                   
                   
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
                   
        if(req.getParameter("mobie")!=null){
                 mobie =(String) req.getParameter("mobie");
                   }
        if(req.getAttribute("mobie")!=null){
                mobie =(String)req.getAttribute("mobie");
                   }
        if(req.getParameter("password")!=null){
                       password =(String) req.getParameter("password");
                   }
                   
        if(req.getAttribute("password")!=null){
                       password =(String)req.getAttribute("password");
                   }
                   
        if(req.getParameter("avatar_url")!=null){
                 avatar_url =(String) req.getParameter("avatar_url");
                   }
        if(req.getAttribute("avatar_url")!=null){
                avatar_url =(String)req.getAttribute("avatar_url");
                   }
                   
                   
        if(req.getParameter("status")!=null){
                       status =(String) req.getParameter("status");
                   }
        if(req.getAttribute("status")!=null){
                       status =(String)req.getAttribute("status");
                   }
                   
        if(req.getParameter("note")!=null){
                 note =(String) req.getParameter("note");
                   }
        if(req.getAttribute("note")!=null){
                note =(String)req.getAttribute("note");
                   }
                           if(req.getParameter("password")!=null){
                       password =(String) req.getParameter("password");
                   }
                   
        if(req.getAttribute("password")!=null){
                       password =(String)req.getAttribute("password");
                   }
                   
        if(req.getParameter("avatar_url")!=null){
                 avatar_url =(String) req.getParameter("avatar_url");
                   }
        if(req.getAttribute("avatar_url")!=null){
                avatar_url =(String)req.getAttribute("avatar_url");
                   }
                   
                   
        if(req.getParameter("status")!=null){
                       status =(String) req.getParameter("status");
                   }
        if(req.getAttribute("status")!=null){
                       status =(String)req.getAttribute("status");
                   }
                   
        if(req.getParameter("note")!=null){
                 note =(String) req.getParameter("note");
                   }
        if(req.getAttribute("note")!=null){
                note =(String)req.getAttribute("note");
                   }
                    if(req.getParameter("createat")!=null){
                 createat =(String) req.getParameter("createat");
                   }
        if(req.getAttribute("createat")!=null){
                createat =(String)req.getAttribute("createat");
                   }
                   
                   
        if(req.getParameter("code")!=null){
                       code =(String) req.getParameter("code");
                   }
        if(req.getAttribute("code")!=null){
                       code =(String)req.getAttribute("code");
                   }
                   
        if(req.getParameter("company")!=null){
                 company =(String) req.getParameter("company");
                   }
        if(req.getAttribute("company")!=null){
                company =(String)req.getAttribute("company");
                   }
        if(req.getParameter("position")!=null){
                       position =(String) req.getParameter("position");
                   }
        if(req.getAttribute("position")!=null){
                       position =(String)req.getAttribute("position");
                   }
                   
        if(req.getParameter("address")!=null){
                 address =(String) req.getParameter("address");
                   }
        if(req.getAttribute("address")!=null){
                address =(String)req.getAttribute("address");
                   }
                   
         if(req.getParameter("role")!=null){
                 role =(String) req.getParameter("role");
                   }
        if(req.getAttribute("role")!=null){
                role =(String)req.getAttribute("role");
                   }
                   
//                   File currentDirFile = new File(".");
//String currentPath = currentDirFile.getAbsolutePath();

                ServletContext servletContext = getServletContext();
                String currentPath = servletContext.getRealPath(File.separator);
     
        currentPath= currentPath.substring(0, currentPath.length()-10);
        int i=currentPath.length()-1;
       while(i!=0){
           if(currentPath.charAt(i)== 92 ){
               StringBuilder myName = new StringBuilder(currentPath);
                myName.insert(i, "\\");
               
              currentPath= myName.toString();
           }
          i--;
           
           
       }
//         int i=currentPath.length()-1;
//       while(i!=0){
//           if(currentPath.charAt(i)== 92 ){
//               StringBuilder myName = new StringBuilder(currentPath);
//                myName.setCharAt(i, '/');
//              currentPath= myName.toString();
//           }
//          i--;
//           
//           
//       }
//String currentPath = helper.substring(0, helper.length() - currentDirFile.getCanonicalPath().length());
        %>

        <section >
            <div class="container py-5">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb" class="bg-light rounded-3 p-3 mb-4">
                            <ol class="breadcrumb mb-0">
                                <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                                <li class="breadcrumb-item active">User</li>
                                <li class="breadcrumb-item active" aria-current="page">User Profile</li>
                            </ol>
                        </nav>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body text-center">

                                <%
                                String aimgaa= avatar_url;
                                if(avatar_url.contains(userid+".jpg")){
            
                                     aimgaa=currentPath+avatar_url;
                                     i=aimgaa.length()-1;
                                while(i!=0){
                                if(aimgaa.charAt(i)== 47 ){
                                StringBuilder myName = new StringBuilder(aimgaa);
                                    myName.setCharAt(i, '\\');
               
                                aimgaa= myName.toString();
                                }
                                i--;
           
           
                                }
                                     aimgaa = dao.linktoimg(aimgaa);
                                %>
                                <img src="data:image/jpg;base64, <%=aimgaa%>" alt="avatar"class="rounded-circle img-fluid" style="width: 150px;">

                                <%
                                     }else{
                                
                                
                                %>

                                <img src="<%=aimgaa%>" alt="avatar"class="rounded-circle img-fluid" style="width: 150px;">
                                <%}%>
                                <h5 class="my-3"> <%=fullname%></h5>

                                <p class="text-muted mb-1"> <%=position%></p>
                                <p class="text-muted mb-4"> <%=address%></p>
                                <div class="d-flex justify-content-center mb-2">
                                    <!--              <button type="button" class="btn btn-primary">Follow</button>
                                                  <button type="button" class="btn btn-outline-primary ms-1">Message</button>-->
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4 mb-lg-0">
                            <div class="card-body p-0">
                                <ul class="list-group list-group-flush rounded-3">


                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                        <i class="fab fa-instagram fa-lg" style="color: #ac2bac;"></i>
                                        <a style="margin: auto;" class="btn btn-outline-primary mb-0" href="/WebApplication2/ChangePassword?mail=<%=mail%> ">Change Password</a>



                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                        <i class="fab fa-instagram fa-lg" style="color: #ac2bac;"></i>
                                        <a style="margin: auto;" class="btn btn-outline-primary mb-0" href="forgotpass.jsp ">Forgot password?</a>



                                    </li>

                                    <li class="list-group-item d-flex justify-content-between align-items-center p-3">
                                        <i class="fab fa-facebook-f fa-lg" style="color: #3b5998;"></i>

                                        <a style="margin: auto;" class="btn btn-outline-primary mb-0" href="/WebApplication2/Logout">Log out</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8"> 
                        <div class="card mb-4">
                            <div class="card-body">


                                <form action="UserProfile" method="post">
                                    <input type="text" hidden name ="id" value="<%=userid%>">
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Full Name</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input style='border: 0px; width:100%;' name="fullname" class="text-muted mb-0" type="text" value="<%=fullname%>" >
                                        </div>
                                    </div>
                                    <hr>

                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Email</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <p  class="text-muted mb-0"> <%=mail%></p>

                                        </div>
                                    </div>
                                    <hr>
                                    <input type="text" hidden name="currentpath" value="<%=currentPath%>" >
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Mobile</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input style='border: 0px;' name="mobie" class="text-muted mb-0" type="text" value="<%=mobie%>" >
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Avatar Url</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input style='border: 0px; width:100%;' name="avatar_url" class="text-muted mb-0" type="text" value="<%=avatar_url%>" >
                                        </div>
                                    </div>

                                    <hr>

                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Role</p>
                                        </div>
                                        <div class="col-sm-9">

                                            <p  class="text-muted mb-0"> <%=role%></p>
                                        </div>
                                    </div>
                                    <hr>


                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Company</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input style='border: 0px; width:100%;' name="company" class="text-muted mb-0" type="text" value="<%=company%>" >
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Position</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input style='border: 0px; width:100%;' name="position" class="text-muted mb-0" type="text" value="<%=position%>" >
                                        </div>
                                    </div>
                                    <hr>

                                    <div class="row">
                                        <div class="col-sm-3">
                                            <p class="mb-0">Address</p>
                                        </div>
                                        <div class="col-sm-9">
                                            <input style='border: 0px; width:100%;' name="address" class="text-muted mb-0" type="text" value="<%=address%>" >
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row"><!-- comment -->

                                        <div class="list-group-item d-flex justify-content-between align-items-center p-3">
                                            <i class="fab fa-facebook-f fa-lg" style="color: #3b5998;"></i>
                                            <button style="margin: auto; padding: 10px 30px;" class="btn btn-primary">Update</button>
                                        </div>
                                    </div>

                                </form>
                            </div>

                        </div>
                        <%}else{%>
                        <h1>There is nothing here please go back</h1>
                        <%}%>
                        <!--        <div class="row">
                                  <div class="col-md-6">
                                    <div class="card mb-4 mb-md-0">
                                      <div class="card-body">
                                        <p class="mb-4"><span class="text-primary font-italic me-1">assigment</span> Project Status
                                        </p>
                                        <p class="mb-1" style="font-size: .77rem;">Web Design</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 80%" aria-valuenow="80"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">Website Markup</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 72%" aria-valuenow="72"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">One Page</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 89%" aria-valuenow="89"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">Mobile Template</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 55%" aria-valuenow="55"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">Backend API</p>
                                        <div class="progress rounded mb-2" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 66%" aria-valuenow="66"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="col-md-6">
                                    <div class="card mb-4 mb-md-0">
                                      <div class="card-body">
                                        <p class="mb-4"><span class="text-primary font-italic me-1">assigment</span> Project Status
                                        </p>
                                        <p class="mb-1" style="font-size: .77rem;">Web Design</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 80%" aria-valuenow="80"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">Website Markup</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 72%" aria-valuenow="72"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">One Page</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 89%" aria-valuenow="89"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">Mobile Template</p>
                                        <div class="progress rounded" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 55%" aria-valuenow="55"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                        <p class="mt-4 mb-1" style="font-size: .77rem;">Backend API</p>
                                        <div class="progress rounded mb-2" style="height: 5px;">
                                          <div class="progress-bar" role="progressbar" style="width: 66%" aria-valuenow="66"
                                            aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                </div>-->
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
