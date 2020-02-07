<%--
  Created by IntelliJ IDEA.
  User: Bahmed
  Date: 07/02/2020
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="annulerReservationServlet" method="post">
 <fieldset>
<jsp:useBean id="reservation" class="Modele.CollectionARC" scope="request"></jsp:useBean>
<p><%out.print(reservation.getActivite().getDesignation());%></p>
<p><% out.print(reservation.getActivite().getPrix());%></p>
<p><%out.print(reservation.getReservation().getDate());%></p>
<p><%out.print(reservation.getCreneaux().getJour());%></p>
<p><%out.print(reservation.getCreneaux().getHeure());%></p>
 <p>code : <input name="code" type="text"></p>
  <input type="submit">

 </fieldset>
</form>
<a href="LoginMembreServlet"><button>retourn</button></a>
</body>
</html>
