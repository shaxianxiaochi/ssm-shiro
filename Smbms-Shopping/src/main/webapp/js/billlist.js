var billObj;
var pageSizeParam = 5;
var currPageNoParam = 1;
var totalCount = 0;
var totalPageCount = 0;

function initInfo() {
    initProviderSelect();
    var billName = $("input[name=queryProductName]").val();
    var providerId = $("#queryProviderId").val();
    var isPayment = $("#queryIsPayment").val();
    findBillInfo(billName,providerId,isPayment,pageSizeParam,1);

    $("#home").click(function () {
        findBillInfo(billName,providerId,isPayment,pageSizeParam,1);
    });
    $("#prev").click(function () {
        findBillInfo(billName,providerId,isPayment,pageSizeParam,currPageNoParam - 1);
    });
    $("#next").click(function () {
        findBillInfo(billName,providerId,isPayment,pageSizeParam,currPageNoParam + 1);
    });
    $("#end").click(function () {
        findBillInfo(billName,providerId,isPayment,pageSizeParam,totalPageCount);
    });
    $("#jump").click(function () {
        if (parseInt($("#inputPage").val()) > totalPageCount){
            $("#inputPage").val(totalPageCount);
            findBillInfo(billName,providerId,isPayment,pageSizeParam,$("#inputPage").val());
        } else if (parseInt($("#inputPage").val()) < 1){
            $("#inputPage").val(1);
            findBillInfo(billName,providerId,isPayment,pageSizeParam,$("#inputPage").val());
        }
        findBillInfo(billName,providerId,isPayment,pageSizeParam,$("#inputPage").val());
    });
    $("#searchbutton").click(function () {
        findBillInfo(billName,providerId,isPayment,pageSizeParam,1);
    });
}


function initProviderSelect() {
	var url = path+"/provider/getProvider.action";
	var params = {
		
	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
            var optionHtml = "";
            $(data.data).each(function () {
                optionHtml += "<option value=\""+this.id+"\">"+this.proName+"</option>";
            });
            $("#queryProviderId").append(optionHtml);
		}

    },"JSON")
}

function findBillInfo(billName,providerId,isPayment,pageSize,currPageNo) {
	if (providerId == 0){
		providerId = null;
	}
	if (isPayment == 0){
        isPayment = null;
	}
    var url = path+"/bill/getList";
    var params = {
    	"billName":billName,
		"providerId":providerId,
		"isPayment":isPayment,
        "pageSize":pageSize,
        "currPageNo":currPageNo
	};
    $.post(url,params,function (data) {
        if (data.status == "200"){
            currPageNoParam = data.data.currPageNo;
            totalCount = data.data.totalCount;
            totalPageCount = data.data.totalPageCount;
            var trHtml = "";
            $(data.data.billList).each(function () {
                trHtml += "<tr>";
                trHtml += "<td><span>"+this.billCode+"</span></td>";
                trHtml += "<td><span>"+this.productName+"</span></td>";
                trHtml += "<td><span>"+this.providerName+"</span></td>";
                trHtml += "<td><span>"+this.totalPrice+"</span></td>";
                if (this.isPayment == 1){
                    trHtml += "<td><span>未付款</span></td>";
                } else {
                    trHtml += "<td><span>已付款</span></td>";
                }
                trHtml += "<td><span>"+this.creationDate+"</span></td>";
                trHtml += "<td>";
                trHtml += "<span><a class=\"viewBill\" href=\"javascript:;\"  billId = \""+this.id+"\"><img src=\"/images/read.png\" alt=\"查看\" title=\"查看\"/></a></span>";
                trHtml += "<span><a class=\"modifyBill\" href=\"javascript:;\" billId=\""+this.id+"\" ><img src=\"/images/xiugai.png\" alt=\"修改\" title=\"修改\"/></a></span>\n";
                trHtml += "<span><a class=\"deleteBill\" href=\"javascript:;\" billId = \""+this.id+"\"><img src=\"/images/schu.png\" alt=\"删除\" title=\"删除\"/></a></span>\n";
                trHtml += "</td>";
                trHtml += "</tr>";
            });
            $("#totalCount").text(totalCount);
            $("#AtotalPageCount").text(totalPageCount);
            $("#currPageNo").text(currPageNoParam);
            $(".providerTable tr:gt(0)").remove();
            $(".providerTable").append(trHtml);
        } else {

        }
    },"JSON");
}

