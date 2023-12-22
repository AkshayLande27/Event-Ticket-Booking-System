<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Browse Events</title>
    
    <!-- Link the external CSS file -->
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
            <div class="event-card">
                <img src="images/event1.jpg" alt="Event Image 1">
                <div class="event-details">
                    <h2 class="event-title">Event Title 1</h2>
                    <p class="event-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eget tristique odio.</p>
                    <p class="event-details">Date: DD/MM/YYYY</p>
                    <p class="event-details">Time: HH:MM AM/PM</p>
                    <p class="event-details">Venue: Event Venue</p>
                    <p class="event-details">Available Seats: X</p>
                    <p class="event-details">Ticket Price: $YYY.YY</p>
                    <a href="BookingServlet?eventId=1" class="book-now-button">Book Now</a>
                </div>
            </div>

            <!-- Add more event cards as needed -->

        </section>
    </main>

    <footer>
        <p>&copy; 2023 Your Event Ticket Booking App</p>
    </footer>

</body>
</html>
