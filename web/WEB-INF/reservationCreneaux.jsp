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
    <title>Reservation Creneaux</title>
</head>
<body>
    <form action="">
        <fieldset>
            <select name="creneauxReserver" >
                <C:forEach var="list" items="${requestScope.activiteLibre}">
                    <optgroup label="${list.getDesignation()}">
                      <C:forEach var="creneaux" items="${list.getCreneaux()}">
                          <option value="${creneaux.getId()}"> jour :<C:out value="${creneaux.getJour()}"></C:out><br/>
                              temp :<C:out value="${creneaux.getHeure()}"></C:out>
                          </option>
                      </C:forEach>
                    </optgroup>
                    </C:forEach>
            </select>
            <input value="reserve" type="submit">
        </fieldset>
    </form>
</body>
</html>
