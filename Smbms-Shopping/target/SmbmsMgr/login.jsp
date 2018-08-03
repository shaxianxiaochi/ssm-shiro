<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <script type="text/javascript" src="js/jquery-1.8.3-mh-community.js"></script>
    <script type="text/javascript" src="js/jquery.base64.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#submit").click(function () {
                var url = "login/login.action";
                var params = {
                    "userCode":$("#userCode").val(),
                    "userPassword":$.base64.btoa($("#userPassword").val())
                };
                $.post(url,params,function (data) {
                    if (data.status != "200"){
                        $(".info").text(data.message);
                    } else if (data.status == "10009" ){
                        $(".info").text(data.message);
                    } else {
                        location.href = "${pageContext.request.contextPath }/jsp/frame.jsp";
                    }
                },"JSON");
            });
            $("#reset").click(function () {
                $("#userCode").val("");
                $("#userPassword").val("");
            });
        });
    </script>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>超市订单管理系统</h1>
        </header>
        <section class="loginCont">
	        <div class="loginForm" name="actionForm" id="actionForm" >
				<div class="info"></div>
				<div class="inputbox">
                    <label for="userCode">用户名：</label>
					<input type="text" class="input-text" id="userCode" name="userCode" placeholder="请输入用户名" required/>
				</div>	
				<div class="inputbox">
                    <label for="userPassword">密码：</label>
                    <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码" required/>
                </div>	
				<div class="subBtn">
                    <input type="submit" id="submit" value="登录"/>
                    <input type="reset" id="reset" value="重置"/>
                </div>	
			</div>
        </section>
    </section>
</body>
</html>