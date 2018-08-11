<%--
  Created by IntelliJ IDEA.
  User: L-PC
  Date: 2017/10/14
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="FileUpLoad" method="post" enctype="multipart/form-data">
        上传者：<input name="userName" type="text"><br>
        选择文件：<input name="myfile" type="file"><br>
        <input type="submit" value="提交">
    </form>
    <h1>springmvc文件上传</h1>
    <form action="user/useraddsave.html"
        id="userFom" name="userForm" method="post" enctype="multipart/form-data">
        <div>
            <input type="text" name="userNameA" value="孟鹤">
            <lable for="a_idPicPath">证件照：</lable>
            <input type="file" name="a_idPicPath" id="a_idPicPath">
            <font color="red"></font>
        </div>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
