var backBtn = null;

$(function(){
    getBillInfo();
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

function getBillInfo() {
    var url = path+"/bill/getBill";
    var params = {
        "id":$.getUrlParam("id")
    };
    $.post(url,params,function (data) {
        if (data.status == "200"){
            $("#billCode").text(data.data.billCode);
            $("#productName").text(data.data.productName);
            $("#productUnit").text(data.data.productUnit);
            $("#productCount").text(data.data.productCount);
            $("#totalPrice").text(data.data.totalPrice);
            $("#providerName").text(data.data.providerName);
            if (data.data.isPayment == 1){
                $("#isPayment").text("未付款");
            }else{
                $("#isPayment").text("已付款");
            }
        }
    },"JSON");
}