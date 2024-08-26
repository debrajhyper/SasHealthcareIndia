package com.example.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDAO;
import com.example.model.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String confirmPassword = request.getParameter("confirmPassword");

	        if (!password.equals(confirmPassword)) {
	            request.setAttribute("error", "Passwords do not match");
	            request.getRequestDispatcher("signup.jsp").forward(request, response);
	            return;
	        }

	        try {
	            User existingUser = userDAO.getUserByUsername(username);
	            if (existingUser != null) {
	                request.setAttribute("error", "Username already exists");
	                request.getRequestDispatcher("signup.jsp").forward(request, response);
	                return;
	            }

	            User newUser = new User(username, password);
	            userDAO.addUser(newUser);

	            response.sendRedirect("login");
	        } catch (SQLException e) {
	            throw new ServletException("Database error", e);
	        }
	}

}
