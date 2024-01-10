<%-- 
    Document   : SettingList
    Created on : Sep 25, 2022, 7:43:10 AM
    Author     : Admin
--%>
<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%@page import="Model.UserAccount"%>
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

        <title>Web Contact List</title>

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
        </style>

    </head>

    <body id="page-top">
  <% int role =0;
    
                     HttpServletRequest req = (HttpServletRequest) request;
                      HttpSession ses = req.getSession();
                      UserAccount uac = new UserAccount();
                
             if(ses.getAttribute("user")!=null){
             uac=(UserAccount) ses.getAttribute("user");
             role = uac.getId();
         }
        %>
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
                        <a class="dropdown-item" href="/WebApplication2/Logout" data-toggle="modal" data-target="#logoutModal">Logout</a>
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
                        <li class="breadcrumb-item active">Web Contact List</li>
                    </ol>


                    <!-- DataTables Example -->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Web Contact List</div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <form action="WebContactDetail" method="POST">
                                    <table class="table table-bordered" width="100%" cellspacing="0">
                                        <thead>
                                            <tr class = "bg-warning">

                                                <th scope="col">
                                                    <span  >Supporter Name<i  aria-hidden="true"></i></span>                      
                                                </th>
                                                <th scope="col">
                                                    <span )"> Name <i aria-hidden="true"></i></span>
                                                    <!--                        <div>
                                                                                    <i class="fa fa-sort-alpha-asc" aria-hidden="true"></i>
                                                                                    <i class="fa fa-sort-alpha-desc" aria-hidden="true"></i>
                                                                                </div>-->
                                                </th>
                                                <th scope="col">
                                                    <span  >Email<i  aria-hidden="true"></i></span>                      
                                                </th>
                                                <th scope="col">
                                                    <span  >Phone number<i  aria-hidden="true"></i></span>                      
                                                </th>
                                                <th scope="col">
                                                    <span  > Message <i  aria-hidden="true"></i></span>
                                                </th>
                                                <th scope="col">
                                                    <span  > Response <i  aria-hidden="true"></i></span>
                                                </th>
                                                <th scope="col">
                                                    <span  > Status <i  aria-hidden="true"></i></span>
                                                </th>

                                            </tr>
                                        </thead>

                                        <tbody>
                                              <c:forEach items="${requestScope.webcs}" var="w">
                                                <tr>
                                                    <c:if test="${empty w.response}">
                                                        <td> 
                                                        </td>
                                                    </c:if>

                                                    <c:if test="${not empty w.response}"> 
                                                        <td>${w.supporterName}</td>
                                                    </c:if>
                                                    <td>${w.fullName}</td>
                                                    <td>${w.email}</td>
                                                    <td>${w.mobile}</td>
                                                    <td>${w.message}</td>

                                                    <c:if test="${empty w.response}">
                                                        <td> <input type="text" contenteditable="true" style="border:none"  name="response" > 

                                                            <input type="hidden" name="id" value="${w.id}">
                                                            <input type="hidden" name="name" value="<%=role%>">
                                                        </td>

                                                    </c:if>

                                                    <c:if test="${not empty w.response}"> 
                                                        <td>   ${w.response} </td>
                                                    </c:if>
                                                    <c:if test="${w.status eq 0}">
                                                        <td>Not Answered </td>
                                                    </c:if>  

                                                    <c:if test="${w.status eq 1 }">
                                                        <td>  Answered </td>
                                                    </c:if>

                                                </tr>
                                            </c:forEach>

                                        </tbody>

                                    </table>

                                    <input type="submit" value="Save" style="border-radius: 8px;" />
                                </form>
                                <div>${message} </div>

                                <!-- This is pagination -->
                                <div class = "setting-pagination">
                                    <ul class="pagination" id = "paggerBottom">
                                        <c:forEach begin="1" end ="${endP}" var="i" >
                                            <a class="page-link" href="WebContactDetail?page=${i}">${i}</a>
                                        </c:forEach>
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
                        <a class="btn btn-primary" href="/WebApplication2/Logout">Logout</a>
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
        <script>
            if (${requestScope.listSetting.size() > 0}) {
                pagger_RegistrationList("paggerBottom", 2,${requestScope.totalPage},${requestScope.pageIndex}, '${requestScope.url}');
            }


        </script>

    </body>
</html>
