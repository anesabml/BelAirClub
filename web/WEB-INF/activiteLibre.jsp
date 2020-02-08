<%--
  Created by IntelliJ IDEA.
  User: Bahmed
  Date: 03/02/2020
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>centre omnisport bel Air</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style\table.css">
</head>
<body>
<div class="header">
    <h2>Activites libre</h2>
</div>
<c:if test="${requestScope.get(\"activiteLibre\")==null}">
    <p>Pas de creneaux libre</p>
</c:if>
<div>

    <c:forEach var="list" items="${requestScope.activiteLibre}">
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
            <th>nombre de place reste</th>
        </tr>
        <c:forEach var="creneaux" items="${list.getCreneaux()}">
            <tr>
                <td><c:out value="${creneaux.getJour()}"></c:out></td>
                <td><c:out value="${creneaux.getHeureDebut()}"></c:out></td>
                <td><c:out value="${creneaux.getHeureFin()}"></c:out></td>
                <c:choose><c:when test="${creneaux.getNbPlace()>0}">
                    <td><c:out value="${creneaux.getNbPlace()}"></c:out></td>
                </c:when>
                    <c:otherwise>
                        <td>list d'attend</td>
                    </c:otherwise>
                </c:choose>

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
