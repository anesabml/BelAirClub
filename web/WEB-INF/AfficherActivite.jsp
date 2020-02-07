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
    <title>AfficherActivite </title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="stayle\tableStyle.css">
</head>
<body>
<p class="titlepage">noter Activite</p>
<c:forEach var="list" items="${requestScope.activite}">
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
                        <th>temp</th>
                    </tr>
                    <c:forEach var="creneaux" items="${list.getCreneaux()}">
                        <tr>
                            <td><c:out value="${creneaux.getJour()}"></c:out></td>
                            <td><c:out value="${creneaux.getHeureDebut()}-${creneaux.getHeureFin()}"></c:out></td>
                        </tr>
                    </c:forEach>
                </table>

            </tr>
        </table>
        <button>reserver</button>
    </div>
</c:forEach>

</body>
</html>
