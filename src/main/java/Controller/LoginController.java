package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.LoginService;
import model.UserModel;

@SuppressWarnings("serial")
@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private LoginService loginService = new LoginService();
	
	protected void getCookies(Cookie[] cookies, HttpServletRequest req) {
		if (cookies != null) {
			String email = new String(""), password = new String("");
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email"))
					email = cookie.getValue();
				if (cookie.getName().equals("password"))
					password = cookie.getValue();
				req.setAttribute("email", email);
				req.setAttribute("password", password);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		getCookies(req.getCookies(), req);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		boolean isSuccess = loginService.checkLogin(req.getParameter("email"), req.getParameter("password"));
		if(isSuccess) {
			resp.addCookie(new Cookie("email", req.getParameter("email")));
			resp.addCookie(new Cookie("password", req.getParameter("password")));
			resp.addCookie(new Cookie("role",loginService.getRoleNameFromRoleID(loginService.getUserByEmailAndPassword(req.getParameter("email"),req.getParameter("password")).getRole_id())));
			resp.addCookie(new Cookie("user_id",loginService.getUserByEmailAndPassword(req.getParameter("email"), req.getParameter("password")).getId()+""));
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}
		else { 
			req.setAttribute("msg", "* Username or password is not valid!");
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

}
