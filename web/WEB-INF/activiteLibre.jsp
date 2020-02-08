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
    <title>activite libre</title>

    <link rel="stylesheet" href="style\tableStyle.css">
    <meta charset="UTF-8">
</head>
<body>
<p class="titlepage">noter Activite</p>
<c:if test="${requestScope.get(\"activiteLibre\")==null}">
    <p>no creneaux libre </p>
</c:if>
<c:forEach var="list" items="${requestScope.activiteLibre}">
    <div>
        <table>
            <tr>
                <td>nom d'activite :</td>
                <td><c:out value="${list.getDesignation()}"></c:out>
                </td>
            </tr>
            <tr>
                <td>prix par creneaux :</td>
                <td><c:out value="${list.getPrix()}"></c:out></td>
            </tr>
            <tr>
                <table class="smoltable">


                    <tr>
                        <th>jour</th>
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

            </tr>
        </table>
        <a href="ReserverServlet">
            <button> reserver</button>
        </a>
    </div>
</c:forEach>

</body>
</html>
