<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加信息</title>
</head>
<body>
   <form action="/bbs/add.do" method="post">
       <table>
           <tr>
               <td>发布版区</td>
               <td><select name="sortId">
                   <c:forEach items="${name}" var="n">
                       <option value="${n.sortId}">${n.sname}</option>
                   </c:forEach>
               </select></td>
           </tr>
           <tr>
               <td>作者</td>
               <td><input type="text" name="author"></td>
           </tr>
           <tr>
               <td>标题</td>
               <td><input type="text" name="title"></td>
               <td><span id="a"></span></td>
           </tr>
           <tr>
               <td>详细内容</td>
               <td><textarea name="detail">

            </textarea></td>
           </tr>
           <tr>
               <td><input type="submit" value="提交">/td>
           </tr>
       </table>
   </form>

    <script type="text/javascript" src="js/jquery-1.8.3-mh-community.js"></script>
    <script type="text/javascript">
        $(function () {
            $.get("/bbs/getTilte.do","title="+$("input[name=title]").val(),function (data) {
                if(data.status=="200"){
                    $("#a").text("数据库已经存在");
                    location.href="/bbs/getName.do";
                }else {
                    $("#a").text("数据库不存在，可以添加");
                }
            })
        });
    </script>
</body>
</html>
