<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>centre omnisport bel Air</title>
    <meta charset="UTF-8">
</head>
<body>
<H1>Espace Membre</H1>

<nav>
    <ol>
        <li><img src=${sessionScope.photo}></li>
        <li><a href="">Notre activites</a></li>
        <li><a href="CreneauxLibreServlet">les créneaux libre</a></li>
        <li><a href="ReservationMembreServlet">liste de réservation</a></li>
        <li><a href="LogoutMembreServlet"></a></li>
    </ol>
</nav>
</body>
</html>