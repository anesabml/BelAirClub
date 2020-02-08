<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Bahmed
  Date: 05/02/2020
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style\reservir.css">
    <title>centre omnisport bel Air</title>
</head>
<body>
<div>
    <form action="ReserverServlet">
        <fieldset>
            <legend>Reservation Creaneaux :</legend>
            <p>Choisir un creaneaux</p>
            <select name="creneauxReserver" >
                <C:forEach var="list" items="${requestScope.activiteLibre}">
                    <optgroup label="${list.getDesignation()}">
                      <C:forEach var="creneaux" items="${list.getCreneaux()}">
                          <option value="${creneaux.getId()}">
                              jour :<C:out value="${creneaux.getJour()}"></C:out>
                              temp :<C:out value="${creneaux.getHeureDebut()}"></C:out>--<C:out value="${creneaux.getHeureFin()}"></C:out>
                          </option>
                      </C:forEach>
                    </optgroup>
                    </C:forEach>
            </select>
            <input value="reserve" type="submit">
        </fieldset>
    </form>
    <a href="LoginMembreServlet">
        <button>retourn</button>
    </a>
</div>
</body>
</html>
