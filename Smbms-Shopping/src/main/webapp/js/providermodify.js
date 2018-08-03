var proContact = null;
var proPhone = null;
var saveBtn = null;
var backBtn = null;

$(function () {
    getProviderById();
    proContact = $("#proContact");
    proPhone = $("#proPhone");
    saveBtn = $("#save");
    backBtn = $("#back");

    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    proContact.next().html("*");
    proPhone.next().html("*");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    proContact.on("focus", function () {
        validateTip(proContact.next(), {"color": "#666666"}, "* 请输入联系人", false);
    }).on("blur", function () {
        if (proContact.val() != null && proContact.val() != "") {
            validateTip(proContact.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proContact.next(), {"color": "red"}, imgNo + " 联系人不能为空，请重新输入", false);
        }

    });

    proPhone.on("focus", function () {
        validateTip(proPhone.next(), {"color": "#666666"}, "* 请输入手机号", false);
    }).on("blur", function () {
        var patrn = /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
        if (proPhone.val().match(patrn)) {
            validateTip(proPhone.next(), {"color": "green"}, imgYes, true);
        } else {
            validateTip(proPhone.next(), {"color": "red"}, imgNo + " 您输入的手机号格式不正确", false);
        }
    });

    saveBtn.on("click", function () {
        proContact.blur();
        proPhone.blur();
        if (proContact.attr("validateStatus") == "true" &&
            proPhone.attr("validateStatus") == "true") {
            if (confirm("是否确认提交数据")) {
                updateProvider();
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

function getProviderById() {
    var url = path+"/provider/getProviderById.action";
    var params = {
        "id": $.getUrlParam("id")
    };
    $.post(url, params, function (data) {
        console.log(data);
        if (data.status == "200") {
            $("#proCode").val(data.data.proCode);
            $("#proName").val(data.data.proName);
            $("#proContact").val(data.data.proContact);
            $("#proAddress").val(data.data.proAddress);
            $("#proPhone").val(data.data.proPhone);
            $("#proFax").val(data.data.proFax);
            $("#proDesc").val(data.data.proDesc);
        } else {
            alert(data.message);
        }
    }, "JSON");
}

function updateProvider() {
    var url = path+"/provider/updateProvider.action";
    var params = {
        "proCode": $("#proCode").val(),
        "id": $.getUrlParam("id"),
        "proName": $("#proName").val(),
        "proContact": $("#proContact").val(),
        "proPhone": $("#proPhone").val(),
        "proAddress": $("#proAddress").val(),
        "proFax": $("#proFax").val(),
        "proDesc": $("#proDesc").val()
    };
    $.post(url, params, function (data) {
        if (data.status == "200"){
            if (confirm("更新成功，你想去看看吗！")){
                window.location.href = "../jsp/providerlist.jsp";
            } else {
                window.location.reload(true);
            }
        }
    }, "JSON");
}