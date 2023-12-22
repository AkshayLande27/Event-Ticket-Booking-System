package com.ekaki.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

	        // Validate input data
	        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
	            // Handle validation error
	            response.sendRedirect("Login.jsp?error=validation");
	            return;
	        }

	       
	        if (isValidUser(username, password)) {
	            // Set session attributes for authenticated user
	        	
	            HttpSession session = request.getSession(true);
	            session.setAttribute("username", username); 
	            session.setAttribute("userId",getUserId(username,password));

	           
	            response.sendRedirect("dashboard.jsp");
	        } else {
	            // Handle login failure
	            response.sendRedirect("Login.jsp?error=invalid");
	        }
	    
	}
	
	private boolean isValidUser(String username, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
                "Akshay@15");
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // If a record is found, the user is valid
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
            return false;
        }
    }
	
	private int getUserId(String username, String password)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
	                "Akshay@15");
	                PreparedStatement statement = connection.prepareStatement("SELECT user_Id FROM users WHERE username = ? AND password = ?")) {

	            statement.setString(1, username);
	            statement.setString(2, password);

	            try (ResultSet resultSet = statement.executeQuery()) 
	            {
	                if (resultSet.next()) {
	                    return resultSet.getInt("user_Id");
	                } else 
	                {
	                    return -1; 
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            return -1;
	        }
	}

}
