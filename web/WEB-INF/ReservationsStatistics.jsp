<%--
  Created by IntelliJ IDEA.
  User: anesabml
  Date: 2/5/20
  Time: 9:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistiques des reservations</title>
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</head>
<body>
<h1>Statistiques des reservations</h1>
<div id="chart" style="width:600px;height:250px;"></div>
<script>

    var reservationsCount = {
        name: 'Reservations',
        type: 'lines'
    };
    var reservationsX = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    var reservationsY = new Array();

    <c:forEach items="${sessionScope.reservationsByMonth}" var="item" varStatus="i">
        reservationsY[${i.index}] = "${item}";
    </c:forEach>

    reservationsCount.x = reservationsX;
    reservationsCount.y = reservationsY;


    var data = [{
        x: reservationsCount.x,
        y: reservationsCount.y,
        type: 'bar'
    }];
    TESTER = document.getElementById('chart');
    Plotly.newPlot(TESTER, data);

</script>
</body>
</html>
