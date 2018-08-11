<%--
  Created by IntelliJ IDEA.
  User: L-PC
  Date: 2017/11/3
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <table>
        <h1>地铁线路详细信息</h1>
        <tr>
            <td>线路名称：</td>
            <td><input type="text" name="subwayName" id="subwayName"></td>
        </tr>
        <tr>
            <td>始发站：</td>
            <td><input type="text" name="startStation" id="startStation"></td>
        </tr>
        <tr>
            <td>终点站：</td>
            <td><input type="text" name="endStation" id="endStation"></td>
        </tr>
        <tr>
            <td>途径站点数：</td>
            <td><input type="text" name="stationNum" id="stationNum"></td>
        </tr>
        <tr>
            <td>首车时间：</td>
            <td><input type="text" name="startTime" id="startTime"></td>
        </tr>
        <tr>
            <td>票价（元）：</td>
            <td><input type="text" name="price" id="price"></td>
        </tr>
        <tr>
            <td colspan="2"><button>更新</button></td>
        </tr>
    </table>
</center>

<script type="text/javascript" src="js/jquery-1.8.3-mh-community.js"></script>
<script type="text/javascript">
    $(function () {
        initData();
        $("button").click(function () {
            update();
        });
    });

    function initData() {
        var url = "subway/getSubwayById";
        var params = {
            "id": $.getUrlParam("id")
        };
        $.post(url, params, function (data) {
            if (data.status == "200") {
                $("#subwayName").val(data.data.subwayName);
                $("#startStation").val(data.data.startStation);
                $("#endStation").val(data.data.endStation);
                $("#stationNum").val(data.data.stationNum);
                $("#startTime").val(data.data.startTime);
                $("#price").val(data.data.price);
            } else {
                alert(data.message);
            }
        }, "JSON");
    }

    function update() {
        var url = "/subway/update";
        var params = {
            "id":$.getUrlParam("id"),
            "subwayName":$("#subwayName").val(),
            "startStation":$("#startStation").val(),
            "endStation":$("#endStation").val(),
            "stationNum":$("#stationNum").val(),
            "startTime":$("#startTime").val(),
            "price":$("#price").val()
        };
        $.post(url, params, function (data) {
            if (data.status == "200") {
                alert(data.message);
                location.href = "index.jsp";
            } else {
                alert(data.message);
            }
        }, "JSON");
    }
</script>
</body>
</html>
