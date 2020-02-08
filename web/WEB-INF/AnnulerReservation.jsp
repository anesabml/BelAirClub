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
    <title>centre omnisport bel Air</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="style\annulerComfimeSyle.css">
<div>
<form action="annulerReservationServlet" method="post">
    <fieldset>
        <legend>Annuler Reservation</legend>
        <jsp:useBean id="reservation" class="modele.CollectionARC" scope="request"></jsp:useBean>
        <table>
            <tr>
                <td>
                    nom d'activite :
                </td>
                <td><%out.print(reservation.getActivite().getDesignation());%></td>
            </tr>
            <tr>
                <td>prix :</td>
                <td><%out.print(reservation.getActivite().getPrix());%></td>
            </tr>
            <tr>
                <td>date de reservation :</td>
                <td><%out.print(reservation.getReservation().getDate());%></td>
            </tr>
            <tr>
                <td>jour de jouer :</td>
                <td><%out.print(reservation.getCreneaux().getJour());%></td>
            </tr>
            <tr>
                <td>heure de debut :</td>
                <td><%out.print(reservation.getCreneaux().getHeureDebut());%></td>
            </tr>
            <tr>
                <td>heure de fin :</td>
                <td><%out.print(reservation.getCreneaux().getHeureFin());%></td>
            </tr>
            <tr>
                <td>code :</td>
                <td><input name="code" type="text"></td>
            </tr>
        </table>
        <input type="submit">

    </fieldset>
</form>
<a href="LoginMembreServlet">
    <button>retourn</button>
</a>
</div>
</body>
</html>
