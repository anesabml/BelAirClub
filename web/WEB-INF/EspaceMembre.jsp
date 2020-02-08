<%@ taglib prefix="o" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>centre omnisport bel Air</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style\menu.css">
</head>
<body>
<body>
<div class="header">
    <h2>Espace Membre</h2>
</div>
<div class="rowContainer">
    <div class="imagestyle"><img src=${sessionScope.photo}></div>
    <c:out value="${sessionScope.membre.getNom()}"></c:out>
    <div class="row"><a href="ConsulterServlet">Notre activites</a></div>
    <div class="row"><a href="CreneauxLibreServlet">Les créneaux libre</a></div>
    <div class="row"><a href="ReservationMembreServlet">Liste de réservation</a></div>
    <div class="row"><a href="LogoutMembreServlet">Logout</a></li>
</div>
</body>
</body>
</html>