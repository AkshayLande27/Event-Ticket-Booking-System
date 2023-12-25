<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
   <link rel="stylesheet" type="text/css" href="style/style.css">
    
</head>
<body>

    <header>
        <h1>Welcome, Admin!</h1>
    </header>

    <nav>
        <ul>
            <li><a href="adminDashboard.jsp">Dashboard</a></li>
            <li><a href="manageEvents.jsp">Manage Events</a></li>
            <li><a href="viewUsers.jsp">View Users</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <main>
        <section>
            <h2>Recent Events</h2>
            <!-- Display a table or list of recent events -->
            <table>
                <thead>
                    <tr>
                        <th>Event ID</th>
                        <th>Event Name</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>venue</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Use JSTL to iterate over a list of recent events -->
                    <c:forEach var="event" items="${recentEvents}">
                        <tr>
                            <td>${event.event_id}</td>
                            <td>${event.event_name}</td>
                            <td>${event.event_date}</td>
                            <td>${event.event_time}</td>
                            <td>${event.venue}</td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Add more sections for other information or features -->

    </main>

    <footer>
        <p>&copy; 2023 Admin Dashboard</p>
    </footer>

</body>
</html>
