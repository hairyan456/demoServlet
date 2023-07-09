<%@ page contentType="text/html; charset=UTF-8"%><!-- Fix lỗi hiển thị UTF-8 -->
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href='<c:url value="/plugins/images/favicon.png"></c:url>'>
<title>Project Detail</title>
<!-- Bootstrap Core CSS -->
<link
	href='<c:url value="/bootstrap/dist/css/bootstrap.min.css"></c:url>'
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href='<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"></c:url>'
	rel="stylesheet">
<link rel="stylesheet"
	href='<c:url value="/https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"></c:url>'>
<!-- Animation CSS -->
<link href='<c:url value="/css/animate.css"></c:url>' rel="stylesheet">
<!-- Custom CSS -->
<link href='<c:url value="/css/style.css"></c:url>' rel="stylesheet">
<link href='<c:url value="/css/colors/blue-dark.css"></c:url>'
	id="theme" rel="stylesheet">
<link rel="stylesheet" href='<c:url value="/./css/custom.css"></c:url>'>
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
					<a class="logo" href='<c:url value="/index.jsp"></c:url>'> <b>
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
								<li><a href='<c:url value="/user/detail"></c:url>'>Thông
										tin cá nhân</a></li>
								<li><a href='<c:url value="/user/detail"></c:url>'>Thống
										kê công việc</a></li>
								<li class="divider"></li>
								<li><a href='<c:url value="/login"></c:url>'>Đăng xuất</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.navbar-header -->
			<!-- /.navbar-top-links -->
			<!-- /.navbar-static-side -->
		</nav>
		<!-- Left navbar-header -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse slimscrollsidebar">
				<ul class="nav" id="side-menu">
					<li style="padding: 10px 0 0;"><a
						href='<c:url value="/index.jsp"></c:url>' class="waves-effect"><i
							class="fa fa-clock-o fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Dashboard</span></a></li>
					<li><a href='<c:url value="/user"></c:url>'
						class="waves-effect"><i class="fa fa-user fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a></li>
					<li><a href='<c:url value="/role"></c:url>'
						class="waves-effect"><i class="fa fa-modx fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Quyền</span></a></li>
					<li><a href='<c:url value="/project"> </c:url>'
						class="waves-effect"><i class="fa fa-table fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Dự án</span></a></li>
					<li><a href='<c:url value="/task"></c:url>'
						class="waves-effect"><i class="fa fa-table fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Công việc</span></a></li>
					<li><a href='<c:url value="/blank.jsp"></c:url>'
						class="waves-effect"><i class="fa fa-columns fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a></li>
					<li><a href='<c:url value="/404.html"></c:url>'
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
						<h4 class="page-title">Chi tiết công việc</h4>
					</div>
					<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
						<ol class="breadcrumb">
							<li><a href="#">Dashboard</a></li>
							<li class="active"><a
								href='<c:url value="/blank.jsp"></c:url>'>Blank Page</li>
						</ol>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- BEGIN THỐNG KÊ -->
				<div class="row">
					<!--col -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="white-box">
							<div class="col-in row">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<i data-icon="E" class="linea-icon linea-basic"></i>
									<h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<h3 class="counter text-right m-t-15 text-danger">${notYetImplemented} %</h3>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="progress">
										<div class="progress-bar progress-bar-danger"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 20%"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.col -->
					<!--col -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="white-box">
							<div class="col-in row">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
									<h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<h3 class="counter text-right m-t-15 text-megna">${inProgress} %</h3>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="progress">
										<div class="progress-bar progress-bar-megna"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 50%"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.col -->
					<!--col -->
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="white-box">
							<div class="col-in row">
								<div class="col-md-6 col-sm-6 col-xs-6">
									<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
									<h5 class="text-muted vb">HOÀN THÀNH</h5>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-6">
									<h3 class="counter text-right m-t-15 text-primary">${beenImplemented} %</h3>
								</div>
								<div class="col-md-12 col-sm-12 col-xs-12">
									<div class="progress">
										<div class="progress-bar progress-bar-primary"
											role="progressbar" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100" style="width: 30%"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.col -->
				</div>
				<!-- END THỐNG KÊ -->

				<!-- BEGIN DANH SÁCH CÔNG VIỆC -->
				<c:forEach items="${listUsers}" var="user" varStatus="loop">
					<div class="row">
						<div class="col-xs-12">
							<a href="#" class="group-title"> <img width="30"
								src='<c:url value="/plugins/images/users/pawandeep.jpg"></c:url>'
								class="img-circle" /> <span>${user.fullname}</span>
							</a>
						</div>
						<div class="col-md-4">
							<div class="white-box">
								<h3 class="box-title">Chưa thực hiện</h3>
								<div class="message-center">
									<c:forEach items="${notYetImplementedTasks}" var="task"
										varStatus="loop">
										<c:if test="${task.user_id == user.id}">
											<a href="#">
												<div class="mail-contnet">
													<h5>${task.name}</h5>
													<span class="mail-desc"></span> 
													<span class="time">Bắt đầu: ${task.formartStartDate()}</span> 
													<span class="time">Kết thúc: ${task.formartEndDate()}</span>
												</div>
											</a>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="white-box">
								<h3 class="box-title">Đang thực hiện</h3>
								<div class="message-center">
									<c:forEach items="${inProgressTasks}" var="task" varStatus="loop">
										<c:if test="${task.user_id == user.id }">
       	  									<a href="#">
											<div class="mail-contnet">
												<h5>${task.name}</h5>
												<span class="mail-desc"></span> 
												<span class="time">Bắt đầu: ${task.formartStartDate()}</span> 
												<span class="time"> Kết thúc: ${task.formartEndDate()}</span>
											</div>
											</a>
      									</c:if>
									</c:forEach>
								</div>
							</div>
						</div>

						<div class="col-md-4">
							<div class="white-box">
								<h3 class="box-title">Đã hoàn thành</h3>
								<div class="message-center">
									<c:forEach items="${beenImplementedTasks}" var="task" varStatus="loop">
										<c:if test="${task.user_id == user.id }">
       	  									<a href="#">
											<div class="mail-contnet">
												<h5>${task.name}</h5>
												<span class="mail-desc"></span> 
												<span class="time">Bắt đầu: ${task.formartStartDate()}</span> 
												<span class="time"> Kết thúc: ${task.formartEndDate()}</span>
											</div>
											</a>
      									</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

				<!-- END DANH SÁCH CÔNG VIỆC -->
			</div>
			<!-- /.container-fluid -->
			<footer class="footer text-center"> 2023 &copy;
				cybersoft.edu.vn </footer>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script
		src='<c:url value="/plugins/bower_components/jquery/dist/jquery.min.js"></c:url>'></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src='<c:url value="/bootstrap/dist/js/bootstrap.min.js"></c:url>'></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src='<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></c:url>'></script>
	<!--slimscroll JavaScript -->
	<script src='<c:url value="/js/jquery.slimscroll.js"></c:url>'></script>
	<script src='<c:url value="/js/jquery.dataTables.js"></c:url>'></script>
	<!--Wave Effects -->
	<script src='<c:url value="/js/waves.js"></c:url>'></script>
	<!-- Custom Theme JavaScript -->
	<script src='<c:url value="/js/custom.min.js"></c:url>'></script>
</body>

</html>