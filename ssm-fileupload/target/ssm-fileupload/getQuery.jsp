<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Xiao
  Date: 2018/8/11
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户数据展示</title>
</head>
<body>

    <input type="text" id="name" name="name">
    <input type="button" value="查询" id="btn"><br>
    <table border="1">
        <thead>
            <th>编号</th>
            <th>姓名</th>
            <th>电话</th>
            <th>年龄</th>
            <th>生日</th>
            <th>照片</th>

        </thead>
    </table>


<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">

    $("#btn").click(function () {
        initDate();
    });


    $(function () {
        initDate();
    });

    function initDate() {

        var url="/ssm/getQuery.do";
        var param={};
        var name=$("#name").val();

        if(name!==null){
            param={name:name}
        }
           $.post(url,param,function (data) {
               var html="";
               $(data).each(function () {
                   html += "<tr>";
                   html += "<td>"+this.id+"</td>";
                   html += "<td>"+this.name+"</td>";
                   html += "<td>"+this.phone+"</td>";
                   html += "<td>"+this.age+"</td>";
                   html += "<td>"+this.brithday+"</td>";
                   html += "<td>"+this.file+"</td>";
                   html += "</tr>";
               });
               $("table tr td").remove();
               $("table").append(html);
           },"Json")
    }
</script>
</body>
</html>
