$(document).ready(function(){
	$(".btn-delete-project").click(function(){
		var id = $(this).attr("project-id");
		var This = $(this);
		$.ajax({
			method:"GET", url:"http://localhost:8080/demoServlet/project/delete?id="+id,
		}).done(function(result){
			This.closest("tr").remove();
			console.log("Ket qua",result);
		})
	});
})