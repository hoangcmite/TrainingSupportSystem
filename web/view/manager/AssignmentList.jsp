<%-- 
    Document   : SettingList
    Created on : Sep 25, 2022, 7:43:10 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        </style>

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
                    <a class="nav-link" href="index.html">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-fw fa-folder"></i>
                        <span>System Manage</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <h6 class="dropdown-header">System List:</h6>
                        <a class="dropdown-item" href="/WebApplication2/admin/ClassSetting">Class Setting List</a>
                        <a class="dropdown-item" href="/WebApplication2/manager/assignment">Assignment List</a>
                    </div>
                </li>
                
            </ul>

            <div id="content-wrapper">

                <div class="container-fluid">

                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">Dashboard</a>
                        </li>
                        <li class="breadcrumb-item active">Assignment List</li>
                    </ol>
                    <a href="AssignmentAdd" class="btn btn-primary">Add new</a>
                    <!--Form search-->
                    <form action = "assignment" method="GET">
                        <div class="advanced-search">
                            <div class = "advanced-search-item first" id="search-1">
                                <label for="type" class="mr-sm-2">Filter by Subject</label>
                                <select class="form-control" id="subject" name = "subject" style="width: 280px">
                                    <option value>Select the subject to search</option>
                                    <c:forEach items="${requestScope.listSubject}" var="t" >
                                        <option ${requestScope.subjectId == t.subject_id?"selected = selected":""} value = "${t.subject_id}">${t.subject_code}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class = "advanced-search-item second" id="search-2">
                                <label for="status" class="mr-sm-2">Filter by Status</label>
                                <select class="form-control" id="status" name = "status" style="width: 250px">
                                    <option value>Select the status to search</option>
                                    <option ${requestScope.status == "1"?"selected = selected":""} value = "1">Active</option>
                                    <option ${requestScope.status == "0"?"selected = selected":""} value = "0">Inactive</option>
                                </select>
                            </div>
                            <div class = "advanced-search-item three" id="search-3">
                                <label for="title" class="mr-sm-2">Search by Title</label>
                                <input value="${requestScope.title}" type="text" class="form-control" name ="title"  id="title" placeholder="Search..." >
                            </div>
                            <div class = "advanced-search-item four" id="search-4">
                                <button class="btn btn-danger" type="submit" style="height: 38px; font-size: 15px">Search</button>
                            </div>
                        </div>
                    </form>
                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Assignment List</div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>
                                        <tr class = "bg-warning" style="text-align: center;">
                                            <th scope="col">
                                                <%-- <i class="fa fa-sort" aria-hidden="true"></i>--%>
                                                <span style="cursor: pointer" onclick="sort(${requestScope.sortId})"> ID
                                                    <c:if test="${requestScope.sortId == 0}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortId == 1}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>                      
                                            </th>
                                            <th scope="col">
                                                <span style="cursor: pointer" onclick ="sort(${requestScope.sortSubject})"> Subject Code
                                                    <c:if test="${requestScope.sortSubject == 2}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortSubject == 3}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>
                                            </th>
                                            <th scope="col">
                                                <span style="cursor: pointer" onclick ="sort(${requestScope.sortTitle})"> Title
                                                    <c:if test="${requestScope.sortTitle == 4}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortTitle == 5}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>
                                            </th>
                                            <th scope="col">
                                                <span style="cursor: pointer" onclick ="sort(${requestScope.sortEvalWeight})"> Eval Weight
                                                    <c:if test="${requestScope.sortEvalWeight == 6}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortEvalWeight == 7}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>
                                            </th>
                                            <th scope="col">
                                                <span style="cursor: pointer" onclick ="sort(${requestScope.sortTeamWork})"> Is Team Work
                                                    <c:if test="${requestScope.sortTeamWork == 8}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortTeamWork == 9}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>
                                            </th>
                                            <th scope="col">
                                                <span style="cursor: pointer" onclick ="sort(${requestScope.sortOnGoing})"> Is On Going
                                                    <c:if test="${requestScope.sortOnGoing == 10}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortOnGoing == 11}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>
                                            </th>
                                            <th scope="col">
                                                <span style="cursor: pointer" onclick ="sort(${requestScope.sortStatus})"> Status
                                                    <c:if test="${requestScope.sortStatus == 12}">
                                                        <i class="fa fa-sort-down" aria-hidden="true"></i>
                                                    </c:if>
                                                    <c:if test="${requestScope.sortStatus == 13}">
                                                        <i class="fa fa-sort-up" aria-hidden="true"></i>
                                                    </c:if>
                                                </span>
                                            </th>
                                            <th scope="col">
                                                Action
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listAss}" var="s">
                                            <tr>
                                                <td style="text-align: center">${s.ass_id}</td>
                                                <td><a href="AssignmentDetail?aid=${s.ass_id}"> ${s.subject_code}</a></td>
                                                <td>${s.ass_title}</td>
                                                <td style="text-align: center">${s.eval_weight}</td>
                                                <td style="text-align: center">${s.team_work}</td>
                                                <td style="text-align: center">${s.ongoing}</td>
                                                <c:if test="${s.status == false}">
                                                    <td style="text-align: center" id="change">
                                                        <p style="color: red;">Inactive</p>
                                                    </td>
                                                </c:if>
                                                <c:if test="${s.status == true}">
                                                    <td style="text-align: center" id="change">
                                                        <p style="color: green;">Active</p>
                                                    </td>
                                                </c:if>
                                                <td style="text-align: center">
                                                    <c:if test="${s.status == false}">
                                                        <label class="switch">
                                                            <input type="checkbox" onclick="active(${s.ass_id})">
                                                            <span class="slider round"></span>
                                                        </label>
                                                        <%--<button class="btn btn-outline-danger" type="button" onclick="active(${s.setting_id})" style="width: 85px; border-radius: 15px;">Activate</button>--%>
                                                    </c:if>
                                                    <c:if test="${s.status == true}">
                                                        <label class="switch">
                                                            <input checked type="checkbox" onclick="inactive(${s.ass_id})">
                                                            <span class="slider round"></span>
                                                        </label>
                                                        <%--<button class="btn btn-success" type="button" onclick="inactive(${s.setting_id})" style="width: 85px; border-radius: 15px; ">Deactivate</button>--%>
                                                    </c:if>
                                                    <a class="btn" href="AssignmentEdit?aid=${s.ass_id}" ><i class="fas fa-edit"></i></a>        
                                                </td>     
                                            </tr>
                                        </c:forEach>
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
                                                        if (${requestScope.listAss.size() > 0}) {
                                                            pagger_RegistrationList("paggerBottom", 2,${requestScope.totalPage},${requestScope.pageIndex}, '${requestScope.url}');
                                                        }
                                                        function editAssList(id) {
                                                            window.location.href = "../manager/assignment?go=detail&id=" + id;
                                                        }
