<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取单条信息</title>
</head>
<body>
<center>
<table>
    <tr>
        <td colspan="2"><h2>常见问题检索</h2></td>
    </tr>
    <tr>
        <td>分类:</td>
        <td><p>${faqs.cName}</p></td>
    </tr>
    <tr>
        <td>添加时间:</td>
        <td><p>${faqs.createDate}</p></td>
    </tr>
    <tr>
        <td>标题:</td>
        <td><p>${faqs.title}</p></td>
    </tr>
    <tr>
        <td>内容:</td>
        <td><textarea rows="5" cols="25">
            ${faqs.content}
        </textarea></td>
    </tr>
    <tr>
        <td><button onclick="window.location='/faqs/getFaqs.action'">返回</button></td>
    </tr>
</table>
</center>
</body>
</html>
