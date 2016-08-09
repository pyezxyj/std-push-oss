var dictType = null;
var dictCompany = null;
$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/web/news/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/news.htm";
	});
});

function initData(){
	// 新闻类型
    var data ={"key":"news_type"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackType);
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}

function doSuccessBackType(res){
	dictType = res.data;
}

//类型转化
function typeFormatter(value, row) {
	for(var i = 0;i < dictType.length;i++){
		if(dictType[i].value == value){
			return dictType[i].remark;
		}
	}
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
			$("#title").html(res.data[0].title);
			$("#keyword").html(res.data[0].keyword);
			$("#type").html(typeFormatter(res.data[0].type));
			$("#author").html(res.data[0].author);
			$("#jumpUrl").html(res.data[0].jumpUrl);
			$("#showUrl").html(res.data[0].showUrl);
			$("#picture").attr({"src":res.data[0].picture});
			$("#content").html(res.data[0].content);
			$("#remark").html(res.data[0].remark);
		}else{
			alert("根据新闻信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}