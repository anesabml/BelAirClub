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
    <title>Ajouter Activite</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="style\modifierActivity.css">
</head>
<body>
<div id="form">
    <form method="POST" action="AjouterActiviteServlet">

        <%-- Designation --%>
        <label for="designation">Designation:</label>
        <input type="text" name="designation" id="designation" required>

        <%-- Prix --%>
        <label for="prix">Prix:</label>
        <input type="number" name="prix" id="prix" required>

        <%-- Crenaux --%>
        <label>Crenaux:</label>
        <div id="crenauxInputs">
            <%-- Jour --%>
            <label for="jour">Jour:</label>
            <input type="date" name="jour[]" id="jour" required>
            <%-- Heure debut --%>
            <label for="debut">Hour debut:</label>
            <input type="time" name="debut[]" id="debut" required>
            <%-- Hour fin --%>
            <label for="fin">Heure fin:</label>
            <input type="time" name="fin[]" id="fin" required>
            <%-- Nomber de places --%>
            <label for="places">Nomber de places:</label>
            <input type="number" name="places[]" id="places" required>
        </div>
        <div>
            <button type="button" onclick="ajouterCrenaux()">Ajouter crenaux</button>
        </div>
        <input type="submit" name="submit" value="Ajouter">
    </form>
</div>
</body>
<script>
    function ajouterCrenaux() {
        var crenauxInputs = document.getElementById("crenauxInputs");

        var jourLabel = document.createTextNode("Jour");
        var jourInput = document.createElement("INPUT");
        jourInput.type = "date";
        jourInput.name = "jour[]";

        var debutLable = document.createTextNode("Hour debut");
        var hourDebutInput = document.createElement("INPUT");
        hourDebutInput.type = "time";
        hourDebutInput.name = "debut[]";


        var finLable = document.createTextNode("Hour fin");
        var hourFinInput = document.createElement("INPUT");
        hourFinInput.type = "time";
        hourFinInput.name = "fin[]";

        var placesLabel = document.createTextNode("Places");
        var placesInput = document.createElement("INPUT");
        placesInput.type = "number";
        placesInput.name = "places[]";

        crenauxInputs.append(jourLabel, jourInput, debutLable, hourDebutInput, finLable, hourFinInput, placesLabel, placesInput);
    }
</script>
</html>
