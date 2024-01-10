<%-- 
    Document   : SubjectDetail
    Created on : Oct 3, 2022, 10:01:24 PM
    Author     : dell
--%>

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

        <title>Add Schedule</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="${pageContext.request.contextPath}/assets/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/assets/css/sb-admin.css" rel="stylesheet">


        <style>
            .advanced-search{
                display: flex;
                /*                flex-direction: row;
                                flex-wrap: wrap;
                                margin: 5px;*/
                width: 1500px;
                margin-bottom: 10px;

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
                margin-left: 20px;
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


            .advanced-search button{
                padding-bottom: 5px;
                margin-top: 31px;
                height: 38px;

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
        </style>

    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="/WebApplication2/Home.jsp">S-Course</a>

            <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
                <i class="fas fa-bars"></i>
            </button>

            <!-- Navbar Search -->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button">
                            <i class="fas fa-search"></i>
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
                        <a class="dropdown-item" href="/WebApplication2/Home.jsp" data-toggle="modal" data-target="#logoutModal">Logout</a>
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
                        <span>Pages</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <h6 class="dropdown-header">System List:</h6>
                        <a class="dropdown-item" href="/WebApplication2/admin/setting">Setting List</a>
                        <a class="dropdown-item" href="register.html">User List</a>
                        <div class="dropdown-divider"></div>
                        <h6 class="dropdown-header">System Detail:</h6>
                        <a class="dropdown-item" href="../../assets/blank.html">Setting Detail</a>
                        <a class="dropdown-item" href="404.html">404 Page</a>
                        <a class="dropdown-item" href="blank.html">Blank Page</a>
                    </div>
                </li>
                <!--                <li class="nav-item">
                                    <a class="nav-link" href="charts.html">
                                        <i class="fas fa-fw fa-chart-area"></i>
                                        <span>Charts</span></a>
                                </li>
                                <li class="nav-item active">
                                    <a class="nav-link" href="tables.html">
                                        <i class="fas fa-fw fa-table"></i>
                                        <span>Tables</span></a>
                                </li>-->
            </ul>

            <div id="content-wrapper">

                <div class="container-fluid">

                    <!-- Breadcrumbs-->
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="#">Dashboard</a>
                        </li>
                        <li class="breadcrumb-item active">Schedule List</li>
                        <li class="breadcrumb-item active">Add New Schedule</li>
                    </ol>

                    <!--Form edit-->

                    <form action="AddSchedule" method="POST">
                        <c:if test="${requestScope.mess eq null}">
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Subject <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <select style="width: 450px;" class="form-control" name="sid" required>
                                        <option value="" disabled selected hidden>Subject</option>
                                        <c:forEach items="${lsubject}" var="s">
                                            <option value = "${s.subjectId}">${s.getSubjectCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Class Code <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <select style="width: 450px;" class="form-control" name="cid" required>
                                        <option value="" disabled selected hidden>Class Code</option>
                                        <c:forEach items="${clist}" var="u">
                                            <option value = "${u.classId}">${u.getClassCode()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Date <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <input style="width: 450px;" value="" name="dateStudy" type="date" class="form-control" id="inputEmail3" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Slot <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <input style="width: 450px;" value="" name="slot" type="number" class="form-control" id="inputEmail3" min="1" max="30" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">From (AM) <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <input required title="Wrong format!" style="width: 450px;" value="" name="from" type="time" class="form-control" id="inputEmail3" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">To (PM) <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <input required title="Wrong format!" style="width: 450px;" value="" name="to" type="time" class="form-control" id="inputEmail3" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail3" class="col-sm-2 col-form-label">Room <code>(*)</code></label>
                                <div class="col-sm-10">
                                    <select style="width: 450px;" class="form-control" name="room" required>
                                        <option value="" disabled selected hidden>Room</option>
                                        <c:forEach items="${rl}" var="r">
                                            <option value = "${r.room}">${r.getRoom()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.mess ne null}">
                            <div class="alert alert-success" role="alert">
                                ${mess} 
                                <a href="/WebApplication2/ScheduleList" class="alert-link">Return to Schedule List screen</a>
                            </div>
                        </c:if>



                        <c:if test="${requestScope.mess eq null}">
                            <div style="display: flex;">
                                <div class="form-group row">
                                    <div style="padding-right: 25px; margin-left: 400px;" class="col-sm-10">
                                        <button type="submit" class="btn btn-primary">Create</button>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-10">
                                        <button style="background-color: gray" onclick="location.href = '/WebApplication2/ScheduleList'" type="button" class="btn btn-primary">Cancel</button>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </form>

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

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
            <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

            <script>
                                        var count = 3;
                                        function redirect()
                                        {
                                            count--;
                                            document.getElementById('timer').innerHTML = count;
                                            if (count <= 0)
                                            {
                                                window.location.href = '/WebApplication2/ScheduleList';
                                            }
                                        }
                                        setInterval(redirect, 1000);
            </script>
    </body>
</html>


