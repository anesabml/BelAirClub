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
    <fieldset>
        <legend>Activite</legend>

        <%-- Designation --%>
        <p class="pclass">Designation : </p>
        <input type="text" name="designation" value="${sessionScope.activite.getDesignation()}" required>

        <%-- Prix --%>
        <p class="pclass">Prix : </p>
        <input type="number" name="prix" value="${sessionScope.activite.getPrix()}" required>

        <%-- Crenaux --%>
        <div>
            <p class="pclass">Creneaux : </p>
            <c:forEach var="creneaux" items="${sessionScope.activite.getCreneaux()}">
                <p class="pclass">Jour: </p>
                <input type="number" name="jour[]" value="${creneaux.getJour()}" required>

                <p class="pclass">Heure de Debut:</p>
                <input type="number" name="debut[]" value="${creneaux.getHeureDebut()}" required>

                <p class="pclass">Heure de fin:</p>
                <input type="number" name="fin[]" value="${creneaux.getHeureFin()}" required>

                <p class="pclass">Nomber de places:</p>
                <input type="number" name="places[]" value="${creneaux.getNbPlace()}" required>
            </c:forEach>
        </div>
        <p><input type="submit" name="submit" value="Save"></p>
    </fieldset>
</form>
</body>
</html>
