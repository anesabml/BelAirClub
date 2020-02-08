<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="modele.CollectionARC" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Bahmed
  Date: 06/02/2020
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>centre omnisport bel Air</title>
    <link rel="stylesheet" type="text/css" href="style\table.css">

</head>
<body>
<div class="header">
    <h2>Votre Reservation</h2>
</div>
<div>
    <table id="activite">>
        <tr>
            <th>Etate</th>
            <th>nom d'activite</th>
            <th>prix</th>
            <th>date de reservir</th>
            <th>Jour de jouer</th>
            <th>Heure de debube</th>
            <th>Heure de fin</th>
            <th></th>
            <th></th>
        </tr>
        <tr>
            <%
                ArrayList<CollectionARC> collectionARCS = (ArrayList<CollectionARC>) session.getAttribute("reservations");
                for (CollectionARC collection : collectionARCS) {
                    if (collection.getReservation().isAnnuler()) {
            %>
            <td> annule</td>
            <%
            } else if (collection.getReservation().isEnAttend()) {
            %>
            <td>en attend</td>
            <%
            } else if (collection.getReservation().isConfirme()) {
            %>
            <td>comfirme</td>
            <%
            } else {
            %>
            <td>no comfirme</td>
            <%
                }
            %>
            <td><%out.print(collection.getActivite().getDesignation());%></td>
            <td><% out.print(collection.getActivite().getPrix());%></td>
            <td><%out.print(collection.getReservation().getDate());%></td>
            <td><%out.print(collection.getCreneaux().getJour());%></td>
            <td><%out.print(collection.getCreneaux().getHeureDebut());%></td>
            <td><%out.print(collection.getCreneaux().getHeureFin());%></td>
            <%
                if (collection.getReservation().isConfirme() || collection.getReservation().isEnAttend()) {
            %>
            <td>
                <% out.print("<a href=\"annulerReservationServlet?idR=" + collectionARCS.indexOf(collection) + "\">");%>

                <button id="removeButton">annuler</button>
                </a></td>
        </tr>
        <%
        } else if (!collection.getReservation().isConfirme()) {
        %>
        <td>
            <% out.print("<a href=\"ComfirmeReservationServlet?idR=" + collectionARCS.indexOf(collection) + "\">");%>

            <button id="modifyButton">comfirme</button>
            </a></td>
        <td>
            <% out.print("<a href=\"annulerReservationServlet?idR=" + collectionARCS.indexOf(collection) + "\">");%>
            <button id="removeButton">annuler</button>
            </a></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</div>
</body>
</html>
