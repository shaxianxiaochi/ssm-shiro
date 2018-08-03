var userName = null;
var birthday = null;
var phone = null;
var userRole = null;
var saveBtn = null;
var backBtn = null;

$(function () {

    initRole();
    userName = $("#userName");
    birthday = $("#birthday");
    phone = $("#phone");
    userRole = $("#userRole");
    saveBtn = $("#save");
    backBtn = $("#back");

    userName.next().html("*");
    birthday.next().html("*");
    phone.next().html("*");
    userRole.next().html("*");


    // $.ajax({
    // 	type:"GET",//请求类型
    // 	url:path+"/jsp/user.do",//请求的url
    // 	data:{method:"getrolelist"},//请求参数
    // 	dataType:"json",//ajax接口（请求url）返回的数据类型
    // 	success:function(data){//data：返回数据（json对象）
    // 		if(data != null){
    // 			var rid = $("#rid").val();
    // 			userRole.html("");
    // 			var options = "<option value=\"0\">请选择</option>";
    // 			for(var i = 0; i < data.length; i++){
    // 				//alert(data[i].id);
    // 				//alert(data[i].roleName);
    // 				if(rid != null && rid != undefined && data[i].id == rid ){
    // 					options += "<option selected=\"selected\" value=\""+data[i].id+"\" >"+data[i].roleName+"</option>";
    // 				}else{
    // 					options += "<option value=\""+data[i].id+"\" >"+data[i].roleName+"</option>";
    // 				}
    //
    // 			}
    // 			userRole.html(options);
    // 		}
    // 	},
    // 	error:function(data){//当访问时候，404，500 等非200的错误状态码
    // 		validateTip(userRole.next(),{"color":"red"},imgNo+" 获取用户角色列表error",false);
    // 	}
    // });


    userName.on("focus", function () {
        validateTip(userName.next(), {"color": "#666666"}, "* 用户名长度必须是大于1小于10的字符", false);
    }).on("blur", function () {
        if (userName.val() != null && userName.val().length > 1
            && userName.val().length < 10) {
            validateTip(userName.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(userName.next(), {"color": "red"}, imgNo + " 用户名输入的不符合规范，请重新输入", false);
        }

    });

    birthday.on("focus", function () {
        validateTip(birthday.next(), {"color": "#666666"}, "* 点击输入框，选择日期", false);
    }).on("blur", function () {
        if (birthday.val() != null && birthday.val() != "") {
            validateTip(birthday.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(birthday.next(), {"color": "red"}, imgNo + " 选择的日期不正确,请重新输入", false);
        }
    });

    phone.on("focus", function () {
        validateTip(phone.next(), {"color": "#666666"}, "* 请输入手机号", false);
    }).on("blur", function () {
        var patrn = /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
        if (phone.val().match(patrn)) {
            validateTip(phone.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(phone.next(), {"color": "red"}, imgNo + " 您输入的手机号格式不正确", false);
        }
    });

    userRole.on("focus", function () {
        validateTip(userRole.next(), {"color": "#666666"}, "* 请选择用户角色", false);
    }).on("blur", function () {
        if (userRole.val() != null && userRole.val() > 0) {
            validateTip(userRole.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(userRole.next(), {"color": "red"}, imgNo + " 请重新选择用户角色", false);
        }

    });

    saveBtn.on("click", function () {
        userName.blur();
        phone.blur();
        birthday.blur();
        userRole.blur();
        if (userName.attr("validateStatus") == "true"
            && phone.attr("validateStatus") == "true"
            && birthday.attr("validateStatus") == "true"
            && userRole.attr("validateStatus") == "true") {
            if (confirm("是否确认要提交数据？")) {
                submit();
            }
        }
    });

    backBtn.on("click", function () {
        //alert("modify: "+referer);
        if (referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4) {
            window.location.href = referer;
        } else {
            history.back(-1);
        }
    });
});


function submit() {
    var url = path+"/user/updateUser.action";
    var params = {
        "id": $.getUrlParam("id"),
        "userName": $("#userName").val(),
        "gender": $("#gender").val(),
        "birthday": $("#birthday").val(),
        "phone": $("#phone").val(),
        "userRole": $("#userRole").val(),
        "address": $("#address").val()
    };
    $.post(url, params, function (data) {
        if (data.status == "200"){
            if (confirm("更新成功，你想返回用户管理页面吗？")){
                window.location.href = path+"/jsp/userlist.jsp";
            } else {
                window.location.reload(true);
            }
        }
    }, "JSON");
}


function getUserInfo() {
    var url = path+"/user/getUserByid.action";
    var params = {
        "id": $.getUrlParam("id")
    };
    $.post(url, params, function (data) {
        if (data.status == "200") {
            $("#userName").val(data.data.userName);
            $("#gender option").each(function () {
                if ($(this).val() == data.data.gender) {
                    $(this).attr("selected", "selected");
                }
            });

            $("#birthday").val(data.data.birthday);
            $("#phone").val(data.data.phone);
            $("#address").val(data.data.address);
            $("#userRole option").each(function () {
                if ($(this).val() == data.data.userRole) {
                    $(this).attr("selected", "selected");
                }
            });
        } else {
            alert(data.message);
        }
    }, "JSON");
}

function initRole() {
    var url = path+"/role/getRole.action";
    var params = {};
    $.post(url, params, function (data) {
        if (data.status == "200") {
            var optionHtml = "";
            $(data.data).each(function () {
                optionHtml += '<option value="' + this.id + '">' + this.roleName + '</option>';
            });
            $("#userRole option:gt(0)").remove();
            $("#userRole").append(optionHtml);
            getUserInfo();
        }
    }, "JSON");
}