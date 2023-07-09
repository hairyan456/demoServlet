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
    <link rel="icon" type="image/png" sizes="16x16" href='<c:url value="/assets/plugins/images/favicon.png"></c:url>'>
    <title>403 - Forbidden</title>
    <!-- Bootstrap Core CSS -->
    <link href='<c:url value='/bootstrap/dist/css/bootstrap.min.css'></c:url>' rel="stylesheet">
    <!-- animation CSS -->
    <link href='<c:url value='/css/animate.css'></c:url>' rel="stylesheet">
    <!-- Custom CSS -->
    <link href='<c:url value='/css/style.css'></c:url>' rel="stylesheet">
    <!-- color CSS -->
    <link href='<c:url value='/css/colors/blue.css'></c:url>' id="theme" rel="stylesheet">
    
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <section id="wrapper" class="error-page">
        <div class="error-box">
            <div class="error-body text-center">
                <h1>403</h1>
                <h3 class="text-uppercase">Bạn không có quyền truy cập !</h3>
                <p class="text-muted m-t-30 m-b-30">YOU SEEM TO BE TRYING TO FIND HIS WAY HOME</p>
                <a href='<c:url value="/index.jsp"></c:url>' class="btn btn-info btn-rounded waves-effect waves-light m-b-40">Về trang chủ</a> </div>
            <footer class="footer text-center">2023 @ cybersoft.edu.vn </footer>
        </div>
    </section>
    <!-- jQuery -->
    <script src='<c:url value='/plugins/bower_components/jquery/dist/jquery.min.js'></c:url>'></script>
    <!-- Bootstrap Core JavaScript -->
    <script src='<c:url value='/bootstrap/dist/js/bootstrap.min.js'></c:url>'></script>
    <script type="text/javascript">
    $(function() {
        $(".preloader").fadeOut();
    });
    </script>
</body>

</html>