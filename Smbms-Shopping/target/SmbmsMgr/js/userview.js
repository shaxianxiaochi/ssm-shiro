var backBtn = null;

$(function(){
    getUserInfo();
	backBtn = $("#back");
	backBtn.on("click",function(){
		//alert("view : "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});

function getUserInfo() {
	var url = path+"/user/getUserByid.action";
	var params = {
		"id":$.getUrlParam("id")
	};
	$.post(url,params,function (data) {
		console.log(data);
		if (data.status == "200"){
			$("#userCode").text(data.data.userCode);
			$("#userName").text(data.data.userName);
			if (data.data.gender == 1){
                $("#sex").text("男");
			} else {
                $("#sex").text("女 ");
			}

			$("#birthday").text(data.data.birthday);
			$("#phone").text(data.data.phone);
			$("#address").text(data.data.address);
			$("#roleName").text(data.data.roleName);
		} else {
			alert(data.message);
		}
    },"JSON");
}