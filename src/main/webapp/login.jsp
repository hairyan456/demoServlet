<%@ page contentType="text/html; charset=UTF-8" %> <!-- Fix lỗi hiển thị UTF-8 -->
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<%-- 
	<%! %> :  khai báo biến  
	<% %> :   xử lý logic code
	<%= %>:   xuất gía trị của biến ra màn hình
	** khi code html không thay đổi thì sẽ tái sử dụng code  bên jsp
--%>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body style="background-color:  #ccccb3;">
	<!-- <%!
		int count = 0;
	%>
	
	<%
		count++;
		if(count%2==0) {		
	%>
		<b style="padding: 20px"> <%= "Hello World" %> </b> 
		<% } %>
		<b style="padding: 20px"> <%= count %> </b>  -->
	
<div class="container">
  <div class="row mt-5">
    <div class="col-md-5 m-auto mt-5" style="background-color: rgb(245, 245, 245)">
      <h3 class="text-center" style="margin-top:20px;" >ĐĂNG NHẬP HỆ THỐNG</h3>
      <div class="p-4 border mt-4">
      <!-- action: đường dẫn mà tham số sẽ được gửi về
      	   method:	phương thức gửi tham số cho đường dẫn khai báo ở Action		
       -->
       <% String contextPath = request.getContextPath(); %>
<%-- 
       <b> ${user.username} </b>
       <b> ${user.getPassword()} </b>
       <c:out value="${msg}"></c:out>
       <c:if test="${user.getPassword() =='123'}">
       	  <br>Mật khẩu của bạn là ${user.getPassword()} <br>
       </c:if>
--%>       	

        <form action="<%=contextPath%>/login" method="post" > 
            <div class="form-group">
            	<h5 style="color:red;">${msg}</h5> <br>
              <label>Email</label>
              <input type="email" value="${email}" class="form-control" name="email">
            </div>
            <div class="form-group">
              <label>Mật khẩu</label>
              <input type="password" value="${password}" class="form-control" name="password">
            </div>
            <input type="checkbox" name="rememberPassword"> Nhớ mật khẩu <br> <br>
            <button type="submit" style="margin-left:140px;" class="btn btn-primary">Đăng nhập</button>
          </form>
      </div>
     
      </div>
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
