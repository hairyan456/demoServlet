$(document).ready(function(){
	$(".btn-delete-task").click(function(){
		var id = $(this).attr("task-id");
		var This = $(this);
		$.ajax({
			method:"GET", url:"http://localhost:8080/demoServlet/task/delete?id="+id,
		}).done(function(result){
			This.closest("tr").remove();
			console.log("Ket qua",result);
		})
	});
})/**
 * 
 */