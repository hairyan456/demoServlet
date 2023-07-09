package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.TaskService;

@SuppressWarnings("serial")
@WebServlet(name = "taskController", urlPatterns = { "/task","/task/add","/task/delete","/task/update" })
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	
	private String getCurrentRole(HttpServletRequest req, HttpServletResponse resp) {
		for(Cookie cookie:req.getCookies()) {
			if(cookie.getName().equalsIgnoreCase("role"))
				return cookie.getValue();
		}
		return null;
	}
	private int getCurrentUserId(HttpServletRequest req, HttpServletResponse resp) {
		for(Cookie cookie:req.getCookies()) {
			if(cookie.getName().equalsIgnoreCase("user_id"))
				return Integer.parseInt(cookie.getValue());
		}
		return -1;
	}
	
	//xuất toàn bộ task nếu user có role ADMIN
	private void getAllTasks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listTasks", taskService.getListTasks());
	} 
	
	//xuất các task do user có role LEADER đảm nhiệm
	private void getAllTasksByLeader(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listTasks", taskService.getAllTasksByLeader(getCurrentUserId(req, resp)));
	} 
	
	//xuất toàn bộ Project nếu user có role ADMIN
	private void getListProjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listProjects", taskService.getListProjects());
	} 
	
	//xuất các Project nếu user có role Leader đảm nhiệm
		private void getAllProjectsByLeader(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setAttribute("listProjects", taskService.getAllProjectsByLeader(getCurrentUserId(req, resp)));
		} 
	
	private void getListUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listUsers", taskService.getListUsers());
	} 
	
	private void getListStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listStatus", taskService.getListStatus());
	} 
	
	private void addNewTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
		if(req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("taskname").isEmpty()) 
				req.setAttribute("tasknameExistedMsg", "* This field is empty!");
			else {
				// nếu tên taskname trùng trong 1 Project nào đó:
				if(taskService.checkTaskNameExisted(req.getParameter("taskname"),req.getParameter("projects"))) 
					req.setAttribute("tasknameExistedMsg", "* This task's name is duplicate!!");
				else 
					req.setAttribute("tasknameExistedMsg", "");
			}
			if(req.getParameter("start_date").isEmpty()) 
				req.setAttribute("startdate", "* This field is empty!");
			else 
				req.setAttribute("startdate", "");
			//Nếu không nhập end_date:
			if(req.getParameter("end_date").isEmpty()) 
				req.setAttribute("enddate", "* This field is empty!");
			else 
				req.setAttribute("enddate", "");
			if(( !(req.getParameter("taskname").isEmpty()) && !(taskService.checkTaskNameExisted(req.getParameter("taskname"),req.getParameter("projects"))) ) && !(req.getParameter("start_date").isEmpty()) && !(req.getParameter("end_date").isEmpty())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				boolean isSucess = taskService.insertNewTask(req.getParameter("taskname"),sdf.parse(req.getParameter("start_date")),sdf.parse(req.getParameter("end_date")),
						req.getParameter("users"),req.getParameter("projects"),req.getParameter("status"));
				if(!isSucess) {
					resp.getWriter().println("Add new task failed!");
					return;
				}
			}
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListProjects(req, resp);
			else 
				getAllProjectsByLeader(req, resp);
			getListUsers(req, resp); getListStatus(req, resp);
			req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
		}
			
	}
	
	private void deleteTask(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException{
		if(req.getMethod().equalsIgnoreCase("get")) {
			boolean isSuccess = taskService.deleteTask(id);
			if(isSuccess)
				resp.sendRedirect(req.getContextPath()+"/task"); // có thể ko cần dòng này vẫn được
			else 
				resp.getWriter().println("Delete failed!");
		}
			
	}
	
	private void updateTask(HttpServletRequest req, HttpServletResponse resp,int task_id) throws ServletException, IOException, NumberFormatException, ParseException {
		if(req.getMethod().equalsIgnoreCase("post")) {
			//Nếu ko nhập tên task:
			if(req.getParameter("task_name").isEmpty()) 
				req.setAttribute("taskname", "* This field is empty!");
			else {
				// nếu tên taskname trùng trong 1 Project nào đó:
				if(taskService.checkTaskNameExisted(req.getParameter("task_name"),req.getParameter("projects"))) 
					req.setAttribute("taskname", "* This task's name is duplicate!!");
				else 
					req.setAttribute("taskname", "");
			}
			//Nếu không nhập start_date:
			if(req.getParameter("start_date").isEmpty()) 
				req.setAttribute("startdate", "* This field is empty!");
			else 
				req.setAttribute("startdate", "");
			//Nếu không nhập end_date:
			if(req.getParameter("end_date").isEmpty()) 
				req.setAttribute("enddate", "* This field is empty!");
			else 
				req.setAttribute("enddate", "");
			//Nếu thỏa cả 3 điều kiện trên:
			if(( !(req.getParameter("task_name").isEmpty()) && !(taskService.checkTaskNameExisted(req.getParameter("task_name"),req.getParameter("projects"))) ) && !(req.getParameter("start_date").isEmpty()) && !(req.getParameter("end_date").isEmpty())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				boolean isSuccess = taskService.updateTask2(req.getParameter("projects"), req.getParameter("task_name"), 
						req.getParameter("user"),
						sdf.parse(req.getParameter("start_date")),sdf.parse(req.getParameter("end_date")),
						req.getParameter("status"),
						task_id);
				if(!isSuccess) {
					resp.getWriter().println("Update failed!");
					return;
				}
					
			}
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListProjects(req, resp);
			else getAllProjectsByLeader(req, resp);
			getListStatus(req, resp);
			getListUsers(req, resp);
			req.setAttribute("task_id", req.getParameter("task_id"));
			req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String path = req.getServletPath();
		switch (path) {
		case "/task": {
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getAllTasks(req, resp);
			else if(getCurrentRole(req, resp).equalsIgnoreCase("role_leader"))
				getAllTasksByLeader(req, resp);
			req.getRequestDispatcher("/task.jsp").forward(req, resp);
			break;
		}
		case "/task/add": {
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListProjects(req, resp);
			else if(getCurrentRole(req, resp).equalsIgnoreCase("role_leader"))
				getAllProjectsByLeader(req, resp);
			getListUsers(req, resp); getListStatus(req, resp);
			req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
			break;
		}
		case "/task/delete":{
			deleteTask(req,resp,Integer.parseInt(req.getParameter("id")));
			break;
		}
		case "/task/update":{
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListProjects(req, resp);
			else if(getCurrentRole(req, resp).equalsIgnoreCase("role_leader"))
				getAllProjectsByLeader(req, resp);
			getListStatus(req, resp); getListUsers(req, resp);
			req.setAttribute("task_id", req.getParameter("task_id"));
			req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
			break;
		}
		default:{
			resp.sendRedirect("404.html");
			break;
		}
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8"); 
		String path = req.getServletPath();
		switch (path) {
		case "/task": {
			break;
		}
		case "/task/add": {
			try {
				addNewTask(req, resp);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		}
		case "/task/update":{
			try {
				updateTask(req, resp, Integer.parseInt(req.getParameter("task_id")));
			} catch (NumberFormatException | ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		}
		default:{
			resp.sendRedirect("404.html");
			break;
		}
		}
	}

}
