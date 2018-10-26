package br.com.cleartech.ptb_performance_admin.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);
	
		boolean isStaticResource = request.getRequestURI().startsWith(request.getContextPath() + "/resources/");
		String loginURI = request.getContextPath() + "/Login";

		boolean loggedIn = session != null && session.getAttribute("username") != null;
		boolean loginRequest = request.getRequestURI().equals(loginURI);
		long currTime = System.currentTimeMillis();
		Cookie cookie = new Cookie("serverTime", "" + currTime);
	    cookie.setPath("/");
	    response.addCookie(cookie);
		if (loggedIn || loginRequest || isStaticResource) {
			if (loggedIn) {
				long expiryTime = currTime + (session.getMaxInactiveInterval() * 1000);
		        cookie = new Cookie("sessionExpiry", "" + expiryTime);
				cookie.setPath("/");
			    response.addCookie(cookie);
			} else {
				cookie = new Cookie("sessionExpiry", "" + currTime);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			chain.doFilter(request, response);
		} else {
			cookie = new Cookie("sessionExpiry", "" + currTime);
			cookie.setPath("/");
			response.addCookie(cookie);
			response.sendRedirect(loginURI);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}