<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
 		<div class="page-bar">
			<ul class="page-num-ul clearfix" style="text-align: center">
				<li>共<span id="totalCount"></span>条记录&nbsp;&nbsp; <span id="currPageNo"></span>/<span id="AtotalPageCount"></span>页</li>
				<a id = "home" href="javascript:;">首页</a>
				<a id = "prev" href="javascript:;">上一页</a>
				<a id = "next" href="javascript:;">下一页</a>
				<a id = "end" href="javascript:;">最后一页</a>
			</ul>
		 <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" id="jump">GO</button>
		</span>
		</div> 
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/rollpage.js"></script>
</html>