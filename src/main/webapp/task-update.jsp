<%@ page contentType="text/html; charset=UTF-8"%><!-- Fix lỗi hiển thị UTF-8 -->
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href='<c:url value="/plugins/images/favicon.png"></c:url>'>
    <title>Task Update Page</title>
    <!-- Bootstrap Core CSS -->
    <link href='<c:url value="/bootstrap/dist/css/bootstrap.min.css"></c:url>' rel="stylesheet">
    <!-- Menu CSS -->
    <link href='<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"></c:url>' rel="stylesheet">
    <!-- animation CSS -->
    <link href='<c:url value="/css/animate.css"></c:url>' rel="stylesheet">
    <!-- Custom CSS -->
    <link href='<c:url value="/css/style.css"></c:url>' rel="stylesheet">
    <!-- color CSS -->
    <link href='<c:url value="/css/colors/blue-dark.css"></c:url>' id="theme" rel="stylesheet">
    
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
			<div class="navbar-header">
				<a class="navbar-toggle hidden-sm hidden-md hidden-lg "
					href="javascript:void(0)" data-toggle="collapse"
					data-target=".navbar-collapse"> <i class="fa fa-bars"></i>
				</a>
				<div class="top-left-part">
					<a class="logo" href='<c:url value="/index.html"></c:url>'> <b>
							<img
							src='<c:url value="/plugins/images/pixeladmin-logo.png"></c:url>'
							alt="home" />
					</b> <span class="hidden-xs"> <img
							src='<c:url value="/plugins/images/pixeladmin-text.png"></c:url>'
							alt="home" />
					</span>
					</a>
				</div>
				<ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
					<li>
						<form role="search" class="app-search hidden-xs">
							<input type="text" placeholder="Search..." class="form-control">
							<a href=""> <i class="fa fa-search"></i>
							</a>
						</form>
					</li>
				</ul>
				<ul class="nav navbar-top-links navbar-right pull-right">
					<li>
						<div class="dropdown">
							<a class="profile-pic dropdown-toggle" data-toggle="dropdown"
								href="#"> <img
								src='<c:url value="/plugins/images/users/varun.jpg"></c:url>'
								alt="user-img" width="36" class="img-circle" /> <b
								class="hidden-xs">Cybersoft</b>
							</a>
							<ul class="dropdown-menu">
								<li><a href='<c:url value="/user/detail"></c:url>'>Thông tin cá nhân</a></li>
								<li><a href='<c:url value="/user/detail"></c:url>'>Thống kê công việc</a></li>
								<li class="divider"></li>
								<li><a href='<c:url value="/login"></c:url>'>Đăng xuất</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			
		</nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse slimscrollsidebar">
				<ul class="nav" id="side-menu">
					<li style="padding: 10px 0 0;"><a href='<c:url value="/index.html"></c:url>'
						class="waves-effect"><i class="fa fa-clock-o fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a></li>
					<li><a href='<c:url value="/user"></c:url>' class="waves-effect"><i
							class="fa fa-user fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Thành viên</span></a></li>
					<li><a href='<c:url value="/role"></c:url>'
						class="waves-effect"><i class="fa fa-modx fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Quyền</span></a></li>
					<li><a href='<c:url value="/project"></c:url>'
						class="waves-effect"><i class="fa fa-table fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Dự án</span></a></li>
					<li><a href='<c:url value="/task"></c:url>'
						class="waves-effect"><i class="fa fa-table fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Công việc</span></a></li>
					<li><a href='<c:url value="/blank.jsp"></c:url>'
						class="waves-effect"><i class="fa fa-columns fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a></li>
					<li><a href='<c:url value="/404.jsp"></c:url>'
						class="waves-effect"><i class="fa fa-info-circle fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Error 404</span></a></li>
				</ul>
			</div>
		</div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Chỉnh sửa công việc</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-2 col-12"></div>
                    <div class="col-md-8 col-xs-12">
                        <div class="white-box">
                            <form class="form-horizontal form-material" action="<c:url value="/task/update?task_id=${task_id}"></c:url>" method="post">
                                <div class="form-group">
                                    <label class="col-md-12">Tên dự án</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name="projects">
                                            <c:forEach items="${listProjects}" var="project" varStatus="loop">
	                                        	<option>${project.name}</option>
	                                         </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Tên công việc <span style="color: red">${taskname}</span></label>
                                    <div class="col-md-12">
                                        <input type="text"  placeholder="Type some task name..." name="task_name" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Người thực hiện</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name="user">
                                            <c:forEach items="${listUsers}" var="user" varStatus="loop">
	                                        	<option>${user.fullname}</option>
	                                         </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày bắt đầu <span style="color: red">${startdate}</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="yyyy-MM-dd" name="start_date" class="form-control form-control-line"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Ngày kết thúc<span style="color: red">${enddate}</span></label>
                                    <div class="col-md-12">
                                        <input type="text" placeholder="yyyy-MM-dd"  name="end_date" class="form-control form-control-line"> 
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Trạng thái</label>
                                    <div class="col-md-12">
                                        <select class="form-control form-control-line" name="status">
                                            <c:forEach items="${listStatus}" var="status" varStatus="loop">
	                                        	<option>${status.name}</option>
	                                         </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button type="submit" class="btn btn-success">Lưu lại</button>
                                        <a href='<c:url value="/task"></c:url>' class="btn btn-primary">Quay lại</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2 col-12"></div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2023 &copy; cybersoft.edu.vn </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src='<c:url value="/plugins/bower_components/jquery/dist/jquery.min.js"></c:url>'></script>
    <!-- Bootstrap Core JavaScript -->
    <script src='<c:url value="/bootstrap/dist/js/bootstrap.min.js"></c:url>'></script>
    <!-- Menu Plugin JavaScript -->
    <script src='<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></c:url>'></script>
    <!--slimscroll JavaScript -->
    <script src='<c:url value="/js/jquery.slimscroll.js"></c:url>'></script>
    <!--Wave Effects -->
    <script src='<c:url value="/js/waves.js"></c:url>'></script>
    <!-- Custom Theme JavaScript -->
    <script src='<c:url value="/js/custom.min.js"></c:url>'></script>
</body>

</html>