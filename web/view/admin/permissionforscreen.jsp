<%-- 
    Document   : permissionforscreen
    Created on : Oct 6, 2022, 1:04:50 PM
    Author     : Legon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "Model.Settingtitle"%>
<%@page import= "Model.PageControl"%>
<%@page import= "Model.SettingValue"%>
<%@page import= "dal.DAO"%>
<%@page import= "Model.permission"%>

<%@page import="jakarta.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Tables</title>
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
            .page-num{
                color: blue;
            }

            .advanced-search .advanced-search-item{
                margin-left: 0;
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

            <a class="navbar-brand mr-1" href="Home.jsp">S-Course</a>
          
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
            <% int size=0;
                    DAO dao = new DAO();
          HttpServletRequest req = (HttpServletRequest) request;
            PageControl PC = (PageControl)request.getAttribute("PC");
            String filter = "sss";
            if(req.getAttribute("filter")!=null){
            filter = (String)req.getAttribute("filter");
                }
            
           if(req.getAttribute("list")==null){
            %>
            <h4>There is nothing here please go back</h4>

            <%
}else{
//                           if(req.getAttribute("sizeofarray")!=null){
//                                String sizeofarray = (String)req.getAttribute("sizeofarray");
//                                 //   String[] aaaa = (String[])req.getAttribute("sizeofarray");
//                                  //  for(int z = 0;z<aaaa.length;z++){
//                                %>
            <%//}
//                            }
 
                
            %>
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
                        <li class="breadcrumb-item active">Setting List</li>
                    </ol>

                    <!--Form search-->
                           <div class="card mb-3">
                        <div class="card-header">
                            <i class="fas fa-table"></i>
                            Permission for Screen</div>
                    <form action = "/WebApplication2/Permission" >



                        <div class="advanced-search">




                            <div style="margin-left:1%;" class = "advanced-search second" id="search-3">
                                <label for="title" class="mr-sm-2">Search by Title</label>
                                
                                <%if( req.getAttribute("Search")!=null){
                                %>
                                <input value="<%=(String)req.getAttribute("Search")%>" type="text" class="form-control" name ="search"  id="search" placeholder="Enter Setting Title You Want To Search" >

                                <%
                                    }else{
                                                
                                                
                                %>
                                <input  type="text" class="form-control" name ="search"  id="search" placeholder="Enter Setting Title You Want To Search" >
                                <%}%>

                            </div>
                               
                            <select style="margin-left:0px ; margin-right: 10px;margin-top: 45px; margin-bottom:15px; padding: 2px 6px;"  name="filterstatus">
                               <option value="Selectone">
                                   Select one
                                </option>
                                
                                <option value="Active" <%=(filter.equals("Active")) ? "selected" :""%>>
                                    Private Screen
                                </option>
                                <option value="Inactive" <%=(filter.equals("Inactive")) ? "selected" :""%>>
                                    Public Screen
                                </option>

                            </select>
                            <button style="margin-left:0px ; margin-top: 45px; margin-bottom:15px; padding: 2px 6px;" class="btn btn-danger" type="submit">Search</button>

                        </div>

                    </form>
                    <%          ArrayList<Settingtitle> lstst = new ArrayList<Settingtitle>();
                                 ArrayList<SettingValue> lstshow = (ArrayList<SettingValue>) req.getAttribute("lstshow");
                
                              //   lstshow2= (ArrayList<Settingtitle>) req.getAttribute("lstshow2");
             
                                  lstst =(ArrayList<Settingtitle>) req.getAttribute("list");
                                             
                                      size= lstst.size();
                    
                                             
                                                       //       int j=1;
                                     if(size==0){
                    %>
                    <h4>There is no result please find again</h4>

                    <%
                                }else{
                                          
                    %>
                    <!-- DataTables Example -->
                    <%if(req.getAttribute("annount")!=null){
                    %>
                     <h3 style="margin:auto; color: red;">Update successful!</h3>
                 
                   <%}%>
                        <div class="card-body">
                            <div class="table-responsive">

                                <table class="table table-bordered" width="100%" cellspacing="0">
                                    <thead>
                                        <tr class = "bg-warning">
                                            <th scope="col">
                                                <%if(req.getAttribute("sortID")!=null&& req.getAttribute("Search")!=null){
                                                %>

                                                <a href="/WebApplication2/Permission?sort=<%=(int) req.getAttribute("sortID")%>&search=<%=(String)req.getAttribute("Search")%>"><span style="cursor: pointer" >Title <i class="fa fa-sort" aria-hidden="true"></i></span> </a>
                                                        <%
                                                            }


                                                            else if( req.getAttribute("Search")!=null){
                                                    
                                                
                                                        %>
                                                <a href="/WebApplication2/Permission?sort=1&search=<%= (String)req.getAttribute("Search")%>"><span style="cursor: pointer" >Title <i class="fa fa-sort" aria-hidden="true"></i></span> </a>

                                                <%}
                                                    else if( req.getAttribute("sortID")!=null){
                                                %>

                                                <a href="/WebApplication2/Permission?sort=<%=(int) req.getAttribute("sortID")%>"><span style="cursor: pointer" >Title <i class="fa fa-sort" aria-hidden="true"></i></span> </a>
                                                        <%
                                                            }

                                                            else{
                                                
                                                
                                                        %>
                                                <a href="/WebApplication2/Permission?sort=1"><span style="cursor: pointer" >Title <i class="fa fa-sort" aria-hidden="true"></i></span> </a>                     
                                                        <%}%>
                                            </th>
                                            <c:forEach items="${lstshow}" var="role">
                                                <th scope="col">
                                                    <span style="cursor: pointer" >${role.getValue()} </span>
                                                </th>

                                            </c:forEach>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <form action='Permission' method='post'>


                                        <% int counttitle=1;
                                             for(int i=PC.getBegin();i<=PC.getEnd();i++){
                                             Settingtitle st =lstst.get(i);
                                             int autoincrease = 1;
                                        %>
                                        <tr>
                                        <input style="border:0px" type="text" hidden name="title<%=counttitle%>" value="<%=st.getSid()%>" readonly>


                                        <td > <input style="border:0px" type="text"  value="<%=st.getTitle()%>" readonly></td>
                                            <%
                                          for(int j=0;j<lstshow.size();j++){
                                              SettingValue sv = lstshow.get(j);
                                    
                                            %>



                                        <td>  <%if(st.getStatus()==1){
                                            %>
                                             <input type="checkbox" id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Access</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox"   id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Add</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox"   id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Delete</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox"   id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Edit</label></td>  
                                            <%autoincrease++;%>
                                            
                                            
                                            <%
                                            }else if(sv.getTitle()==17){
                                             permission p = dao.getPermis(st.getSid(),sv.getTitle());
                                             String s= p.toString();
                                             String[] k1 = s.split("/");
                                            %>

                                            <input type="checkbox"checked id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Access</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox" checked  id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Add</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox" checked  id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Delete</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox" checked  id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1" onclick="return false;">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Edit</label></td>  
                                            <%autoincrease++;%>
                                        
                                             <%}else{
                                             permission p = dao.getPermis(st.getSid(),sv.getTitle());
                                             String s= p.toString();
                                             String[] k1 = s.split("/");
                                            %>

                                            <input type="checkbox" <%=(k1[0].equals("false")) ? "" :"checked"%> id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Access</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox" <%=(k1[1].equals("false")) ? "" :"checked"%>  id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Add</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox" <%=(k1[2].equals("false")) ? "" :"checked"%>  id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Delete</label></br>
                                            <%autoincrease++;%>
                                            <input type="checkbox" <%=(k1[3].equals("false")) ? "" :"checked"%>  id="vehicle<%=counttitle%><%=autoincrease%>" name="vehicle<%=counttitle%><%=autoincrease%>" value="1">
                                            <label for="vehicle<%=counttitle%><%=autoincrease%>">Can Edit</label></td>  
                                            <%autoincrease++;%>
                                        
                                            <%}}%>
                                        </tr>
                                        <%counttitle++;
                                         autoincrease=1;
                                   }%>
                                        </tbody>
                                </table>

                                        <input style="margin-left:45% ;" name="button" class="btn btn-danger" type="submit" value="Update Permission"></br>
                                
                                <!-- This is pagination -->
                                <div class = "setting-pagination">
                                    <ul class="pagination" id = "paggerBottom">


                                        <li><c:if test = "${PC.cp!=0}">
                                                <input style="color:blue" type="submit" name="home" value="Home"> 
                                                <input style="color:blue" type="submit" name="pre" value="Pre"> 

                                            </c:if>

                                            <c:forEach begin="${PC.pageStart}" end= "${PC.pageEnd}" var = "i" >
                                                <input style="color:blue" type="submit" name="btn${i}" value="${i+1}"> 
                                            </c:forEach>  
                                            <c:if test = "${PC.cp!=PC.np-1}">
                                                <input style="color:blue" type="submit" name="next" value="Next"> 
                                                <input style="color:blue" type="submit" name="end" value="End"> 

                                            </c:if></li> </ul></br>
                                    <input type="text"  hidden name ="cp" value="${PC.cp}"> </br><!-- comment -->

                                    <%if(req.getAttribute("Search")!=null){%>
                                    <input value="<%=(String)req.getAttribute("Search")%>" type="text" class="form-control" hidden name ="search"  id="search" placeholder="Enter Setting Title You Want To Search" readonly >
                                    <%}%>
                                    <%if(req.getAttribute("sortID")!=null){%>
                                    <input value="<%=(int) req.getAttribute("sortID")%>" type="text" class="form-control" hidden name ="sort"  id="sort" placeholder="Enter Setting Title You Want To Search" readonly>
                                    <%}%>
                                     <%if(req.getAttribute("filter")!=null){%>
                                    <input value="<%=(String) req.getAttribute("filter")%>" type="text" class="form-control" hidden name ="filterstatus"  id="sort" placeholder="Enter Setting Title You Want To Search" readonly>
                                    <%}%>





                                </div>

                            </div>
                        </div>


                        <div style="margin:auto;">
                            Input nrpp:<select name="nrpp">
                                <c:forEach items='${PC.arrNrpp}' var = "i" varStatus="loop">
                                    <option value='${loop.index}/${i}'
                                            <c:if test="${loop.index==PC.nrpp}" >selected
                                            </c:if>
                                            > ${i}
                                    </option>

                                </c:forEach> 


                            </select>
                        </div></div></div>
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
        <%}}%>
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

        <script src="https://code.jquery.com/jquery-3.6.0.min.js" ></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-notify@0.5.4/dist/simple-notify.min.js"></script>
        <script>
            if (${requestScope.listSetting.size() > 0}) {
                pagger_RegistrationList("paggerBottom", 2,${requestScope.totalPage},${requestScope.pageIndex}, '${requestScope.url}');
            }
            function editRegistration(id) {
                window.location.href = "../../RegistrationDetails?ucid=" + id;
            }
            function addRegistration() {
                window.location.href = "../../RegistrationDetails";
            }
            function sort(SortId) {
                var url = "Permission?sort= " + SortId + "";
                window.location.href = url;
            }
        </script>
    </body>
</html>
