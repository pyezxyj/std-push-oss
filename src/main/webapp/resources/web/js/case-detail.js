$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/web/case/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/case.htm";
	});
});

function initData(){
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}

function doSuccessBackCompany(res){
	dictCompany = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictCompany) != "undefined"){//判断undifined
		for(var i = 0;i < dictCompany.length;i++){
			html += "<option value='"+dictCompany[i].code+"'>"+dictCompany[i].name+"</option>";
		}
	}
	$("#companyCode").html(html);
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
			$("#status").html(Dict.getName('case_status', res.data[0].status));
			$("#url").html(res.data[0].url);
			$("#logo").attr({"src":res.data[0].logo});
			$("#picture").attr({"src":res.data[0].picture});
			$("#description").html(res.data[0].description);
		}else{
			alert("根据新闻信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}