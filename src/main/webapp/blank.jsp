<%@ page contentType="text/html; charset=UTF-8" %> <!-- Fix lỗi hiển thị UTF-8 -->
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/plugins/images/favicon.png"></c:url>">
    <title>Blank Page</title>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/bootstrap/dist/css/bootstrap.min.css"> </c:url>" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"> </c:url>" rel="stylesheet">
    <!-- Animation CSS -->
    <link href="<c:url value="/css/animate.css"> </c:url>" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<c:url value="/css/style.css"> </c:url>" rel="stylesheet">
   
    <link href="css/colors/blue-dark.css" id="theme" rel="stylesheet">
    
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg "
                    href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i
                        class="fa fa-bars"></i></a>
                <div class="top-left-part">
                        <a class="logo" href="<c:url value="/index.jsp"></c:url>">
                            <b>
                                <img src="<c:url value="/plugins/images/pixeladmin-logo.png"></c:url>" alt="home" />
                            </b>
                            <span class="hidden-xs">
                                <img src='<c:url value="/plugins/images/pixeladmin-text.png"></c:url>' alt="home" />
                            </span>
                        </a>
                    </div>
                <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                    <li>
                        <form role="search" class="app-search hidden-xs">
                            <input type="text" placeholder="Search..." class="form-control"> <a href=""><i
                                    class="fa fa-search"></i></a>
                        </form>
                    </li>
                </ul>
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li>
                        <a class="profile-pic" href="#"> <img src="<c:url value="/plugins/images/users/varun.jpg"></c:url>" alt="user-img"
                                width="36" class="img-circle"><b class="hidden-xs">Steave</b> </a>
                    </li>
                </ul>
            </div>
            
        </nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                <ul class="nav" id="side-menu">
                    <li style="padding: 10px 0 0;">
                        <a href="<c:url value="/index.jsp"></c:url>" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/user"></c:url>" class="waves-effect"><i class="fa fa-user fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/role"></c:url>" class="waves-effect"><i class="fa fa-modx fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/project"></c:url>" class="waves-effect"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/task"></c:url>" class="waves-effect"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="#"></c:url>" class="waves-effect"><i class="fa fa-columns fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                    </li>
                    <li>
                        <a href="<c:url value="/404.jsp"></c:url>" class="waves-effect"><i class="fa fa-info-circle fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Blank Page </h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12"> <a
                            href='<c:url value="/http://wrappixel.com/templates/pixeladmin/"></c:url>' target="_blank"
                            class="btn btn-danger pull-right m-l-20 btn-rounded btn-outline hidden-xs hidden-sm waves-effect waves-light">Upgrade
                            to Pro</a>
                        <ol class="breadcrumb">
                            <li><a href="<c:url value="/index.jsp"></c:url>">Dashboard</a></li>
                            <li class="active">Blank Page</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Blank page</h3>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2023 &copy; cybersoft.edu.vn </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="<c:url value="/plugins/bower_components/jquery/dist/jquery.min.js"> </c:url>"></script>
    <!-- Bootstrap Core JavaScript -->
     <script src="<c:url value= "/bootstrap/dist/js/bootstrap.min.js"> </c:url>"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="<c:url value= "/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"> </c:url>"></script>
    <!--slimscroll JavaScript -->
    <script src="<c:url value="/js/jquery.slimscroll.js"> </c:url>"></script>
    <!--Wave Effects -->
   <script src="<c:url value="/js/waves.js"> </c:url>"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/js/custom.min.js"> </c:url>"></script>
</body>

</html>