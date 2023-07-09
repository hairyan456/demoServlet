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

import Service.ProjectService;

@SuppressWarnings("serial")
@WebServlet(name = "projectController", urlPatterns = { "/project","/project/add","/project/delete","/project/update","/project/view" })
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();
	
	//xuất toàn bộ dự án nếu đăng nhập account Quyền ADMIN
	private void getAllProjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listProjects", projectService.getListProjects());
		req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
	} 
	
	//Chỉ xuất các dự án của user có quyền Leader đó đảm nhận
	private void getListProjectsOfLeader(HttpServletRequest req, HttpServletResponse resp,int leader_id) throws ServletException, IOException {
		req.setAttribute("listProjects", projectService.getListProjectsOfLeader(leader_id));
		req.getRequestDispatcher("/groupwork.jsp").forward(req, resp);
	} 
	
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
	
	private void getListLeaders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listLeaders", projectService.getListLeaders());
	}
	
	private void getLeaderById(HttpServletRequest req, HttpServletResponse resp,int user_id) throws ServletException, IOException {
		req.setAttribute("listLeaders", projectService.getUserByID(user_id));
	}
	
	// do class Project khai báo kiểu Date java.util -> có thể dùng SimpleDateFormat để parse đúng định dạng chuỗi hiển thị thời gian .
	// khi insert vào database thì chỉ nhận kiểu dữ liệu Date java.sql => chuyển từ java.util -> long (hàm getTime()) -> new Date(long)
	private void addNewProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
		if(req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("name").isEmpty()) 
				req.setAttribute("projectname", "* This field is empty!");
			else {
				// nếu tên taskname trùng trong 1 Project nào đó:
				if(projectService.checkProjectNameExisted(req.getParameter("name"))) 
					req.setAttribute("projectname", "* This task's name is duplicate!!");
				else 
					req.setAttribute("projectname", "");
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
			if(( !(req.getParameter("name").isEmpty()) && !(projectService.checkProjectNameExisted(req.getParameter("name"))) ) && !(req.getParameter("start_date").isEmpty()) && !(req.getParameter("end_date").isEmpty())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				boolean isSucess = false;
				if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
					isSucess = projectService.insertNewProject(req.getParameter("name"),sdf.parse(req.getParameter("start_date")),sdf.parse(req.getParameter("end_date")),req.getParameter("leader"));
				else if(getCurrentRole(req, resp).equalsIgnoreCase("role_leader"))
					isSucess = projectService.insertNewProject2(req.getParameter("name"),sdf.parse(req.getParameter("start_date")),sdf.parse(req.getParameter("end_date")),getCurrentUserId(req, resp));
				if(!isSucess) {
					resp.getWriter().println("Add new Project failed!");
					return;
				}
			}
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListLeaders(req, resp);
			else getLeaderById(req, resp, getCurrentUserId(req, resp));
			req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
		}
	}
	
	private void deleteProject(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException{
		if(req.getMethod().equalsIgnoreCase("get")) {
			boolean isSuccess = projectService.deleteProject(id);
			if(isSuccess)
				resp.sendRedirect(req.getContextPath()+"/project"); // có thể ko cần dòng này vẫn được
			else 
				resp.getWriter().println("Delete failed!");
		}
			
	}
	
	private void updateProject(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException, ParseException {
		if(req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("name").isEmpty()) 
				req.setAttribute("projectname", "* This field is empty!");
			else {
				if(projectService.checkProjectNameExisted(req.getParameter("name"))) 
					req.setAttribute("projectname", "* This task's name is duplicate!!");
				else 
					req.setAttribute("projectname", "");
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
			if(( !(req.getParameter("name").isEmpty()) && !(projectService.checkProjectNameExisted(req.getParameter("name"))) ) && !(req.getParameter("start_date").isEmpty()) && !(req.getParameter("end_date").isEmpty())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				boolean isSucess = false;
				if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
					isSucess = projectService.updateProject(req.getParameter("name"),sdf.parse(req.getParameter("start_date")),sdf.parse(req.getParameter("end_date")),id,req.getParameter("leader"));
				else isSucess = projectService.updateProject2(req.getParameter("name"),sdf.parse(req.getParameter("start_date")),sdf.parse(req.getParameter("end_date")),id,getCurrentUserId(req, resp));
				if(!isSucess) {
					resp.getWriter().println("Update Project failed!");
					return;
				}
			}
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListLeaders(req, resp);
			else getLeaderById(req, resp, getCurrentUserId(req, resp));
			req.setAttribute("project_id", req.getParameter("project_id"));
			req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);
		}
	}
	
	private void viewProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Hiển thị phần trăm task chưa,đang,đã thực hiện:
		int sum = projectService.countTaskByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 1) + 
				  projectService.countTaskByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 2) + 
				  projectService.countTaskByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 3);
		req.setAttribute("notYetImplemented", Math.round((projectService.countTaskByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 1)/(double)(sum))*100) );
		req.setAttribute("inProgress", Math.round((projectService.countTaskByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 2)/(double)(sum))*100) );
		req.setAttribute("beenImplemented", Math.round((projectService.countTaskByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 3)/(double)(sum))*100) );
		
		//Hiển thị danh sách các task của từng User thuộc Dự án:
		req.setAttribute("listUsers", projectService.getListUsersDoProject(Integer.parseInt(req.getParameter("id"))));
		req.setAttribute("notYetImplementedTasks",projectService.getAllTasksByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 1));
		req.setAttribute("inProgressTasks",projectService.getAllTasksByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 2));
		req.setAttribute("beenImplementedTasks",projectService.getAllTasksByProjectAndStatus(Integer.parseInt(req.getParameter("id")), 3));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String path = req.getServletPath();
		switch (path) {
		case "/project": {
			String role = getCurrentRole(req, resp);
			int user_id = getCurrentUserId(req, resp);
			if(role.equalsIgnoreCase("role_admin"))
				getAllProjects(req, resp);
			else if(role.equalsIgnoreCase("role_leader")) {
				getListProjectsOfLeader(req, resp, user_id);
			}
			break;
		}
		case "/project/add": {
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListLeaders(req, resp);
			else 
				getLeaderById(req, resp, getCurrentUserId(req, resp));
			req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
			break;
		}
		case "/project/delete":{
			deleteProject(req,resp,Integer.parseInt(req.getParameter("id")));
			break;
		}
		case "/project/update":{
			if(getCurrentRole(req, resp).equalsIgnoreCase("role_admin"))
				getListLeaders(req, resp);
			else 
				getLeaderById(req, resp, getCurrentUserId(req, resp));
			req.setAttribute("project_id", req.getParameter("project_id"));
			req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);
			break;
		}
		case "/project/view":{
			viewProject(req, resp);
			req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
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
		case "/project","/project/view": {
			break;
		}
		case "/project/add": {
			try {
				addNewProject(req, resp);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		}
		case "/project/update":{
			try {
				updateProject(req, resp, Integer.parseInt(req.getParameter("project_id")));
			} catch (ServletException | IOException | ParseException e) {
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
