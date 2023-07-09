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

import Service.UserService;
import model.UserModel;

@SuppressWarnings("serial")
@WebServlet(name = "userController", urlPatterns = { "/user", "/user/add","/user/delete","/user/update","/user/view","/user/detail","/user/profile-edit" })
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	
	private void getAllUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listUsers", userService.getListUsers()); // nếu truyền null cho listUsers thì giao diện vẫn ko bị lỗi!
		req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
	}

	private void getListRoles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listRoles", userService.getListRoles()); 
	}
	
	private void addNewUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("fullname").isEmpty()) 
				req.setAttribute("full_name", "* This field is empty!");
			else req.setAttribute("full_name", "");
			if(req.getParameter("email").isEmpty()) 
				req.setAttribute("emailExistedMsg", "* This field is empty!");
			else {
				if(userService.checkEmailExisted(req.getParameter("email"))) 
					req.setAttribute("emailExistedMsg", "* This email is duplicate!");
				else req.setAttribute("emailExistedMsg", "");
			}
			if(!req.getParameter("fullname").isEmpty() && (!req.getParameter("email").isEmpty() && !userService.checkEmailExisted(req.getParameter("email")))) { 
				boolean isSucess = userService.insertNewUser(req.getParameter("email"),req.getParameter("password"),req.getParameter("fullname"),
						userService.getRoleIDFromRoleName(req.getParameter("role")));
				if(!isSucess) {
					resp.getWriter().println("Add new user failed!");
					return;
				}
			}
			getListRoles(req, resp);
			req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
		}
			
	}
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException{
		if(req.getMethod().equalsIgnoreCase("get")) {
			boolean isSuccess = userService.deleteUser(id);
			if(isSuccess)
				resp.sendRedirect(req.getContextPath()+"/user"); // có thể ko cần dòng này vẫn được
			else 
				resp.getWriter().println("Delete failed!");
		}
			
	}
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException{
		if(req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("fullname").isEmpty()) 
				req.setAttribute("full_name", "* This field is empty!");
			else req.setAttribute("full_name", "");
			//TH0: nhập trùng email của user khác đã được tạo:
			if(req.getParameter("email").isEmpty()) 
				req.setAttribute("emailExistedMsg", "* This field is empty!");
			else {
				if(userService.checkEmailExisted2(req.getParameter("email"),Integer.parseInt(req.getParameter("id")))) 
					req.setAttribute("emailExistedMsg", "* This email is duplicate!");
				else req.setAttribute("emailExistedMsg", "");
			}
			//TH1: nhập sai password hiện tại & nhập Re-Type Password != New Password
			if(!(userService.checkPasswordExisted(id, req.getParameter("curentpassword"))) && !(req.getParameter("newpassword").equals(req.getParameter("retypenewpassword"))) ) {
				req.setAttribute("currentPasswdMsg", "* Password not correct!");
				req.setAttribute("retypePasswdMsg", "* Re-type password not match with new password!");
			}
			else {
				req.setAttribute("currentPasswdMsg", ""); 
				req.setAttribute("retypePasswdMsg", "");
			}
			//TH2: nhập đúng password hiện tại nhưng Re-Type Password != New Password:
			if(userService.checkPasswordExisted(id, req.getParameter("curentpassword")) && !(req.getParameter("newpassword").equals(req.getParameter("retypenewpassword"))) ){
				req.setAttribute("currentPasswdMsg", "");
				req.setAttribute("retypePasswdMsg", "* Re-type password not match with new password!");
			}
			else {
				req.setAttribute("retypePasswdMsg", "");
			}
			//TH3: nhập sai password hiện tại nhưng Re-Type Password = New Password:
			if(!userService.checkPasswordExisted(id, req.getParameter("curentpassword")) && (req.getParameter("newpassword").equals(req.getParameter("retypenewpassword"))) ){
				req.setAttribute("currentPasswdMsg", "* Password not correct!");
				req.setAttribute("retypePasswdMsg", "");
			}
			else {
				req.setAttribute("currentPasswdMsg", ""); 
			}
			//nếu 3 điều kiện trên thỏa:
			if(!req.getParameter("fullname").isEmpty() && (!req.getParameter("email").isEmpty() && !userService.checkEmailExisted2(req.getParameter("email"),Integer.parseInt(req.getParameter("id")))) 
					&& (userService.checkPasswordExisted(id, req.getParameter("curentpassword"))) 
					&&  req.getParameter("newpassword").equals(req.getParameter("retypenewpassword"))) {
				boolean isSuccess = userService.updateUser(req.getParameter("fullname"),req.getParameter("email"),req.getParameter("newpassword"),
						userService.getRoleIDFromRoleName(req.getParameter("role")),req.getParameter("avatar"),id);
				if(!isSuccess) {
					resp.getWriter().println("Update failed!");
					return;
				}
			}
			getListRoles(req, resp);
			req.setAttribute("id", req.getParameter("id"));
			req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
		}
	}
	
	private void viewUserByID(HttpServletRequest req, HttpServletResponse resp,int user_id) throws ServletException, IOException {
		UserModel user = userService.getUserByID(user_id);
		//hiển thị tên và Email:
		req.setAttribute("fullname", user.getFullname());
		req.setAttribute("email", user.getEmail());
		//Hiển thị phần trăm task chưa,đang,đã thực hiện:
		int sum = userService.countNotYetImplementedTaskByUserID(user_id) + userService.countInProgressTaskByUserID(user_id) 
					+ userService.countBeenImplementedTaskByUserID(user_id);
		req.setAttribute("notYetImplemented", Math.round((userService.countNotYetImplementedTaskByUserID(user_id)/(double)(sum))*100) );
		req.setAttribute("inProgress", Math.round((userService.countInProgressTaskByUserID(user_id)/(double)(sum))*100));
		req.setAttribute("beenImplemented", Math.round((userService.countBeenImplementedTaskByUserID(user_id)/(double)(sum))*100));
		//Hiển thị danh sách công việc
		req.setAttribute("listProjects", userService.getListProjectsByUserID(user_id));
		req.setAttribute("notYetImplementedTasks",userService.getAllTasksByUserIDAndStatusID(user_id, 1));
		req.setAttribute("inProgressTasks",userService.getAllTasksByUserIDAndStatusID(user_id, 2));
		req.setAttribute("beenImplementedTasks",userService.getAllTasksByUserIDAndStatusID(user_id, 3));
	}
	
	private void loadUserProfile(HttpServletRequest req, HttpServletResponse resp,int user_id) throws ServletException,IOException {
		viewUserByID(req, resp, user_id);
		req.setAttribute("listTasks", userService.getAllTasksByUserID(user_id));
	}
	
	private void profileEdit(HttpServletRequest req, HttpServletResponse resp,int task_id) throws ServletException,IOException, NumberFormatException, ParseException  {
		if(req.getMethod().equalsIgnoreCase("post")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				boolean isSuccess = userService.updateTask(userService.getProjectIDFromProjectName(req.getParameter("project")),
						req.getParameter("task_name"), sdf.parse(req.getParameter("start_date")),
						sdf.parse(req.getParameter("end_date")), userService.getStatusIDFromStatusName(req.getParameter("status")),
						Integer.parseInt(req.getParameter("task_id")));
				if(!isSuccess) {
					resp.getWriter().println("Update failed!");
					return;
				}
			req.setAttribute("listStatus", userService.getListStatus());
			req.setAttribute("task_id", req.getParameter("task_id"));
			req.setAttribute("task",userService.getTaskByID(task_id));
			req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String path = req.getServletPath();
		switch (path) {
		case "/user": {
			getAllUsers(req, resp);
			break;
		}
		case "/user/add": {
			getListRoles(req, resp);
			req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
			break;
		}
		case "/user/delete":{
			deleteUser(req,resp,Integer.parseInt(req.getParameter("id")));
			break;
		}
		case "/user/update":{
			getListRoles(req, resp);
			req.setAttribute("id", req.getParameter("id"));
			req.getRequestDispatcher("/user-update.jsp").forward(req, resp);
			break;
		}
		case "/user/view":{
			viewUserByID(req,resp,Integer.parseInt(req.getParameter("id")));
			req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
			break;
		}
		case "/user/detail":{
			int user_id = 0;
			for(Cookie cookie:req.getCookies()) {
				if(cookie.getName().equals("user_id")) {
					user_id = Integer.parseInt(cookie.getValue());
					break;
				}
			}
			loadUserProfile(req, resp,user_id );
			req.getRequestDispatcher("/profile.jsp").forward(req, resp);
			break;
		}
		case "/user/profile-edit":{
			req.setAttribute("listStatus", userService.getListStatus());
			req.setAttribute("task_id", req.getParameter("task_id"));
			req.setAttribute("task",userService.getTaskByID(Integer.parseInt(req.getParameter("task_id"))));
			req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
			break;
		}
		default:{
			resp.sendRedirect("/404.jsp");
			break;
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8"); //nếu nhập có dấu thì khi load giao diện sẽ bị lỗi font, thêm dòng này để fix!
		String path = req.getServletPath();
		switch (path) {
		case "/user","/user/view","/user/detail": {
			break;
		}
		case "/user/add": {
			addNewUser(req, resp);
			break;
		}
		case "/user/update": {
			updateUser(req, resp, Integer.parseInt(req.getParameter("id")));
			break;
		}
		case "/user/profile-edit":{
			try {
				profileEdit(req, resp, Integer.parseInt(req.getParameter("task_id")));
			} catch (NumberFormatException | ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		}
		default:{
			resp.sendRedirect("/404.jsp");
			break;
		}
		}
	}
}
