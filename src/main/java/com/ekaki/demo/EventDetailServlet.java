package com.ekaki.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EventDetailServlet
 */
@WebServlet("/EventDetailServlet")
public class EventDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eventId = Integer.parseInt(request.getParameter("eventId"));

        
        if (eventId <= 0) {
            // Handle validation error
            response.sendRedirect("error.jsp?message=Invalid event ID");
            return;
        }

        // Get event details
        Event eventDetails = getEventDetails(eventId);

        if (eventDetails != null) {
            // Set attributes for the event details
            request.setAttribute("eventDetails", eventDetails);

            // Forward to the event details JSP page
            request.getRequestDispatcher("eventDetails.jsp").forward(request, response);
        } else {
            // Handle event details retrieval failure
            response.sendRedirect("error.jsp?message=Failed to retrieve event details");
        }
    }

  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private Event getEventDetails(int eventId) {
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root",
                "Akshay@15");
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM events WHERE event_id = ?")) {

            statement.setInt(1, eventId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                   
                    Event event = new Event();
                    event.setEvent_id(resultSet.getInt("event_id"));
                    event.setEvent_name(resultSet.getString("event_name"));

                    event.setEvent_date(resultSet.getDate("event_date").toLocalDate());

                    event.setEvent_time(resultSet.getTime("event_time").toLocalTime());

                    event.setVenue_id(resultSet.getInt("venue_id"));
                    event.setDescription(resultSet.getString("description"));
                    event.setVenue(resultSet.getString("venue"));
                    event.setImagepath(resultSet.getString("imagepath"));
                    return event;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        return null;
    }


}
