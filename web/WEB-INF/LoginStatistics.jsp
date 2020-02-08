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
    <title>centre omnisport bel Air</title>
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style\statistics.css">
</head>
<body>
<div class="header">
    <h2>Statistiques des inscriptions</h2>
</div>
<div id="chart" style="width:600px;height:250px;"></div>
<script>

    var loginsCount = {
        name: 'Inscriptions',
        type: 'lines'
    };

    var loginX = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    var loginY = new Array();

    <c:forEach items="${sessionScope.loginsByMonth}" var="item" varStatus="i">
    loginY[${i.index}] = "${item}";
    </c:forEach>

    loginsCount.x = loginX;
    loginsCount.y = loginY;

    var data = [{
        x: loginsCount.x,
        y: loginsCount.y,
        type: 'bar'
    }];

    TESTER = document.getElementById('chart');
    Plotly.newPlot(TESTER, data);

</script>
</body>
</html>
