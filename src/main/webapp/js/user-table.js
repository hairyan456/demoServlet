// khi nào trang HTML nội dung đã được nạp vào trình duyệt thì sẽ chạy code bên trong Function
$(document).ready(function(){
	//lắng nghe sự kiện Click cho thẻ có id là btn-delete-user
	$(".btn-delete-user").click(function(){
		var id = $(this).attr("user-id");
		var This = $(this);
		$.ajax({
			method:"GET", url:"http://localhost:8080/demoServlet/user/delete?id="+id,
		}).done(function(result){
			This.closest("tr").remove();
			console.log("Ket qua",result);
		})
	});
})