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
            label.error{
                color : red !important;
                font-size: 14px;
                font-weight: 500;
            }
        </style>

    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="/WebApplication2/Home.jsp">S-Course</a>

            <button  class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
                <i class="fas fa-bars" ></i>
            </button>

            <!-- Navbar Search -->
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input  class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append" >
                        <button  class="btn btn-primary" type="button">
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
                        <span>System Supporter</span>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                        <h6 class="dropdown-header">System List:</h6>
                        <a class="dropdown-item" href="/WebApplication2/admin/ClassSetting">Class Setting List</a>
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
                            <a href="#">Dashboard</a>
                        </li>
                        <li class="breadcrumb-item active">Trainee Details</li>
                    </ol>
                    <!--onSubmit="return formValidation();"-->
                    <div id="form-detail">
                        <form action="ClassUser" method="GET" class="forms-sample" id="validate-form-Class-Setting-Update" name='registration' >
                            <!--                            -->
                            <input type="hidden" value="update" name="go">
                            <input type="hidden" value="${requestScope.roleID}" name="roleId">
                            <input type="hidden" value="${requestScope.traineeDetail.class_id}" name="class_id">
                            <input type="hidden" value="${requestScope.traineeDetail.user_id}" name="user_id">
                            <table style="margin-left: 50px">
                                <tr>
                                    <td >
                                        <div class="form-group">
                                            <label for="exampleFormControlReadonly">Full Name</label>
                                            <input style="width: 500px" id="fullName" name="fullName" class="form-control" type="text" value="${requestScope.traineeDetail.full_name}" placeholder="${requestScope.traineeDetail.full_name}" readonly>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label for="exampleFormControlReadonly">Email</label>
                                            <input style="width: 500px" id="email" name="email" class="form-control" type="text" value="${requestScope.traineeDetail.user_email}" placeholder="${requestScope.traineeDetail.user_email}" readonly>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label for="exampleFormControlReadonly">Ongoing Evaluation</label>
                                            <input id="ongoing" name="ongoing" class="form-control" type="text" value="${requestScope.traineeDetail.ongoing_eval}" placeholder="${requestScope.traineeDetail.ongoing_eval}" readonly>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label for="exampleFormControlReadonly">Final Evaluation</label>
                                            <input id="final" name="final" class="form-control" type="text" value="${requestScope.traineeDetail.final_eval}" placeholder="${requestScope.traineeDetail.final_eval}" readonly>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label for="exampleFormControlReadonly">Topic Evaluation</label>
                                            <input id="topic" name="topic" class="form-control" type="text" value="${requestScope.traineeDetail.topic_eval}" placeholder="${requestScope.traineeDetail.topic_eval}" readonly>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label for="exampleFormControlInput1">Dropout Date</label>
                                            <input max="2022-10-31" id="dropout" name="dropout" type="date" class="form-control" value=${requestScope.traineeDetail.dropout_date} >
                                            <span id="form-message1"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label for="exampleFormControlTextarea1">Note</label>
                                            <textarea rangelength="[0, 300]" id="note" name="note" class="form-control" rows="3">${requestScope.traineeDetail.note}</textarea>
                                            <span class="form-message"></span>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-check" style="margin-left: -10px; margin-top: 20px">
                                            <label style="margin-right: 15px;">Status: </label>
                                            <div class="form-check form-check-inline">
                                                <input ${requestScope.traineeDetail.status eq true?"checked":""} class="form-check-input" 
                                                                                                                 type="radio" name="status" id="inlineRadio1" value="1">
                                                <label class="form-check-label" for="inlineRadio1">Active</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input ${requestScope.traineeDetail.status eq false?"checked":""} class="form-check-input" 
                                                                                                                  type="radio" name="status" id="inlineRadio2" value="0">
                                                <label class="form-check-label" for="inlineRadio2">Dropped Out</label>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <input hidden id ="sbSetting" type ="submit" /> 
                        </form>
                        <div style="margin-left: 70%; margin-bottom: 15px; margin-top: 15px">
                            <button type="button" class="btn btn-outline-dark" onclick="cancel()">Cancel</button>
                            <input type="submit" value="Update" class="btn btn-success" onclick="updateTrainee()">
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

        <!--         Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!--         Page level plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/assets/vendor/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/datatables/dataTables.bootstrap4.js"></script>

        <!--         Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/assets/js/sb-admin.min.js"></script>

        <!--        Demo scripts for this page-->
        <script src="${pageContext.request.contextPath}/assets/js/demo/datatables-demo.js"></script>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-notify@0.5.4/dist/simple-notify.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <!--<script src ="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>-->
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/validation/validation.js" type="text/javascript"></script>
        <script>
                                validationFormClassSettingUpdate();
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

                                function cancel() {
                                    swal({
                                        title: "Are you cancel?",
                                        text: "You will do not save all change!",
                                        icon: "warning",
                                        buttons: true,
                                        dangerMode: true,
                                    })
                                            .then((willDelete) => {
                                                if (willDelete) {
                                                    swal("Cancel!", "", "success")
                                                            .then((value) => {
                                                                window.location.href = "/WebApplication2/supporter/ClassUser?class_id=${requestScope.classId}" + "&roleId=${requestScope.roleID}";
                                                            });
                                                } else {
                                                    swal("You canceled the exit order!");
                                                }
                                            });
                                }
                                //        


                                function updateTrainee() {

                                    swal({
                                        title: "Are you sure?",
                                        text: "Once updated, you will not be able to recover this setting!",
                                        icon: "warning",
                                        buttons: true,
                                        dangerMode: true,
                                    })
                                            .then((willDelete) => {
                                                if (willDelete) {
                                                    swal("", "", "success")
                                                            .then((value) => {
                                                                document.getElementById("sbSetting").click();
//                                                                    document.getElementById("validate-form-Setting-Update").submit();
                                                            });
                                                } else {
                                                    swal("Your setting is still not updated!");
                                                }
                                            });
                                }
                                
        </script>
    </body>
</html>

