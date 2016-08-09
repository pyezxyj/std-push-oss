$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/web/jobs/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/jobs.htm";
	});
});

function initData(){
	// 状态
    var data ={"key":"jobs_status"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackStatus);
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}

function doSuccessBackStatus(res){
	dictStatus = res.data;
}

//类型转化
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
		if(res.data.length > 0){
			$("#name").html(res.data[0].name);
			$("#status").html(statusFormatter(res.data[0].status));
			$("#department").html(res.data[0].department);
			$("#area").html(res.data[0].area);
			$("#duty").html(res.data[0].duty);
			$("#claim").html(res.data[0].claim);
			$("#content").html(res.data[0].content);
			$("#description").html(res.data[0].description);
			$("#companyCode").html(companyFormatter(res.data[0].companyCode));
			$("#createDatetime").html(dateFormatter(res.data[0].createDatetime));
		}else{
			alert("根据岗位信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

//格式化时间
function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}