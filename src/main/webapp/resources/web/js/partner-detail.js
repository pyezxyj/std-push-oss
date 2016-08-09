$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/web/partner/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/partner.htm";
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
			var data = res.data[0];
			$("#companyCode").html(companyFormatter(data.companyCode));
			$("#code").html(data.code);
			$("#name").html(data.name);
			$("#url").html(data.url);
			$("#logo").attr({"src":data.logo});
			$("#description").html(data.description);
		}else{
			alert("根据合作伙伴编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}