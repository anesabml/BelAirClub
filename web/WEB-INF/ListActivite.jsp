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
    <title>List des activites</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style\table.css">
</head>
<body>
<div class="header">
    <h2>Notre Activites</h2>
</div>
<a href="AjouterActiviteServlet" id="addButton">
    Ajouter
</a>
<table id="activite">
    <tr>
        <th>Nom</th>
        <th>Prix / Creneaux</th>
        <th class="action">Modifier</th>
        <th class="action">Supprimer</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.activites}">
        <tr>
                <%-- Nom de l'activity --%>
            <td><c:out value="${item.getDesignation()}"></c:out></td>

                <%-- Prix de l'activity --%>
            <td><c:out value="${item.getPrix()}"></c:out></td>
                <%-- Action des activity --%>
            <td><a href="ModifierActiviteServlet?id=${item.getCode()}" id="modifyButton">
                Modifier
            </a></td>
            <td><a href="DeleteActiviteServlet?id=${item.getCode()}" id="removeButton">
                Supprimer
            </a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
