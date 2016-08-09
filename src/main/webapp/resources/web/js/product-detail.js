var dictKind = null;
var dictStatus = null;
var dictCompany = null;

$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/web/product/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/product.htm";
	});
});

function initData(){
	// 产品类别
    var data ={"key":"product_kind"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackKind);
    // 产品状态
    var data ={"key":"product_status"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackStatus);
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}


function doSuccessBackKind(res){
	dictKind = res.data;
}

//类别转化
function kindFormatter(value, row) {
	for(var i = 0;i < dictKind.length;i++){
		if(dictKind[i].value == value){
			return dictKind[i].remark;
		}
	}
}

function doSuccessBackStatus(res){
	dictStatus = res.data;
}

//状态转化
function statusFormatter(value, row) {
	for(var i = 0;i < dictStatus.length;i++){
		if(dictStatus[i].value == value){
			return dictStatus[i].remark;
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
		console.log(res.data);
		if(res.data.length > 0){
			$("#code").html(res.data[0].code);
			$("#name").html(res.data[0].name);
			$("#companyCode").html(companyFormatter(res.data[0].companyCode));
			$("#kind").html(kindFormatter(res.data[0].kind));
			$("#status").html(statusFormatter(res.data[0].status));
			$("#picture").attr({"src":res.data[0].picture});
			$("#description").html(res.data[0].description);
		}else{
			alert("根据产品信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}