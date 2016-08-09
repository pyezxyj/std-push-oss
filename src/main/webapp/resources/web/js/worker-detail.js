$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/web/worker/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/worker.htm";
	});
});

function initData(){
	 // 公司列表
   var url = $("#basePath").val() + "/plat/company/list";
   doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}


function doSuccessBackCompany(res){
	dictCompany = res.data;
}


function companyFormatter(value, row) {
	for(var i = 0;i < dictCompany.length;i++){
		if(dictCompany[i].code == value){
			return dictCompany[i].name;
		}
	}
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#companyCode").html(companyFormatter(res.data[0].companyCode));
			$("#code").html(res.data[0].code);
			$("#name").html(res.data[0].name);
			$("#description").html(res.data[0].description);
			$("#position").html(res.data[0].position);
			$("#picture").attr({"src":res.data[0].picture});
			$("#remark").html(res.data[0].remark);
		}else{
			alert("根据公司编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}