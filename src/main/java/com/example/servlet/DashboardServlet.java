package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDAO;
import com.example.model.User;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String searchQuery = request.getParameter("search");
	        try {
	            List<User> users;
	            if (searchQuery != null && !searchQuery.isEmpty()) {
	                users = userDAO.searchUsers(searchQuery);
	            } else {
	                users = userDAO.getAllUsers();
	            }
	            request.setAttribute("users", users);
	            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	        } catch (SQLException e) {
	            throw new ServletException("Database error", e);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Handle AJAX search requests
        String searchQuery = request.getParameter("search");
        try {
            List<User> users = userDAO.searchUsers(searchQuery);
            // Convert users to JSON and send as response
            // For simplicity, we'll just send usernames as a comma-separated string
            StringBuilder sb = new StringBuilder();
            for (User user : users) {
                if (sb.length() > 0) sb.append(",");
                sb.append(user.getUsername());
            }
            response.setContentType("text/plain");
            response.getWriter().write(sb.toString());
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
	}

}
