<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查询信息</title>
</head>
<body>
   <form action="/bbs/getAllOrName.do" method="post">
       <span>讨论版区:</span>
       <select name="sname">
           <c:forEach items="${detailList}" var="d">
               <option value="${d.sname}">${d.sname}</option>
           </c:forEach>
       </select>
       <input type="submit" value="投票">
       <a href="/bbs/getName.do">添加信息</a>
       <table border="1">
           <thead>
           <th>帖子ID</th>
           <th>标题</th>
           <th>作者</th>
           <th>发布时间</th>
           <th>回复次数</th>
           </thead>
           <c:forEach items="${detailList}" var="deta">
               <tr>
                   <td>${deta.id}</td>
                   <td>${deta.title}</td>
                   <td>${deta.author}</td>
                   <td>${deta.createDate}</td>
                   <td>${deta.replyCount}</td>
               </tr>
           </c:forEach>
       </table>
   </form>
</body>
</html>
