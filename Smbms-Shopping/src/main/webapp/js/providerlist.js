var providerObj;
var pageSizeParam = 5;
var currPageNoParam = 1;
var totalCount = 0;
var totalPageCount = 0;


function initProviderInfo(queryProCode,queryProName,pageSize,currPageNo) {
	var url = path+"/provider/getProviderByLike.action";
	var params = {
		"proCode":queryProCode,
		"proName":queryProName,
        "pageSize":pageSize,
        "currPageNo":currPageNo
	};
	$.post(url,params,function (data) {
		if (data.status == "200"){
            currPageNoParam = data.data.currPageNo;
            totalCount = data.data.totalCount;
            totalPageCount = data.data.totalPageCount;
			var trHtml = "";
			$(data.data.providerList).each(function () {
				trHtml +="<tr>";
				trHtml +="<td><span>"+this.proCode+"</span></td>";
				trHtml +="<td><span>"+this.proName+"</span></td>";
				trHtml +="<td><span>"+this.proContact+"</span></td>";
				trHtml +="<td><span>"+this.proPhone+"</span></td>";
				trHtml +="<td><span>"+this.proFax+"</span></td>";
				trHtml +="<td><span>"+this.creationDate+"</span></td>";
				trHtml +="<td>";
				trHtml +='<span><a class="viewProvider" href="javascript:;" proid='+this.id+' proname='+this.proName+'><img src="../images/read.png" alt="查看" title="查看"/></a></span>';
				trHtml +='<span><a class="modifyProvider" href="javascript:;" proid='+this.id+' proname='+this.proName+'><img src="../images/xiugai.png" alt="修改" title="修改"/></a></span>';
				trHtml +='<span><a class="deleteProvider" href="javascript:;" proid='+this.id+' proname='+this.proName+'><img src="../images/schu.png" alt="删除" title="删除"/></a></span>';
				trHtml +="<td>";
				trHtml +="</tr>";
            });
            $("#totalCount").text(totalCount);
            $("#AtotalPageCount").text(totalPageCount);
            $("#currPageNo").text(currPageNoParam);
            $(".providerTable tr:gt(0)").remove();
			$(".providerTable").append(trHtml);
		}
    },"JSON");
}

//供应商管理页面上点击删除按钮弹出删除框(providerlist.jsp)
function deleteProvider(obj){
	$.ajax({
		type:"GET",
		url:path+"/provider/deleteProvider.action",
		data:{"id":obj.attr("proid")},
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.status == "200"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
			}else if(data.status == "99999"){//删除失败
				//alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
				changeDLGContent("对不起，删除供应商【"+obj.attr("proname")+"】失败");
			}else if(data.status == "10004"){
				//alert("对不起，供应商【"+obj.attr("proname")+"】不存在");
				changeDLGContent("对不起，供应商【"+obj.attr("proname")+"】不存在");
			}else{
				//alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
				changeDLGContent("对不起，该供应商下有订单信息，不能删除");
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
	$('#removeProv').fadeIn();

}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeProv').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}
$(function(){
    initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,1);
    $("#home").click(function () {
        initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,1);
    });
    $("#prev").click(function () {
        initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,currPageNoParam - 1);
    });
    $("#next").click(function () {
        initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,currPageNoParam + 1);
    });
    $("#end").click(function () {
        initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,totalPageCount);
    });
    $("#jump").click(function () {
        if (parseInt($("#inputPage").val()) > totalPageCount){
            $("#inputPage").val(totalPageCount);
            initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,$("#inputPage").val());
        } else if (parseInt($("#inputPage").val()) < 1){
            $("#inputPage").val(1);
            initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,$("#inputPage").val());
        }
        initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,$("#inputPage").val());
    });
    $("#searchbutton").click(function () {
        initProviderInfo($("#queryProCode").val(),$("#queryProName").val(),pageSizeParam,1);
    });
	$(document).on("click",".viewProvider",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/providerview.jsp?id="+ obj.attr("proid");
	});
	
	$(document).on("click",".modifyProvider",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/providermodify.jsp?id="+ obj.attr("proid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteProvider(providerObj);
	});

	$(document).on("click",".deleteProvider",function(){
		providerObj = $(this);
		changeDLGContent("你确定要删除供应商【"+providerObj.attr("proname")+"】吗？");

		openYesOrNoDLG();
	});

});