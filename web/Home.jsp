<%-- 
    Document   : Home
    Created on : Sep 22, 2022, 9:52:07 PM
    Author     : Legon
--%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="jakarta.servlet.http.HttpServletResponse"%>

<%@page import="Model.UserAccount"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <script src="js/main.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
              integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body> 
        ${sad}
        <%String id = "tyugyugy";
        String test="";
        UserAccount uac = new UserAccount();
                    HttpServletRequest req = (HttpServletRequest) request;
                     HttpSession ses = req.getSession();
                 
                     if(req.getAttribute("test")!=null){
                     try{
                      test = (int)req.getAttribute("test")+"";
            }catch(Exception e){
                         test = (String)req.getAttribute("test")+"";
            }
                 
        %>


        <%
            }
                 
                     if(req.getAttribute("type")!=null){
                 
                    if((int)req.getAttribute("type")==1){
        %>
        <h5 style="color:red; margin-left: 45%">You Need to Login First</h5>


        <%}
                     
        if((int)req.getAttribute("type")==2){
        %>
        <h5 style="color:red; margin-left: 45%">You had been login already</h5>


        <%}
           if((int)req.getAttribute("type")==3){
        %>
        <h5 style="color:red; margin-left: 45%">Your account does not have permission to access this page! </h5>


        <%}
             if((int)req.getAttribute("type")==4){
        %>
        <h5 style="color:red; margin-left: 45%">This page have been deactive please try another!</h5>


        <%}
           if((int)req.getAttribute("type")==5){
        %>
        <h5 style="color:red; margin-left: 45%">This page does not exist please try another!</h5>


        <%}
}
                 
         int role=0;
       if(ses.getAttribute("user")==null){
        %>
        <jsp:include page="./header.jsp" />
        <%}
         else{ 
           
             uac = (UserAccount)ses.getAttribute("user");
             role= uac.getId();
 //        uac = (UserAccount)ses.getAttribute("user");
 //            role= Integer.parseInt(uac.getRole());
 //        if(req.getParameter("id")!=null){
 //                 
 //                 
 //                       id =(String) req.getParameter("id");
 //                   }
 //
 //        if(req.getAttribute("id")!=null){
 //                 
 //                 
 //                       id =(String)req.getAttribute("id");
 //                   }
 //                if(ses.getAttribute("id")!=null){
 //                 
 //                 
 //                       id =(String)ses.getAttribute("id");
 //                   }
          
        %>


        <%--<%=id%>--%>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">
                <img src="https://9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-1.jpg" width="30" height="30" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                        </form>
                    </li>
                </ul>
                <!--                <img src="https://9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-1.jpg" width="40" height="40"
                                     style="border-radius: 60px;">-->
                <li class="nav-item dropdown " style="list-style: none; margin-right: 30px;" >
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <img src="https://9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-1.jpg" width="40" height="40" alt="">
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/WebApplication2/Dashboard">DashBoard</a>
                        <a class="dropdown-item" href="/WebApplication2/UserProfile?id=<%=role%>">User Profile</a>
                        <a class="dropdown-item" href="/WebApplication2/ChangePassword?mail=${sessionScope.user.getMail()}">Change Password</a>

                        <div class="dropdown-divider"></div>

                        <a class="dropdown-item" href="/WebApplication2/Logout">Logout</a>
                    </div>
                </li>
            </div>
        </nav>

        <%}%>
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">

            <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">

                <div class="carousel-inner">
                    <div class="carousel-item active" data-bs-interval="10000">
                        <img src="https://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg"
                             class="d-block w-100"
                             alt="https://www.gettyimages.pt/gi-resources/images/Homepage/Hero/PT/PT_hero_42_153645159.jpg">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>First slide label</h5>
                            <p>Some representative placeholder content for the first slide.</p>
                        </div>
                    </div>
                    <div class="carousel-item" data-bs-interval="2000">
                        <img src="https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg"
                             class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Second slide label</h5>
                            <p>Some representative placeholder content for the second slide.</p>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="https://i.ytimg.com/vi/7oIAs-0G4mw/maxresdefault.jpg" class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5>Third slide label</h5>
                            <p>Some representative placeholder content for the third slide.</p>
                        </div>
                    </div>
                </div>
               
            </div>


            <div class="album py-5 bg-light">
                <div class="container">

                    <div class="row">
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 box-shadow">
                                <img class="card-img-top"
                                     src="https://opengraph.githubassets.com/62523757aea01ed22e0894d699653146a38fef5398a2d4d6e40cb8c71cc2e08f/quizit-online/extension"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <p class="card-text">Best cheats for Quizizz, Kahoot, Wordwall, Liveworksheets and more! Quizit allows you to easily pass all of your online exams.
                                        lead-in to additional content. This content is a little bit longer.</p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-outline-secondary">View</button>
                                            <button type="button" class="btn btn-outline-secondary">Take Quiz</button>
                                        </div>
                                        <small class="text-muted">9 mins</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="./footer.jsp" />




            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
                    integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
                    integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    </body>

</html>