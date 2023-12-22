<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Event Ticket Booking Dashboard</title>
    
   <link rel="stylesheet" type="text/css" href="style/style.css">
</head>
<body>

    <header>
        <h1>Welcome to the Event Ticket Booking Dashboard</h1>
    </header>

    <nav>
        <ul>
            <li><a href="dashboard.jsp" ">Home</a></li>
            <li><a href="browseEvents.jsp">Browse Events</a></li>
            <li><a href="myBookings.jsp">My Bookings</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <main>
        <section>
            <h2>Hello, ${sessionScope.username}!</h2>
            <p>Explore and book upcoming events.</p>
            <img class="dashboard-image" src="Image/event1.jpg" alt="Event Image 1">
            <!-- Replace the above src attribute with the actual path to your image -->
        </section>

        <!-- You can add more styled sections with images based on your application's features -->

    </main>

    <footer>
        <p>&copy; 2023 Your Event Ticket Booking App</p>
    </footer>

</body>
</html>
