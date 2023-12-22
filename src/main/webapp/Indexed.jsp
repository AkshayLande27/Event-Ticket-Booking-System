<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Event Ticket Booking</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        header {
            background-color: #4CAF50;
            color: white;
            text-align: center;
            padding: 1em;
        }

        nav {
            background-color: #333;
            padding: 1em;
            text-align: center;
        }

        nav a {
            text-decoration: none;
            color: white;
            margin: 0 15px;
            font-weight: bold;
            font-size: 18px;
        }

        nav a:hover {
            color: #4CAF50;
        }

        main {
            padding: 20px;
        }

        section {
            margin-bottom: 30px;
        }

        h2 {
            color: #4CAF50;
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 1em;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>

<body>

    <header>
        <h1>Welcome to Event Ticket Booking</h1>
    </header>

    <nav>
        <a href="Login.jsp">Login</a>
        <a href="Register.jsp">Register</a>
        <a href="Events.jsp">Browse Events</a>
    </nav>
	
    <main>
        <section>
            <h2>About Us</h2>
            <p>Discover and book exciting events with ease on our platform. Your gateway to unforgettable experiences!</p>
        </section>

        <section>
            <h2>Featured Events</h2>
            <p>Explore our handpicked selection of events that promise entertainment, joy, and unforgettable moments.</p>
           
        </section>
    </main>

    <footer>
        <p>&copy; 2023 Your Event Ticket Booking App</p>
    </footer>

</body>

</html>
