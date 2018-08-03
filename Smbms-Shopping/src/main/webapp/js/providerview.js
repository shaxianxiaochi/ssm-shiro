var backBtn = null;

$(function(){
    getProviderById();
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

function getProviderById() {
	var url = path+"/provider/getProviderById.action";
	var params = {
		"id":$.getUrlParam("id")
	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
			$("#proCode").text(data.data.proCode);
			$("#proName").text(data.data.proName);
			$("#proContact").text(data.data.proContact);
			$("#proPhone").text(data.data.proPhone);
			$("#proFax").text(data.data.proFax);
			$("#proDesc").text(data.data.proDesc);
		}
    },"JSON");
}