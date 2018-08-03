var userObj;
var pageSizeParam = 5;
var currPageNoParam = 1;
var totalCount = 0;
var totalPageCount = 0;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/user/deleteUser.action",
		data:{id:obj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.status == "200"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.status == "99999"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(data.status == "10006"){
				//alert("对不起，用户【"+obj.attr("username")+"】不存在");
				changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
			} else if(data.status == "10007"){
                //alert("这个账户是您登录的账户，不能进行删除！");
                changeDLGContent(data.message);
            }
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){

    initRole();
    getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,1);
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on
	 */
	$(document).on("click",".viewUser",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/userview.jsp?id="+ obj.attr("userid");
	});
	
	$(document).on("click",".modifyUser",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/usermodify.jsp?id="+ obj.attr("userid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(document).on("click",".deleteUser",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});


	$("#home").click(function () {
        getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,1);
    });
	$("#prev").click(function () {
        getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,currPageNoParam - 1);
    });
	$("#next").click(function () {
        getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,currPageNoParam + 1);
    });
	$("#end").click(function () {
        getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,totalPageCount);
    });
	$("#jump").click(function () {
		if (parseInt($("#inputPage").val()) > totalPageCount){
            $("#inputPage").val(totalPageCount);
            getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,$("#inputPage").val());
		} else if (parseInt($("#inputPage").val()) < 1){
            $("#inputPage").val(1);
            getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,$("#inputPage").val());
		}
        getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,$("#inputPage").val());
    });
	$("#searchbutton").click(function () {
        getUserInfo($("#queryname").val(),$("#queryUserRole").val(),pageSizeParam,1);
    });

	/*$(".deleteUser").on("click",function(){
		var obj = $(this);
		if(confirm("你确定要删除用户【"+obj.attr("username")+"】吗？")){
			$.ajax({
				type:"GET",
				url:path+"/jsp/user.do",
				data:{method:"deluser",uid:obj.attr("userid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除用户【"+obj.attr("username")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，用户【"+obj.attr("username")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		}
	});*/
});

function initRole() {
	var url = path+"/role/getRole.action";
	var params = {

	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
			var optionHtml = "";
			$(data.data).each(function () {
				optionHtml += '<option value="'+this.id+'">'+this.roleName+'</option>';
            });
			$("#queryUserRole option:gt(0)").remove();
			$("#queryUserRole").append(optionHtml);
		}
    },"JSON");
}

function getUserInfo(queryname,userRole,pageSize,currPageNo) {
	if (userRole == "0"){
		userRole = null;
	}
	var url = path+"/user/getUser.action";
	var params = {
		"userName":queryname,
		"roleId":userRole,
		"pageSize":pageSize,
		"currPageNo":currPageNo
	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
			currPageNoParam = data.data.currPageNo;
			totalCount = data.data.totalCount;
			totalPageCount = data.data.totalPageCount;
			var trHtml = "";
			$(data.data.userList).each(function () {
                trHtml += "<tr>";
                trHtml += "<td><span>"+this.userCode+"</span></td>";
                trHtml += "<td><span>"+this.userName+"</span></td>";
                if (this.gender == 1){
                    trHtml += "<td><span>男</span></td>";
				} else {
                    trHtml += "<td><span>女</span></td>";
				}
                trHtml += "<td><span>"+this.age+"</span></td>";
                trHtml += "<td><span>"+this.phone+"</span></td>";
                trHtml += "<td><span>"+this.roleName+"</span></td>";
                trHtml += "<td>";
                trHtml += '<span><a class="viewUser" href="javascript:;" userid='+this.id+' username='+this.userName+'><img src="/images/read.png" alt="查看" title="查看"/></a></span>';
                trHtml += '<span><a class="modifyUser" href="javascript:;" userid='+this.id+' username='+this.userName+'><img src="/images/xiugai.png" alt="修改" title="修改"/></a></span>';
                trHtml += '<span><a class="deleteUser" href="javascript:;" userid='+this.id+' username='+this.userName+'><img src="\/images/schu.png" alt="删除" title="删除"/></a></span>';
                trHtml += "</td>";
                trHtml += "</tr>";
            });
			$("#totalCount").text(totalCount);
			$("#AtotalPageCount").text(totalPageCount);
			$("#currPageNo").text(currPageNoParam);
			$(".providerTable tr:gt(0)").remove();
			$(".providerTable").append(trHtml);

		}
    },"JSON");
}