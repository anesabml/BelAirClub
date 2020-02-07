<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="Modele.CollectionARC" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Bahmed
  Date: 06/02/2020
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Reservation</title>
</head>
<body>
<%
    ArrayList<CollectionARC> collectionARCS=(ArrayList<CollectionARC>) session.getAttribute("reservations");
    for (CollectionARC collection :collectionARCS ) {
        if (collection.getReservation().isAnnule()) {
%>
<p>Etate : annule</p>
<%
} else if (collection.getReservation().isAttend()) {
%>
<p>Etate : en attend</p>
<%
} else if (collection.getReservation().isComfirme()) {
%>
<p>Etate : comfirme</p>
<%
} else {
%>
<p>Etate : no comfirme</p>
<%
    }
%>
<p><%out.print(collection.getActivite().getDesignation());%></p>
<p><% out.print(collection.getActivite().getPrix());%></p>
<p><%out.print(collection.getReservation().getDate());%></p>
<p><%out.print(collection.getCreneaux().getJour());%></p>
<p><%out.print(collection.getCreneaux().getHeure());%></p>
<%
    if (collection.getReservation().isComfirme()|| collection.getReservation().isAttend()) {
%>
<% out.print("<a href=\"annulerReservationServlet?idR="+collectionARCS.indexOf(collection)+"\">");%>

    <button>annuler</button>
</a>
<%
} else if (!collection.getReservation().isComfirme()) {
%>
<% out.print("<a href=\"annulerReservationServlet?idR="+collectionARCS.indexOf(collection)+"\">");%>
    <button>annuler</button>
</a>
<% out.print("<a href=\"ComfirmeReservationServlet?idR="+collectionARCS.indexOf(collection)+"\">");%>

    <button>comfirme</button>
</a>
<%
        }
    }
%>

</body>
</html>
