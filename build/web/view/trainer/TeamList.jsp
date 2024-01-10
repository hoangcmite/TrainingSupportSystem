<%-- 
    Document   : SettingList
    Created on : Sep 25, 2022, 7:43:10 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "Model.Classes"%>
<%@page import= "dal.DAO"%>
<%@page import= "Model.Team"%>
<%@page import= "Model.UserAccount"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Tables</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="${pageContext.request.contextPath}/assets/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/assets/css/sb-admin.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/js/paging/pagingSettingList.js" type="text/javascript"></script>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .advanced-search{
                display: flex;
                flex-direction: row;
                flex-wrap: wrap;
                margin: 15px;
                /*                justify-content: space-between;*/
            }
            .advanced-search label{
                font-weight: bold;
                color: #333;
            }

            .setting-pagination{
                display: flex;
                justify-content: center;
                margin-top: 20px;

            }
            * {
                margin: 0;
                padding: 0;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
            }

            body {

                font-family: 'Open Sans', Arial, Helvetica, Sans-serif, Verdana, Tahoma;
            }

            ul {
                list-style-type: none;
            }

            p {
                color: white;
                text-decoration: none;
            }
            .dropsublist{
                color: white;
            }

            /** =======================
             * Contenedor Principal
             ===========================*/


            h1 {
                color: #FFF;
                font-size: 24px;
                font-weight: 400;
                text-align: center;
                margin-top: 80px;
            }

            h1 p {
                color: white;
                font-size: 16px;
            }

            .accordion {
                width: 100%;

                margin: 30px auto 20px;
                background: #FFF;
                -webkit-border-radius: 4px;
                -moz-border-radius: 4px;
                border-radius: 4px;
            }

            .accordion .link {
                cursor: pointer;
                display: block;
                padding: 15px 15px 15px 42px;
                color: #4D4D4D;
                font-size: 14px;
                font-weight: 700;
                border-bottom: 1px solid #CCC;
                position: relative;
                -webkit-transition: all 0.4s ease;
                -o-transition: all 0.4s ease;
                transition: all 0.4s ease;
            }

            .accordion li:last-child .link {
                border-bottom: 0;
            }

            .accordion li i {
                position: absolute;
                top: 16px;
                left: 12px;
                font-size: 18px;
                color: #595959;
                -webkit-transition: all 0.4s ease;
                -o-transition: all 0.4s ease;
                transition: all 0.4s ease;
            }

            .accordion li i.fa-chevron-down {
                right: 12px;
                left: auto;
                font-size: 16px;
            }

            .accordion li.open .link {
                color: #b63b4d;
            }

            .accordion li.open i {
                color: #b63b4d;
            }

            .accordion li.open i.fa-chevron-down {
                -webkit-transform: rotate(180deg);
                -ms-transform: rotate(180deg);
                -o-transform: rotate(180deg);
                transform: rotate(180deg);
            }

            /**
             * Submenu
             -----------------------------*/


            .submenu {
                display: none;
                background: white;
                font-size: 14px;
            }

            .submenu li {
                border-bottom: 1px solid #4b4a5e;
            }

            .submenu p {
                display: block;
                text-decoration: none;
                color: black;
                padding: 12px;
                padding-left: 42px;
                -webkit-transition: all 0.25s ease;
                -o-transition: all 0.25s ease;
                transition: all 0.25s ease;
            }

            .submenu p:hover {
                background: #b63b4d;
                color: #FFF;
            }
            .page-link:hover{
                color : white !important;
            }

            .advanced-search .advanced-search-item{
                flex-basis: 32%;
            }
            .advanced-search .first{

            }
            .advanced-search .second{
                margin-left: -30px;
            }
            .advanced-search .three{
                margin-left: -60px;

            }
            .advanced-search .four{
                margin-top: -38px;
                margin-left: 90%;
            }

            .advanced-search-submit{
                display: flex;
                /*                justify-content: center;*/
                margin-top: 10px;
                margin-bottom: 10px;
            }


            @media (max-width: 900px){
                .advanced-search .advanced-search-item{
                    flex-basis: calc(50% - 50px);
                }
            }
            @media (max-width: 600px){
                .advanced-search .advanced-search-item{
                    flex-basis: 100% !important;
                }
            }
            /* The switch - the box around the slider */
            .switch {
                position: relative;
                display: inline-block;
                width: 30px;
                height: 17px;
                margin-top: 14px;
                margin-right: 5px;
            }

            /* Hide default HTML checkbox */
            .switch input {
                opacity: 0;
                width: 0;
                height: 0;
            }

            /* The slider */
            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 17px;
                width: 17px;
                left: 0px;
                bottom: 0px;
                background-color: black;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #2196F3;
            }

            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }

            input:checked + .slider:before {
                -webkit-transform: translateX(17px);
                -ms-transform: translateX(17px);
                transform: translateX(17px);
            }

            /* Rounded sliders */
            .slider.round {
                border-radius: 34px;
            }

            .slider.round:before {
                border-radius: 50%;
            }

            /* Style buttons */
            .btn {
                background-color: DodgerBlue; /* Blue background */
                border: none; /* Remove borders */
                color: white; /* White text */
                padding: 0px 8px; /* Some padding */
                font-size: 12px; /* Set a font size */
                cursor: pointer; /* Mouse pointer on hover */
                margin-bottom: 14px;
            }

            /* Darker background on mouse-over */
            .btn:hover {
                background-color: RoyalBlue;
            }

            .dropbtn {
                background-color: #3498DB;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }


            .dropbtn:hover, .dropbtn:focus {
                background-color: #2980B9;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content p {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown p:hover {
                background-color: #ddd;
            }

            .show {
                display: block;
            }
            #main {
                width: 100%;
                height: 100px;

                display: flex;
                justify-content: space-evenly;
            }

            #main div {
                width: 100%;
            }
            .dropdown {
                position: relative;
                display: inline-block;
            }
            .nut_dropdown {
                background-color: #CCC;
                color: black;
                padding: 6px;
                font-size: 16px;
                border: none;
            }


            /* Nội dung Dropdown */
            .noidung_dropdown {
                /*Ẩn nội dụng các đường dẫn*/
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            /* Thiết kế style cho các đường dẫn tronng Dropdown */
            .noidung_dropdown a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* thay đổi màu background khi hover vào đường dẫn */
            .noidung_dropdown a:hover {
                background-color: #ddd;
            }

            /* hiển thị nội dung dropdown khi hover */
            .dropdown:hover .noidung_dropdown {
                display: block;
            }

            /* Thay đổi màu background cho nút khi được hover */
            .dropdown:hover .nut_dropdown {
                background-color: #00bfff;
            }

            /*Dùng Để hiển thị nội dung*/
            .hienThi {
                display:block;
            }
        </style>
        <script src="jquery-3.6.0.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="/WebApplication2/Home.jsp">S-Course</a>

            <button style="margin-top: 15px; background-color: black;" class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
                <i class="fas fa-bars" ></i>
            </button>

            <!-- Navbar Search -->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input style="margin-top: 10px; font-size: 8px" type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append" style="margin-top: 10px">
                        <button style="font-size: 17px" class="btn btn-primary" type="button">
                            <i class="fas fa-search""></i>
                        </button>
                    </div>
                </div>
            </form>

            <!-- Navbar -->
            <ul class="navbar-nav ml-auto ml-md-0">
                <li class="nav-item dropdown no-arrow mx-1">
                    <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-bell fa-fw"></i>
                        <span class="badge badge-danger">9+</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item dropdown no-arrow mx-1">
                    <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-envelope fa-fw"></i>
                        <span class="badge badge-danger">7</span>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-circle fa-fw"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#">Settings</a>
                        <a class="dropdown-item" href="#">Activity Log</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/Home.jsp" data-toggle="modal" data-target="#logoutModal">Logout</a>
                    </div>
                </li>
            </ul>

        </nav>

        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="sidebar navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="Dashboard.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>System Admin</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <h6 class="dropdown-header">System List:</h6>
                        <a class="dropdown-item" href="/WebApplication2/admin/setting">Setting List</a>
                        <a class="dropdown-item" href="register.html">User List</a>
                        <a class="dropdown-item" href="/WebApplication2/admin/ClassSetting">Class Setting List</a>
                        <div class="dropdown-divider"></div>
                        <h6 class="dropdown-header">System Detail:</h6>
                        <a class="dropdown-item" href="../../assets/blank.html">Setting Detail</a>
                        <a class="dropdown-item" href="404.html">404 Page</a>
                        <a class="dropdown-item" href="blank.html">Blank Page</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>System Manage</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <h6 class="dropdown-header">System List:</h6>
                        <a class="dropdown-item" href="/WebApplication2/manager/assignment">Assignment List</a>
                        <div class="dropdown-divider"></div>
                        <h6 class="dropdown-header">System Detail:</h6>
                        <a class="dropdown-item" href="404.html">404 Page</a>
                        <a class="dropdown-item" href="blank.html">Blank Page</a>
                    </div>
                </li>
            </ul>

            <div id="content-wrapper">

                <div class="container-fluid">

                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="Dashboard.jsp">Dashboard</a>
                        </li>
                        <li class="breadcrumb-item active">Team List</li>
                    </ol>

                    <!--Form search-->

                    <div class="advanced-search">
                        <div class = "advanced-search-item first" id="search-1">


                            <%   DAO dao = new DAO();
                                    HttpServletRequest req = (HttpServletRequest) request;
                                     ArrayList<Classes> lstcl = new ArrayList<Classes>();
                                     lstcl = (ArrayList<Classes>) req.getAttribute("classes");
                                 if(lstcl.size()>0) {
                            %>

                            <label for="type" class="mr-sm-2">Search by Class Code</label>
                            <form id="frm" action = "TeamList" method="GET">
                                <select class="form-control"  name = "classid" style="width: 280px"  onchange="this.form.submit()">

                                    <%
                                            for(int j=0;j<lstcl.size();j++){
                                            Classes cl =lstcl.get(j);
                                    %>
                                    <option <%=((int)req.getAttribute("classidselect")==cl.getClassId()) ? "selected" :""%> value="<%=cl.getClassId()%>"><%=cl.getClassCode()%></option>

                                    <%
                                        }
                                    
                                    %>

                                </select>

                            </form>
                            <form action = "TeamList" method="GET">
                                <input type="text" hidden name="classid" value="<%=(int)req.getAttribute("classidselect")%>">
                                </div>
                                <div class = "advanced-search-item second" id="search-2">
                                    <label for="status" class="mr-sm-2">Search by Milestone </label>
                                    <select class="form-control"  id="status" name = "milestoneid" style="width: 250px">

                                        <%  
                                       
                                              int[] msid = (int[]) req.getAttribute("milestone");
                                             
                                            for(int j=0;j<msid.length;j++){
                                            int mid = msid[j];
                                            int tt = j+1;
                                            int temp = (int)req.getAttribute("milestoneidselect");
                                            if(mid!=0){
                                        %>
                                        <option <%=(temp==mid) ? "selected" :""%> value="<%=mid%>">Milestone <%=j+1%></option>

                                        <%}
                                        } %>
                                        <!--
                                        <option ${requestScope.status == "1"?"selected = selected":""} value = "1">Active</option>
                                        <option ${requestScope.status == "0"?"selected = selected":""} value = "0">Inactive</option>-->

                                    </select>
                                </div>
                                <div class = "advanced-search-item second" id="search-2">
                                    <label for="status" class="mr-sm-2">Search by Status </label>
                                    <select class="form-control" id="status" name = "status" style="width: 250px">
                                        <%int status = 0;
                                    if(req.getAttribute("status")!=null)status=(int)req.getAttribute("status");%>
                                        <option <%=(status==2) ? "selected" :""%> value ="2">Select the status to search</option>
                                        <option <%=(status==1) ? "selected" :""%> value="1">Active</option>
                                        <option <%=(status==0) ? "selected" :""%> value="0">Inactive</option>
                                        <!--
                                        <option ${requestScope.status == "1"?"selected = selected":""} value = "1">Active</option>
                                        <option ${requestScope.status == "0"?"selected = selected":""} value = "0">Inactive</option>-->
                                    </select>
                                </div>
                                <div class = "advanced-search-item four" id="search-4">
                                    <button class="btn btn-danger" type="submit" style="height: 38px; font-size: 15px">Search</button>
                                </div>
                        </div>
                        <% 
                                ArrayList<Team> lstteam = new ArrayList<Team>();   
                           lstteam = (ArrayList<Team>) req.getAttribute("lstteam");
                            if(lstteam.size()!=0){
                        %>
                        <h1 style="color:orange;">This milestone has teams already.Click <a href="Dashboard.jsp">HERE</a> to regroup</h1>

                        <%}else{
                        %>
                        <h1 style="color:orange;">Trainer have not been grouped.Click <a href="Dashboard.jsp">HERE</a> to create teams</h1>

                        <%
                                           }%>

                        </form>
                        <!-- DataTables Example -->
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fas fa-table"></i>
                                Team List</div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" width="100%" cellspacing="0">    
                                        <table class="table table-bordered" width="100%" cellspacing="0">
                                            <thead>
                                                <tr class = "bg-warning" style="text-align: center;">

                                                    <td style="text-align: left;" class = "geeks">ID</td>

                                                    <td style="text-align: center; padding-left :30px;" class = "geeks">Name</td>


                                                    <td style="padding:10px 30px;" class = "geeks">Email</td>


                                                    <td style="text-align: left;" class = "geeks">Position</td>


                                                    <td style="padding-left: 30px;text-align: left;" class = "geeks">Status</td>


                                                </tr>
                                            </thead>
                                        </table>
                                        <tbody>
                                               
                                        
                                        <ul id="accordion" class="accordion" style="width:100%;">
                                                <li>
                                                <div class="link"><i class="fa fa-database">

                                                    </i>Waiting List<i class="fa fa-chevron-down"></i></div>

                                                <ul class="submenu">
                                                    <!--                                                    <li><a href="#">Photoshop</a></li>
                                                                                                        <li><a href="#">HTML</a></li>
                                                                                                        <li><a href="#">CSS</a></li>-->


                                                    <%
                                                         ArrayList<UserAccount> lstwatingmember = new ArrayList<UserAccount>();
                                                         lstwatingmember = dao.getWaitingMember((int)req.getAttribute("classidselect"),(int)req.getAttribute("milestoneidselect"));
                                                        for(int k=0;k<lstwatingmember.size();k++){
                                                             UserAccount tmember = lstwatingmember.get(k);
                                                    %>
                                                  
                                                    <li >
                                                        <div class="dropdown">
                                                            <button class="nut_dropdown">Action</button>
                                                            <div class="noidung_dropdown">
                                                                <div class="dropdown">
                                                            <button class="nut_dropdown">Add to Team</button>
                                                            <div class="noidung_dropdown">
                                                             <%
                                       for(int j=0;j<lstteam.size();j++){
                                      Team team = lstteam.get(j);%>    
                                                                
                                                            <a href="/WebApplication2/TeamList?teamID=<%=team.getId()%>&&joinerID=<%=tmember.getId()%>&classid=<%=(int)req.getAttribute("classidselect")%>&milestoneid=<%=(int)req.getAttribute("milestoneidselect")%>&status=<%=(int)req.getAttribute("status")%>">Add to Team <%=team.getName()%> </a>
<%}%>

                                                           
                                                            </div>
                                                        </div>


                                                           
                                                            </div>
                                                        </div>
                                                        <div id="main">
                                                            <div> <%=tmember.getId()%></div>
                                                            <div ><%=tmember.getFullname()%></div>
                                                            <div > <%=tmember.getMail()%></div>
                                                            <div > <%if(tmember.getIsleader()==1){%>
                                                                Leader
                                                                <%}else{%>
                                                                Member
                                                                <%}%></div>
                                                            <div > <%if(tmember.getStatus().equals("1")){%>
                                                                Active
                                                                <%}else{%>
                                                                Inactive
                                                                <%}%>  </div>
                                                        </div>



                                                    </li><%}%>
                                                </ul>
                                            </li>
                                            <%
                                       for(int j=0;j<lstteam.size();j++){
                                      Team team = lstteam.get(j);
                                     
                                        
                                     
                                            %>
                                          

                                            <li>
                                                <div class="link"><i class="fa fa-database">

                                                    </i><%=team.getName()%> <div class="dropdown">
                                                        <button class="nut_dropdown">Action</button>
                                                        <div class="noidung_dropdown">
                                                            <a href="#">Add new Trainee</a>
                                                            <a href="#">Add new</a>
                                                            <a href="#">Team Details</a>
                                                            <%if(status==1){%>
                                                            <a href="/WebApplication2/TeamList?teamID=<%=team.getId()%>&doa=0&classid=<%=(int)req.getAttribute("classidselect")%>&milestoneid=<%=(int)req.getAttribute("milestoneidselect")%>&status=<%=(int)req.getAttribute("status")%>">Deactive Team</a>
                                                            <%}else{%>
                                                            <a href="/WebApplication2/TeamList?teamID=<%=team.getId()%>&doa=1&classid=<%=(int)req.getAttribute("classidselect")%>&milestoneid=<%=(int)req.getAttribute("milestoneidselect")%>&status=<%=(int)req.getAttribute("status")%>">Active Team</a>

                                                            <%}%>
                                                        </div>
                                                    </div><i class="fa fa-chevron-down"></i></div>

                                                <ul class="submenu">
                                                    <!--                                                    <li><a href="#">Photoshop</a></li>
                                                                                                        <li><a href="#">HTML</a></li>
                                                                                                        <li><a href="#">CSS</a></li>-->



                                                    <%
                                                         ArrayList<UserAccount> lstteammeber = new ArrayList<UserAccount>();
                                                         lstteammeber = dao.getTeamMember(team.getId());
                                                           for(int k=0;k<lstteammeber.size();k++){
                                                             UserAccount tmember = lstteammeber.get(k);
                                                    %>
                                                    <li >
                                                        <div class="dropdown">
                                                            <button class="nut_dropdown">Action</button>
                                                            <div class="noidung_dropdown">
                                                            <a href="/WebApplication2/TeamList?teamID=<%=team.getId()%>&memberID=<%=tmember.getId()%>&classid=<%=(int)req.getAttribute("classidselect")%>&milestoneid=<%=(int)req.getAttribute("milestoneidselect")%>&status=<%=(int)req.getAttribute("status")%>">Remove from Team</a>

                                                           <a href="/WebApplication2/TeamList?teamID=<%=team.getId()%>&leaderID=<%=tmember.getId()%>&classid=<%=(int)req.getAttribute("classidselect")%>&milestoneid=<%=(int)req.getAttribute("milestoneidselect")%>&status=<%=(int)req.getAttribute("status")%>">Set as Leader Team</a>

                                                           
                                                            </div>
                                                        </div>
                                                        <div id="main">
                                                            <div> <%=tmember.getId()%></div>
                                                            <div ><%=tmember.getFullname()%></div>
                                                            <div > <%=tmember.getMail()%></div>
                                                            <div > <%if(tmember.getIsleader()==1){%>
                                                                Leader
                                                                <%}else{%>
                                                                Member
                                                                <%}%></div>
                                                            <div > <%if(tmember.getStatus().equals("1")){%>
                                                                Active
                                                                <%}else{%>
                                                                Inactive
                                                                <%}%>  </div>
                                                        </div>



                                                    </li>

                                                    <!--                                                    <div class="dropdown-header">
                                                                                                            <tr >
                                                                                                                <td><%=tmember.getId()%></td>
                                                                                                                <td><%=tmember.getMail()%></td>
                                                                                                                <td><%=tmember.getFullname()%></td>  <td>cell</td> 
                                                                                                                <td><%=tmember.getIsleader()%></td>
                                                                                                                <td><%=tmember.getStatus()%></td>
                                                                                                            </tr>
                                                                                                        </div>
                                                    </table>
                                                    
                                                                                                </div>
                                                                                            </li>-->

                                                    <%}%>
                                                </ul>
                                            </li>
                                            <%}%>
                                        </ul>
                                        </tbody>
                                    </table>
                                    <!-- This is pagination -->
                                    <div class = "setting-pagination">
                                        <ul class="pagination" id = "paggerBottom">

                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}else{%>
                    You don't manage any class yet.     
                    Please Contract to Manager!
                    <%}%>
                    <!-- /.container-fluid -->

                    <!-- Sticky Footer -->
                    <footer class="sticky-footer">
                        <div class="container my-auto">
                            <div class="copyright text-center my-auto">
                                <span>Copyright © Your Website 2019</span>
                            </div>
                        </div>
                    </footer>

                </div>
                <!-- /.content-wrapper -->

            </div>
            <!-- /#wrapper -->

            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fas fa-angle-up"></i>
            </a>

            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <a class="btn btn-primary" href="login.html">Logout</a>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bootstrap core JavaScript-->
            <script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="${pageContext.request.contextPath}/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Page level plugin JavaScript-->
            <script src="${pageContext.request.contextPath}/assets/vendor/datatables/jquery.dataTables.js"></script>
            <script src="${pageContext.request.contextPath}/assets/vendor/datatables/dataTables.bootstrap4.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="${pageContext.request.contextPath}/assets/js/sb-admin.min.js"></script>

            <!-- Demo scripts for this page-->
            <script src="${pageContext.request.contextPath}/assets/js/demo/datatables-demo.js"></script>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/simple-notify@0.5.4/dist/simple-notify.min.js"></script>

            <script>

                                    $(function () {
                                        var Accordion = function (el, multiple) {
                                            this.el = el || {};
                                            this.multiple = multiple || false;

                                            // Variables privadas
                                            var links = this.el.find('.link');
                                            // Evento
                                            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
                                        }

                                        Accordion.prototype.dropdown = function (e) {
                                            var $el = e.data.el;
                                            $this = $(this),
                                                    $next = $this.next();

                                            $next.slideToggle();
                                            $this.parent().toggleClass('open');

                                            if (!e.data.multiple) {
                                                $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
                                            }
                                            ;
                                        }

                                        var accordion = new Accordion($('#accordion'), false);
                                    });



            </script>
    </body>
</html>