//                                                        function addSettingList() {
//                                                            window.location.href = "../admin/setting?go=add";
//                                                        }
                                                        function sort(SortId) {
                                                            var url = 'assignment?sort=' + SortId + '&subjecte=${requestScope.subjectId}&status=${requestScope.status}&title=${requestScope.title}&page=${requestScope.pageIndex}';
                                                            window.location.href = url;
                                                        }
                                                        function alert(_status, _title, _text) {
                                                            new Notify({
                                                                status: _status,
                                                                title: _title,
                                                                text: _text,
                                                                effect: "slide",
                                                                speed: 300,
                                                                customClass: "",
                                                                customIcon: "",
                                                                showIcon: true,
                                                                showCloseButton: true,
                                                                autoclose: true,
                                                                autotimeout: 3000,
                                                                gap: 20,
                                                                distance: 20,
                                                                type: 1,
                                                                position: "right top",
                                                                customWrapper: "",
                                                            });
                                                        }
                                                        function active(id)
                                                        {
                                                            swal({
                                                                title: "Are you sure?",
                                                                text: "You want update action ACTIVE Setting (ID " + id + ")!",
                                                                icon: "warning",
                                                                buttons: true,
                                                                dangerMode: true,
                                                            })
                                                                    .then((willDelete) => {
                                                                        if (willDelete) {
                                                                            alert("success", "Success!", "Update Inactivate Successfully!")
                                                                            setTimeout(function () {
                                                                                window.location.href = "/WebApplication2/manager/assignment?go=active&id=" + id;
                                                                            }, 1000)
                                                                        } else {
                                                                            window.location.href = "/WebApplication2/manager/assignment";
                                                                        }
                                                                    });
                                                        }

                                                        function inactive(id)
                                                        {
                                                            swal({
                                                                title: "Are you sure?",
                                                                text: "You want update action INACTIVE Setting (ID " + id + ")!",
                                                                icon: "warning",
                                                                buttons: true,
                                                                dangerMode: true,
                                                            })
                                                                    .then((willDelete) => {
                                                                        if (willDelete) {
                                                                            alert("success", "Success!", "Update Activate Successfully!")
                                                                            setTimeout(function () {
                                                                                window.location.href = "/WebApplication2/manager/assignment?go=inactive&id=" + id;
                                                                            }, 1000)
                                                                        } else {
                                                                            window.location.href = "/WebApplication2/manager/assignment";
                                                                        }
                                                                    });
                                                        }
        </script>
    </body>
</html>
