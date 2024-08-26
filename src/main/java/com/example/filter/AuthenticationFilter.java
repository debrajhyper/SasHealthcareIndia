package com.example.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = request.getSession(false);

	        String loginURI = request.getContextPath() + "/login";
	        String signupURI = request.getContextPath() + "/signup";

	        boolean loggedIn = session != null && session.getAttribute("user") != null;
	        boolean loginRequest = request.getRequestURI().equals(loginURI);
	        boolean signupRequest = request.getRequestURI().equals(signupURI);

	        if (loggedIn && (loginRequest || signupRequest)) {
	            response.sendRedirect("dashboard");
	        } else if (loggedIn || loginRequest || signupRequest) {
	            chain.doFilter(request, response);
	        } else {
	            response.sendRedirect(loginURI);
	        }
	    }

	    public void init(FilterConfig config) throws ServletException {}

	    public void destroy() {}
}
