var billCode = null;
var productName = null;
var productUnit = null;
var productCount = null;
var totalPrice = null;
var providerId = null;
var saveBtn = null;
var backBtn = null;

function priceReg (value){
	value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
		value = value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//去掉特殊符号￥
	if(value.indexOf(".")>0){
		value = value.substring(0,value.indexOf(".")+3);
	}
	return value;
}

/**
 * 获取订单信息
 */
function getBillInfo() {
	var url = path+"/bill/getBill";
	var params = {
		"id":$.getUrlParam("id")
	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
            $("#billCode").val(data.data.billCode);
            $("#productName").val(data.data.productName);
            $("#productUnit").val(data.data.productUnit);
            $("#productCount").val(data.data.productCount);
            $("#totalPrice").val(data.data.totalPrice);
            $("select option").each(function () {
				if (data.data.providerId == this.value){
					this.setAttribute("selected","selected")
				}
            })
			if (data.data.isPayment == 1){
                $("#isPayment").append("");
            	var checkHtml = "<input type=\"radio\" name=\"isPayment\" value=\"1\" checked=\"checked\">未付款\n" +
                    			"<input type=\"radio\" name=\"isPayment\" value=\"2\" >已付款";
            	$("#isPayment").append(checkHtml);
			}else{
                $("#isPayment").append("");
                var checkHtml = "<input type=\"radio\" name=\"isPayment\" value=\"1\">未付款\n" +
                    "<input type=\"radio\" name=\"isPayment\" value=\"2\" checked=\"checked\">已付款";
                $("#isPayment").append(checkHtml);
			}
		}
    },"JSON");
}


function updateBill() {
	var url = path+"/bill/updateBill";
	var params = {
		"id":$.getUrlParam("id"),
		"billCode":$("#billCode").val(),
		"productName":$("#productName").val(),
		"productUnit":$("#productUnit").val(),
		"productCount":$("#productCount").val(),
		"totalPrice": $("#totalPrice").val(),
		"providerId":$("#providerId").val(),
		"isPayment":$("input[name=isPayment]").val()
	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
			location.href = path+"/jsp/billlist.jsp";
		}else{
			window.location.reload();
		}
    },"JSON");
}

$(function(){
	billCode = $("#billCode");
	productName = $("#productName");
	productUnit = $("#productUnit");
	productCount = $("#productCount");
	totalPrice = $("#totalPrice");
	providerId = $("#providerId");
	addBtn = $("#save");
	backBtn = $("#back");
	
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	billCode.next().html("*");
	productName.next().html("*");
	productUnit.next().html("*");
	productCount.next().html("*");
	totalPrice.next().html("*");
	providerId.next().html("*");
	
	$.ajax({
		type:"GET",//请求类型
		url:path+"/provider/getProvider.action",//请求的url
		data:"",//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
            if (data.status == "200"){
                var optionHtml = "<option value=\"0\">请选择</option>";
                $(data.data).each(function () {
                    optionHtml += "<option value=\""+this.id+"\">"+this.proName+"</option>";
                });
                $("select").append(optionHtml);
                getBillInfo();
            }
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(providerId.next(),{"color":"red"},imgNo+" 获取供应商列表error",false);
		}
	});

	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	
	productName.on("focus",function(){
		validateTip(productName.next(),{"color":"#666666"},"* 请输入商品名称",false);
	}).on("blur",function(){
		if(productName.val() != null && productName.val() != ""){
			validateTip(productName.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(productName.next(),{"color":"red"},imgNo+" 商品名称不能为空，请重新输入",false);
		}
		
	});
	
	productUnit.on("focus",function(){
		validateTip(productUnit.next(),{"color":"#666666"},"* 请输入商品单位",false);
	}).on("blur",function(){
		if(productUnit.val() != null && productUnit.val() != ""){
			validateTip(productUnit.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(productUnit.next(),{"color":"red"},imgNo+" 单位不能为空，请重新输入",false);
		}
		
	});
	
	providerId.on("focus",function(){
		validateTip(providerId.next(),{"color":"#666666"},"* 请选择供应商",false);
	}).on("blur",function(){
		if(providerId.val() != null && providerId.val() != "" && providerId.val() != 0){
			validateTip(providerId.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(providerId.next(),{"color":"red"},imgNo+" 供应商不能为空，请选择",false);
		}
		
	});
	
	productCount.on("focus",function(){
		validateTip(productCount.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位",false);
	}).on("keyup",function(){
		this.value = priceReg(this.value);
	}).on("blur",function(){
		this.value = priceReg(this.value);
	});
	
	totalPrice.on("focus",function(){
		validateTip(totalPrice.next(),{"color":"#666666"},"* 请输入大于0的正自然数，小数点后保留2位",false);
	}).on("keyup",function(){
		this.value = priceReg(this.value);
	}).on("blur",function(){
		this.value = priceReg(this.value);
	});
	
	addBtn.on("click",function(){
		productName.blur();
		productUnit.blur();
		providerId.blur();
		if(productName.attr("validateStatus") == "true" 
			&& productUnit.attr("validateStatus") == "true" 
			&& providerId.attr("validateStatus") == "true"){
			if(confirm("是否确认提交数据")){
                updateBill();
			}
		}
	});
	
	backBtn.on("click",function(){
		//alert("modify: "+referer);
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