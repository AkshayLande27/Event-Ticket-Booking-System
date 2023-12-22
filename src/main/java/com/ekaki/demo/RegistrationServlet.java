package com.ekaki.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	        // Retrieve parameters from the request
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");

	        // Validate input data
	        if (username == null || password == null || email == null || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
	            // Handle validation error
	            response.sendRedirect("Register.jsp?error=validation");
	            return;
	        }

	        // Store user information in the database (users table)
	        boolean registrationSuccess = saveUserToDatabase(username, password, email);

	        if (registrationSuccess) {
	            // Redirect to login page or dashboard
	            response.sendRedirect("Login.jsp");
	        } else {
	            // Handle registration failure
	            response.sendRedirect("Register.jsp?error=database");
	        }
	    }
		
		private boolean saveUserToDatabase(String username, String password, String email) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root", "Akshay@15");
	             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)")) {

	            statement.setString(1, username);
	            statement.setString(2, password);
	            statement.setString(3, email);

	            int rowsInserted = statement.executeUpdate();

	            return rowsInserted > 0;
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            return false;
	        }
	    }

	}