$(function () {
    initInfo()
	$(document).on("click",".modifyBill",function () {
        var id = $(this).attr("billId");
        location.href = path+"/jsp/billmodify.jsp?id="+id;
    })

    $(document).on("click",".viewBill",function () {
        var id = $(this).attr("billId");
        location.href = path+"/jsp/billview.jsp?id="+id;
    })
    $(document).on("click",".deleteBill",function () {
        var id = $(this).attr("billId");
        if (confirm("确定要删除吗？")){
            var url = path+"/bill/delete.action";
            var params = {
                "id":id
            };
            $.post(url,params,function (data) {
                if(data.status == "200"){
                    alert("删除成功！");
                    var billName = $("input[name=queryProductName]").val();
                    var providerId = $("#queryProviderId").val();
                    var isPayment = $("#queryIsPayment").val();
                    findBillInfo(billName,providerId,isPayment);
                } else {
                    alert("删除失败！");
                }
            },"JSON");
        }
    })
});

function deleteBill(id) {

}

// //订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
// function deleteBill(obj){
// 	$.ajax({
// 		type:"GET",
// 		url:path+"/jsp/bill.do",
// 		data:{method:"delbill",billid:obj.attr("billid")},
// 		dataType:"json",
// 		success:function(data){
// 			if(data.delResult == "true"){//删除成功：移除删除行
// 				cancleBtn();
// 				obj.parents("tr").remove();
// 			}else if(data.delResult == "false"){//删除失败
// 				//alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
// 				changeDLGContent("对不起，删除订单【"+obj.attr("billcc")+"】失败");
// 			}else if(data.delResult == "notexist"){
// 				//alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
// 				changeDLGContent("对不起，订单【"+obj.attr("billcc")+"】不存在");
// 			}
// 		},
// 		error:function(data){
// 			alert("对不起，删除失败");
// 		}
// 	});
// }
//
// function openYesOrNoDLG(){
// 	$('.zhezhao').css('display', 'block');
// 	$('#removeBi').fadeIn();
// }
//
// function cancleBtn(){
// 	$('.zhezhao').css('display', 'none');
// 	$('#removeBi').fadeOut();
// }
// function changeDLGContent(contentStr){
// 	var p = $(".removeMain").find("p");
// 	p.html(contentStr);
// }
//
// $(function(){
//     initInfo();
// 	$(document).on("click",".viewBill",function () {
//         alert("aaaaaaaaaaaaaaaa");
//     })
// 	$(document).on("click",".viewBill",function(){
// 		var id = $(this).attr(billId);
// 	});
//
// 	$(document).on("click",".modifyBill",function(){
// 		alert($(this).attr(billId));
//         var id = $(this).attr(billId);
//         location.href = "/jsp/frame.jsp?id="+id;
// 	});
// 	$('#no').click(function () {
// 		cancleBtn();
// 	});
//
// 	$('#yes').click(function () {
// 		deleteBill(billObj);
// 	});
//
// 	$(".deleteBill").on("click",function(){
// 		billObj = $(this);
// 		changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
// 		openYesOrNoDLG();
// 	});
//
// 	$("#searchbutton").click(function () {
//         var billName = $("input[name=queryProductName]").val();
//         var providerId = $("#queryProviderId").val();
//         var isPayment = $("#queryIsPayment").val();
//         findBillInfo(billName,providerId,isPayment);
//     });
//
// 	/*$(".deleteBill").on("click",function(){
// 		var obj = $(this);
// 		if(confirm("你确定要删除订单【"+obj.attr("billcc")+"】吗？")){
// 			$.ajax({
// 				type:"GET",
// 				url:path+"/bill.do",
// 				data:{method:"delbill",billid:obj.attr("billid")},
// 				dataType:"json",
// 				success:function(data){
// 					if(data.delResult == "true"){//删除成功：移除删除行
// 						alert("删除成功");
// 						obj.parents("tr").remove();
// 					}else if(data.delResult == "false"){//删除失败
// 						alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
// 					}else if(data.delResult == "notexist"){
// 						alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
// 					}
// 				},
// 				error:function(data){
// 					alert("对不起，删除失败");
// 				}
// 			});
// 		}
// 	});*/
// });