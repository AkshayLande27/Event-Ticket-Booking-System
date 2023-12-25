<%@ page import="java.sql.*, java.util.ArrayList, java.util.List" %>
<%@ page import="com.ekaki.demo.Event" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Events</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

    <header>
        <h1>Welcome Admin! - Manage Events</h1>
    </header>

    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Home</a></li>
            <li><a href="manageEvents.jsp">Manage Events</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <main>
        <section id="add-event-section">
            <h2>Add New Event</h2>
            <form action="EventCreationServlet" method="post">
                <label for="eventName">Event Name:</label>
                <input type="text" id="eventName" name="eventName" required>
                
                <label for="eventDate">Event Date:</label>
                <input type="date" id="eventDate" name="eventDate" required>
                
                <label for="eventTime">Event Time:</label>
                <input type="time" id="eventTime" name="eventTime" required>
                
                <label for="venueId">Venue ID:</label>
                <input type="text" id="venueId" name="venueId" required>
                
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>

                <label for="venue">Venue:</label>
                <input type="text" id="venue" name="venue" required>

                <label for="imagepath">Image Path:</label>
                <input type="text" id="imagepath" name="imagepath" required>

                <button type="submit">Add Event</button>
            </form>
        </section>

        <section id="manage-events-section">
        <%try {
            // Establish database connection (replace with your actual database credentials)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectevent", "root", "Akshay@15");

            // Execute SQL query to retrieve events
            String query = "SELECT * FROM events";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                List<Event> eventsList = new ArrayList<>();
                while (resultSet.next()) {
                    // Retrieve attributes from the database
                    int eventId = resultSet.getInt("event_id");
                    String title = resultSet.getString("event_name");
                    String description = resultSet.getString("description");
                    String date = resultSet.getString("event_date");
                    String time = resultSet.getString("event_time");
                    String venue = resultSet.getString("venue");
                    int venue_id = resultSet.getInt("venue_id");
                    int availableSeats = resultSet.getInt("available_seats");
                    double ticketPrice = resultSet.getDouble("ticket_price");
                    String imagePath = resultSet.getString("image_path");

                    // Create Event object and add it to the list
                    Event event = new Event( title,  date, time, ticketPrice,venue_id,description,availableSeats,venue, imagePath);
                    eventsList.add(event);
                }
                request.setAttribute("eventsList", eventsList);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // Close the database connection
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } %>
            <% for (Event event : eventsList) { %>
		    <div class="event-card">
		        <img src="<%= event.getImagePath() %>" alt="<%= event.getEvent_name() %> Image">
		        <div class="event-details">
		            <h2 class="event-title"><%= event.getEvent_name() %></h2>
		            <p class="event-description"><%= event.getDescription() %></p>
		            <p class="event-details">Date: <%= event.getEvent_date() %></p>
		            <p class="event-details">Time: <%= event.getEvent_time() %></p>
		            <p class="event-details">Venue: <%= event.getVenue() %></p>
		            <p class="event-details">Available Seats: <%= event.getAvailable_seats() %></p>
		            <p class="event-details">Ticket Price: $<%= event.getTicketPrice() %></p>
		            <a href="editEvent.jsp?eventId=<%= event.getEvent_id() %>" class="edit-event-button">Edit</a>
		            <a href="deleteEventServlet?eventId=<%= event.getEvent_id() %>" class="delete-event-button">Delete</a>
		        </div>
		    </div>
		<% } %>
	        </section>
	    </main>

    <footer>
        <p>&copy; 2023 Your Event Ticket Booking App</p>
    </footer>

</body>
</html>
