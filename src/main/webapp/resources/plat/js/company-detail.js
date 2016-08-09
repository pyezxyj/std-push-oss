$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/company/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/company.htm";
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#code").html(res.data[0].code);
			$("#name").html(res.data[0].name);
			$("#userid").html(userFormatter(res.data[0].userid));
			$("#corporation").html(res.data[0].corporation);
			$("#telephone").html(res.data[0].telephone);
			$("#fax").html(res.data[0].fax);
			$("#email").html(res.data[0].email);
			$("#url").html(res.data[0].url);
			$("#logo").attr({"src":res.data[0].logo});
			$("#address").html(res.data[0].address);
			$("#barCode").attr({"src":res.data[0].barCode});
			$("#copyright").html(res.data[0].copyright);
			$("#slogan").html(res.data[0].slogan);
			$("#longitude").html(res.data[0].longitude);
			$("#latitude").html(res.data[0].latitude);
			$("#description").html(res.data[0].description);
		}else{
			alert("根据公司编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

var user = null;

function initData(){
    var url = $("#basePath").val() + "/user/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackUser);
}

function doSuccessBackUser(res){
	user = res.data;
}

//类型转化
function userFormatter(value, row) {
	for(var i = 0;i < user.length;i++){
		if(user[i].userCode == value){
			return user[i].userName;
		}
	}
}