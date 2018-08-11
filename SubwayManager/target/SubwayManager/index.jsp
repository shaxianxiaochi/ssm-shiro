<%--
  Created by IntelliJ IDEA.
  User: L-PC
  Date: 2017/11/3
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>地铁线路信息列表</title>
</head>
<body>
    <center>
        <table>
            <h1>地铁线路信息列表</h1>
            <tr>
                <td>线路名称</td>
                <td>始发站</td>
                <td>终点站</td>
                <td>途经站点数</td>
            </tr>

        </table>
    </center>
<script type="text/javascript" src="js/jquery-1.8.3-mh-community.js"></script>
<script type="text/javascript">
    $(function () {
        initData();
        $(document).on("click","button",function () {
            if (confirm("确定删除吗？")){
                deleteById($(this).attr("subwayId"));
            } else {

            }
        });
    });

    function initData() {
        var url = "subway/getSubwayList";
        var params = {};
        $.post(url,params,function (data) {
            if (data.status == "200"){
                var trHtml = "";
                $(data.data).each(function () {
                    trHtml += "<tr>";
                    trHtml += "<td><a href='update.jsp?id="+this.id+"'>"+this.subwayName+"</a></td>";
                    trHtml += "<td>"+this.startStation+"</td>";
                    trHtml += "<td>"+this.endStation+"</td>";
                    trHtml += "<td>"+this.stationNum+"</td>";
                    trHtml += "<td><button subwayId='"+this.id+"'>删除</button></td>";
                    trHtml += "</tr>";
                });
                $("table tr:gt(0)").remove();
                $("table").append(trHtml);
            } else {
                alert(data.message);
            }
        },"JSON");
    }
    function deleteById(id) {
        var url = "subway/delete";
        var params = {
            "id":id
        };
        $.post(url,params,function (data) {
            if (data.status == "200"){
                alert(data.message);
                initData()
            } else {
                alert(data.message);
            }
        },"JSON");
    }
</script>
</body>
</html>
