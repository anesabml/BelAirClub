<%--
  Created by IntelliJ IDEA.
  User: Bahmed
  Date: 05/01/2020
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Activites</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style\table.css">
</head>
<body>
<div class="header">
    <h2>Notre Activites</h2>
</div>
<div>
    <c:forEach var="list" items="${requestScope.activite}">
    <div id="activiteInfo">
        <p>Nom:</p>
        <p><c:out value="${list.getDesignation()}"></c:out></p>
        <p>Prix / Creneaux:</p>
        <p><c:out value="${list.getPrix()}"></c:out></p>
    </div>
    <table id="activite">
        <tr>
            <th>Jour</th>
            <th>Heure debut</th>
            <th>Heure fin</th>
        </tr>
        <c:forEach var="creneaux" items="${list.getCreneaux()}">
            <tr>
                <td><c:out value="${creneaux.getJour()}"></c:out></td>
                <td><c:out value="${creneaux.getHeureDebut()}"></c:out></td>
                <td><c:out value="${creneaux.getHeureFin()}"></c:out></td>
            </tr>
        </c:forEach>
    </table>
</div>
<a id="addButton" href="ReserverServlet">
    Reserver
</a>
</c:forEach>
</body>
</html>
