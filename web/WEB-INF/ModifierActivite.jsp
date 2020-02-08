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
    <title>centre omnisport bel Air</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style\modifierActivity.css">
</head>
<body>
<form method="POST" action="ModifierActiviteServlet?id=${sessionScope.activite.getCode()}">

    <%-- Designation --%>
    <label for="designation">Designation:</label>
    <input id="designation" type="text" name="designation" value="${sessionScope.activite.getDesignation()}" required>

    <%-- Prix --%>
    <label for="prix">Prix:</label>
    <input id="prix" type="number" name="prix" value="${sessionScope.activite.getPrix()}" required>

    <%-- Crenaux --%>
    <label>Crenaux:</label>
    <div id="crenauxInputs">
        <c:forEach var="creneaux" items="${sessionScope.activite.getCreneaux()}">
            <label for="jour">Jour:</label>
            <input id="jour" type="number" name="jour[]" value="${creneaux.getJour()}" required>

            <label for="debut">Hour debut:</label>
            <input id="debut" type="number" name="debut[]" value="${creneaux.getHeureDebut()}" required>

            <label for="fin">Heure fin:</label>
            <input id="fin" type="number" name="fin[]" value="${creneaux.getHeureFin()}" required>

            <label for="places">Nomber de places:</label>
            <input id="places" type="number" name="places[]" value="${creneaux.getNbPlace()}" required>
        </c:forEach>
    </div>
    <input type="submit" name="submit" value="Save">
</form>
</body>
</html>
