package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.RoleService;

@SuppressWarnings("serial")
@WebServlet(name = "roleController", urlPatterns = { "/role", "/role/add", "/role/delete","/role/update" })
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();

	private void getAllRoles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listRoles", roleService.getListRoles());
		req.getRequestDispatcher("/role-table.jsp").forward(req, resp);
	}

	private void addNewRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("rolename").isEmpty()) 
				req.setAttribute("role_name", "* This field is empty!");
			else {
				// nếu tên rolename trùng trong 1 Role nào đó:
				if(roleService.checkRoleNameExisted(req.getParameter("rolename"))) 
					req.setAttribute("role_name", "* This task's name is duplicate!!");
				else 
					req.setAttribute("role_name", "");
			}
			if(req.getParameter("description").isEmpty()) 
				req.setAttribute("descrip_tion", "* This field is empty!");
			else 
				req.setAttribute("descrip_tion", "");
			if( (!(req.getParameter("rolename").isEmpty()) && !roleService.checkRoleNameExisted(req.getParameter("rolename"))) && (!req.getParameter("description").isEmpty())) {
				boolean isSucess = roleService.insertNewRole(req.getParameter("rolename"), req.getParameter("description"));
				if (!isSucess) {
					resp.getWriter().println("Add new role failed!");
					return;
				}
			}
			req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
		}
	}
	
	private void deleteRole(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException{
		if(req.getMethod().equalsIgnoreCase("get")) {
			boolean isSuccess = roleService.deleteRole(id);
			if(isSuccess)
				resp.sendRedirect(req.getContextPath()+"/role"); // có thể ko cần dòng này vẫn được
			else 
				resp.getWriter().println("Delete failed!");
		}
			
	}
	
	private void updateRole(HttpServletRequest req, HttpServletResponse resp,int id) throws ServletException, IOException {
		if (req.getMethod().equalsIgnoreCase("post")) {
			if(req.getParameter("rolename").isEmpty()) 
				req.setAttribute("role_name", "* This field is empty!");
			else {
				// nếu tên rolename trùng trong 1 Role nào đó:
				if(roleService.checkRoleNameExisted(req.getParameter("rolename"))) 
					req.setAttribute("role_name", "* This task's name is duplicate!!");
				else 
					req.setAttribute("role_name", "");
			}
			if(req.getParameter("description").isEmpty()) 
				req.setAttribute("descrip_tion", "* This field is empty!");
			else 
				req.setAttribute("descrip_tion", "");
			if( (!(req.getParameter("rolename").isEmpty()) && !roleService.checkRoleNameExisted(req.getParameter("rolename"))) && (!req.getParameter("description").isEmpty())) {
				boolean isSucess = roleService.updateRole(req.getParameter("rolename"), req.getParameter("description"),id);
				if (!isSucess) {
					resp.getWriter().println("Update role failed!");
					return;
				}
			}
			req.setAttribute("role_id", req.getParameter("role_id"));
			req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String path = req.getServletPath();
		switch (path) {
		case "/role": {
			getAllRoles(req, resp);
			break;
		}
		case "/role/add": {
			req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
			break;
		}
		case "/role/delete":{
			deleteRole(req,resp,Integer.parseInt(req.getParameter("id")));
			break;
		}
		case "/role/update":{
			req.setAttribute("role_id", req.getParameter("role_id"));
			req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
			break;
		}
		default: {
			resp.sendRedirect("404.html");
			break;
		}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8"); // nếu nhập có dấu thì khi load giao diện sẽ bị lỗi font, thêm dòng này để fix!
		String path = req.getServletPath();
		switch (path) {
		case "/role": {
			break;
		}
		case "/role/add": {
			addNewRole(req, resp);
			break;
		}
		case "/role/update":{
			updateRole(req,resp,Integer.parseInt(req.getParameter("role_id")));
			break;
		}
		default: {
			resp.sendRedirect("404.html");
			break;
		}
		}
	}
}
