package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = { "/*" }) //Khi người dùng gọi tới link được quy định trong đây thì Filter sẽ được kích hoạt.
public class CustomFilter implements Filter {
	/*
	 * req.getContextPath() : /demoServlet
	 * req.getServletPath() : /login , /index.html
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (!req.getServletPath().startsWith("/login")) {
			if (req.getCookies() != null) {
				boolean flag = false;
				for (Cookie cookie : req.getCookies()) {
					if (cookie.getName().equals("email") || cookie.getName().equals("password")) {
						flag = true;
						break;
					}
				}
				if (flag) {
					String role = new String("");
					for (Cookie cookie : req.getCookies()) {
						if(cookie.getName().equals("role")) {
							role = cookie.getValue().substring(5,cookie.getValue().length());
							break;
						}
					}
					if(role.equalsIgnoreCase("admin")) 
							chain.doFilter(request, response);
					else if(role.equalsIgnoreCase("leader")) {
						if(req.getServletPath().equals("/user/add") || req.getServletPath().equals("/user/delete") || req.getServletPath().equals("/user/update")
								 || req.getServletPath().startsWith("/role") ) 
							resp.sendRedirect(req.getContextPath()+"/404.jsp");
						else
							chain.doFilter(request, response);
					}
					else {
						if(req.getServletPath().equals("/user/add") || req.getServletPath().equals("/user") || req.getServletPath().equals("/user/delete") || req.getServletPath().equals("/user/update") || req.getServletPath().startsWith("/role") || req.getServletPath().startsWith("/project")
								|| req.getServletPath().startsWith("/task")) 
							resp.sendRedirect(req.getContextPath()+"/404.jsp");
						else
							chain.doFilter(request, response);
					}
				}
				else
					resp.sendRedirect(req.getContextPath()+"/login");
			} else
				resp.sendRedirect(req.getContextPath()+"/login");
		}
		else 
			chain.doFilter(req, resp);
	}
}
