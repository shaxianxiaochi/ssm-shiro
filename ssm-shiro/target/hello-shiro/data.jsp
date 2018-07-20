<%--
  Created by IntelliJ IDEA.
  User: Xiao
  Date: 2018/3/24
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <thead>
    <th>id</th>
    <th>姓名</th>
    <th>年龄</th>
    <th>性别</th>
    <th>生日</th>
    </thead>
    <c:forEach items="${userdata}" var="u">
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.age}</td>
            <td>${u.sex}</td>
            <td>${u.birthday}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
