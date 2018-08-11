<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加数据</title>
</head>
<body>
<form action="/faqs/insert.action" method="post">
    <table>
        <tr>
            <td colspan="2"><h2>添加常见问题</h2></td>
        </tr>
        <tr>
            <td>分类:</td>
            <td>
            <select name="classid">
                <c:forEach items="${faqsList}" var="faq">
                    <option value="${faq.classid}">${faq.cName}</option>
                </c:forEach>
            </select>
            </td>
        </tr>
        <tr>
            <td>标题:</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>内容:</td>
            <td><textarea name="content">
            </textarea></td>
        </tr>
        <tr>
            <td><input type="submit" value="保存"></td>
            <td><input type="reset"  value="重置"></td>
            <td><input type="button" onclick="window.location='/faqs/getFaqs.action'"  value="放弃"></td>
        </tr>
    </table>
</form>
</body>
</html>
