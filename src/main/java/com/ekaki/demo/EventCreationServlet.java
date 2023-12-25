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

/**
 * Servlet implementation class EventCreationServlet
 */
@WebServlet("/EventCreationServlet")
public class EventCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventCreationServlet() {
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
		 String eventName = request.getParameter("eventName");
	        String eventDate = request.getParameter("eventDate");
	        String eventTime = request.getParameter("eventTime");
	        String venueId = request.getParameter("venueId");
	        String description = request.getParameter("description");
	        String venue = request.getParameter("venue");
	        String imagepath = request.getParameter("imagepath");
	        

	        // Validate input data
	        if (eventName == null || eventDate == null || eventTime == null || venueId == null
	                || eventName.isEmpty() || eventDate.isEmpty() || eventTime.isEmpty() || venueId.isEmpty()|| venue.isEmpty()) {
	            // Handle validation error
	            response.sendRedirect("createEvent.jsp?error=validation");
	            return;
	        }

	        // Store event information in the database (events table)
	        boolean creationSuccess = createEvent(eventName, eventDate, eventTime, venueId, description,venue,imagepath);

	        if (creationSuccess) {
	            // Redirect to the event details page
	            response.sendRedirect("eventDetails.jsp?eventId=" + getCreatedEventId());
	        } else {
	            // Handle event creation failure
	            response.sendRedirect("createEvent.jsp?error=database");
	        }
	}
	
	private boolean createEvent(String eventName, String eventDate, String eventTime, String venueId, String description,String venue,String imagepath) {
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
                "Akshay@15");
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO events (event_name, event_date, event_time, venue_id, description,imagepath,venue) VALUES (?, ?, ?, ?, ?,?,?)")) {

            statement.setString(1, eventName);
            statement.setString(2, eventDate);
            statement.setString(3, eventTime);
            statement.setString(4, venueId);
            statement.setString(5, description);
            statement.setString(6, venue);
            statement.setString(7, imagepath);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    private int getCreatedEventId() {
    	int createdEventId = -1; // Default value if retrieval fails

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root", "Akshay@15");
             PreparedStatement statement = connection.prepareStatement("SELECT LAST_INSERT_ID()")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    createdEventId = resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return createdEventId;
    }

}
