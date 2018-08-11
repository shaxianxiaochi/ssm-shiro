<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页面</title>
</head>
<body>
<center>
    <form action="/faqs/getFaqs.action" method="post">
        <h1>常见问题检索</h1>
        <span>请输入查询关键字:</span>
        <input type="text" name="cName" value="${cName}">
        <input type="submit" value="查询">
        <a href="/faqs/jump.action">添加常见问题</a>
        <c:if test="${empty faqs}">
            <p>没有找到和"${cName}"相关的内容,请修改关键字重试!!!</p>
        </c:if>
        <c:if test="${not empty faqs}">
        <table border="1">
            <h2>检索结果</h2>
            <thead>
            <th>编号</th>
            <th>标题</th>
            <th>分类</th>
            <th>创建时间</th>
            </thead>
            <c:forEach items="${faqs}" var="faq" varStatus="f">
                <tr <c:if test="${f.count%2==0}">bgcolor="#B0E0E6"</c:if>>
                    <td>${faq.id}</td>
                    <td> <a href="/faqs/getOne.action?id=${faq.id}">${faq.title}</a></td>
                    <td>${faq.cName}</td>
                    <td>${faq.createDate}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </form>
</center>
</body>
</html>
