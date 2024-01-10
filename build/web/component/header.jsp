<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%String id = "tyugyugy";
                    HttpServletRequest req = (HttpServletRequest) request;
                    if(req.getAttribute("id")==null){
        %>
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
                <a href="Login.jsp" class="btn btn-outline-success my-2 my-sm-0" style="margin-right: 10px;">Login</a>

                <a href="register.jsp" class="btn btn-outline-success my-2 my-sm-0" style="margin-right: 10px;">Register</a>
            </div>
        </nav>
        <%}
         else{ 
         if(req.getParameter("id")!=null){
                 
                 
                        id =(String) req.getParameter("id");
                    }

         if(req.getAttribute("id")!=null){
                 
                 
                        id =(String)req.getAttribute("id");
                    }
        %>



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
                <img src="https://9mobi.vn/cf/images/2015/04/nkk/hinh-avatar-dep-1.jpg" width="40" height="40"
                     style="border-radius: 60px;">
                <li class="nav-item dropdown btn btn-outline-success ">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Menu
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="./view/admin/dashboard.jsp">DashBoard</a>
                        <a class="dropdown-item" href="/WebApplication2/profile?id=<%=id%>">User Profile</a>


                        <div class="dropdown-divider"></div>

                        <a class="dropdown-item" href="Login.jsp">Logout</a>
                    </div>
                </li>
            </div>
        </nav>

        <%}%>