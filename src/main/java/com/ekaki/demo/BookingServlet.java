package com.ekaki.demo;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
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
		int eventId = Integer.parseInt(request.getParameter("eventId"));
	    int numberOfTickets = Integer.parseInt(request.getParameter("numberOfTickets"));

	    if (eventId <= 0 || numberOfTickets <= 0) {
	        response.sendRedirect("error.jsp?message=Invalid input data");
	        return;
	    }


	    if (!checkSeatAvailability(eventId, numberOfTickets)) {
	        response.sendRedirect("error.jsp?message=Not enough seats available");
	        return;
	    }

	   
	    double totalPrice = calculateTotalPrice(eventId, numberOfTickets);

	    
	    boolean bookingSuccess = bookEvent(eventId, numberOfTickets, request);

	    if (bookingSuccess) {
	    	
	    	
	       
	        response.sendRedirect("bookingConfirmation.jsp?totalPrice=" + totalPrice);
	    } else {
	        // Handle booking failure
	        response.sendRedirect("error.jsp?message=Failed to book event");
	    }
    }

	private boolean checkSeatAvailability(int eventId, int numberOfTickets) {
	    
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
	            "Akshay@15");
	            PreparedStatement statement = connection.prepareStatement(
	                    "SELECT available_seats FROM events WHERE event_id = ?")) {

	        statement.setInt(1, eventId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                int availableSeats = resultSet.getInt("available_seats");
	                return availableSeats >= numberOfTickets;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return false; 
	}

	private double calculateTotalPrice(int eventId, int numberOfTickets) {
	    
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
	            "Akshay@15");
	            PreparedStatement statement = connection.prepareStatement(
	                    "SELECT ticket_price FROM events WHERE event_id = ?")) {

	        statement.setInt(1, eventId);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                double ticketPrice = resultSet.getDouble("ticket_price");
	                return ticketPrice * numberOfTickets;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return 0.0; 
}
	
	private boolean bookEvent(int eventId, int numberOfTickets, HttpServletRequest request) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
	            "Akshay@15");
	         PreparedStatement insertStatement = connection.prepareStatement(
	                 "INSERT INTO bookings (user_id, event_id, num_tickets, total_price, is_paid) VALUES (?, ?, ?, ?, ?)",
	                 Statement.RETURN_GENERATED_KEYS);
	         PreparedStatement updateEventStatement = connection.prepareStatement(
	                 "UPDATE events SET available_seats = available_seats - ? WHERE event_id = ?")) {

	        HttpSession session = request.getSession(false);

	        if (session != null) {
	            Integer userId = (Integer) session.getAttribute("userId");

	            if (userId != null) {
	                connection.setAutoCommit(false); 

	                try {
	                   
	                    insertStatement.setInt(1, userId);
	                    insertStatement.setInt(2, eventId);
	                    insertStatement.setInt(3, numberOfTickets);

	                   
	                    double totalPrice = calculateTotalPrice(eventId, numberOfTickets);
	                    insertStatement.setDouble(4, totalPrice);

	                    
	                    insertStatement.setInt(5, 0);

	                    int rowsInserted = insertStatement.executeUpdate();

	                    if (rowsInserted > 0) {
	                        // Retrieve the generated booking ID
	                        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
	                        if (generatedKeys.next()) {
	                            // Update available seats
	                            updateEventStatement.setInt(1, numberOfTickets);
	                            updateEventStatement.setInt(2, eventId);
	                            updateEventStatement.executeUpdate();

	                            // Commit the transaction if successful
	                            connection.commit();
	                            return true;
	                        } else {
	                            // Rollback if the booking ID retrieval failed
	                            connection.rollback();
	                        }
	                    } else {
	                        // Rollback if the insert failed
	                        connection.rollback();
	                    }
	                } catch (SQLException e) {
	                    // Rollback in case of any exception
	                    connection.rollback();
	                 
	                } finally {
	                    connection.setAutoCommit(true); // Reset auto-commit mode
	                }
	            }
	        }

	    } catch (SQLException e) {
	       
	    }

	    return false;
	}

	
}