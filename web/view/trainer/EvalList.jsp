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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js" integrity="sha512-ElRFoEQdI5Ht6kZvyzXhYG9NqjtkmlkfYk0wr6wHxU9JEHakS7UJZNeml5ALk+8IKlU6jDgMabC3vkumRokgJA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <title>SB Admin - Tables</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="${pageContext.request.contextPath}/assets/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/assets/css/sb-admin.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/js/paging/pagingSettingList.js" type="text/javascript"></script>

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
                margin-right: 40px;
            }
            .advanced-search .second{
                margin-left: -30px;
                margin-right: 60px;
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
        </style>

    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="index.html">S-Course</a>

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
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
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
                        <a class="dropdown-item" href="login.html">Setting List</a>
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
                    </ol>
                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Assigment Detail</div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <form method="get" action="ClassEvalList">
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-md-10">
                                            <input type="hidden" value="${param.mid}" name="mid" class="form-control" style="display: inline; width: 150px;margin-left: 15px;">

                                            <input type="text" value="${param.name}" name="name" class="form-control" style="display: inline; width: 150px;margin-left: 15px;">
                                            <select name="status"   class="form-control" style="display: inline; width: 150px;margin-left: 15px;">
                                                <option value="1" ${param.status==0?"selected":""}>Active</option>
                                                <option value="0" ${param.status==1?"selected":""}>Inactive</option>
                                                <option value="" ${param.status==""?"selected":""}>All</option>
                                            </select >
                                            <select name="assId"  class="form-control" style="display: inline; width: 150px;margin-left: 15px;">
                                                <option value="">All</option>
                                                <c:forEach var="e" items="${alist}">
                                                    <option value="${e.id}" ${param.assId==e.id?"selected":""}>${e.title}</option>
                                                </c:forEach>
                                            </select>
                                            <select  name="cid"  class="form-control" style="display: inline; width: 150px;margin-left: 15px;">
                                                <option value="">All</option>
                                                <c:forEach var="e" items="${clist}">
                                                    <option value="${e.classId}" ${param.cid==e.classId?"selected":""}>${e.classCode}</option>
                                                </c:forEach>
                                            </select>
                                            <button type="submit" class="btn btn-primary" style="margin-left: 10px;">Search</button>
                                        </div>
                                        <div class="col-md-2">
                                        </div>

                                    </div>
                                </form>
                                <div class="row">
                                    <div class="col-md-12">
                                        <table class="table table-bordered" width="100%" id="slidetable" >
                                            <thead>
                                                <tr>
                                                    <th>Id</th>
                                                    <th>Name</th>
                                                    <th>Team Eval</th>
                                                    <th> Weight</th>
                                                    <th>Max Loc</th>
                                                    <th>Subject</th>
                                                    <th>Assignment</th>
                                                    <th>Status</th>
                                                    <th>Change Status</th>
                                                    <th>Edit</th>
                                                    <th>Clone</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="a" items="${elist}">
                                                    <tr>
                                                        <td>${a.id}</td>
                                                        <td><a href="./EvalDetail?eid=${a.id}">${a.name}</a></td>
                                                        <td>${a.isTeamEval}</td>
                                                        <td>${a.evalWeight}</td>
                                                        <td>${a.maxLoc}</td>
                                                        <td>${a.assignment.subject.code}</td>
                                                        <td>${a.assignment.title}</td>
                                                        <td>${a.status?"Active":"Inactive"}</td>
                                                        <c:if test="${a.status}">
                                                            <td><a href="./UpdateEvalStatus?eid=${a.id}&status=0&mid=${param.mid}" class="btn btn-danger">Inactive</a></td>
                                                        </c:if>
                                                        <c:if test="${!a.status}">
                                                            <td><a href="./UpdateEvalStatus?eid=${a.id}&status=1&mid=${param.mid}" class="btn btn-success">Active</a></td>
                                                        </c:if>
                                                        <td><a href="./EditEval?eid=${a.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
                                                        <td><a href="./CloneEval?eid=${a.id}"><i class="fa-regular fa-clone"></i></a></td>

                                                    </tr>
                                                </c:forEach>

                                            </tbody>
                                        </table>

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

            <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
            <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
            <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
            <script>
                $(document).ready(function () {
                    $("#slidetable").DataTable({bFilter: false, bInfo: false, lengthChange: false});
                });
            </script>

    </body>
</html>
