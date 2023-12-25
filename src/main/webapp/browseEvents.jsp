<%@ page import="com.ekaki.demo.Event" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

       <header>
        <h1>Welcome to the Event Ticket Booking - Browse Events</h1>
    </header>

    <nav>
        <ul>
            <li><a href="dashboard.jsp">Home</a></li>
            <li><a href="browseEvents.jsp">Browse Events</a></li>
            <li><a href="myBookings.jsp">My Bookings</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>


    <main>
        <section>
            <c:if test="${not empty eventDetails}">
                <div class="event-details-container">
                    <h2 class="event-title">${eventDetails.event_name}</h2>
                    <p class="event-details">Date: ${eventDetails.event_date}</p>
                    <p class="event-details">Time: ${eventDetails.event_time}</p>
                    <p class="event-details">Venue: ${eventDetails.event_venue}</p>
                    <p class="event-details">Description: ${eventDetails.description}</p>
                    

                    <!-- You can add a link to book the event using the event ID -->
                    <a href="BookingServlet?eventId=${eventDetails.event_id}" class="book-now-button">Book Now</a>
                </div>
            </c:if>
            <c:if test="${empty eventDetails}">
                <p>Event details not found.</p>
            </c:if>
        </section>
    </main>

    <!-- Footer -->

</body>
</html>
