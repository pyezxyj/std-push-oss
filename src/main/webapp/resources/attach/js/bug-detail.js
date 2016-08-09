$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/attach/bug/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/attach/bug.htm";
	});
});

function initData(){
	// 状态
    var data ={"key":"bug_status"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackStatus);
    
	// 审核
    var data ={"key":"check_result"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackCheck);
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

function doSuccessBackCheck(res){
	dictCheck = res.data;
}

//审核转化
function checkFormatter(value, row) {
	for(var i = 0;i < dictCheck.length;i++){
		if(dictCheck[i].value == value){
			return dictCheck[i].remark;
		}
	}
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#code").html(res.data[0].code);
			$("#module").html(res.data[0].module);
			$("#topic").html(res.data[0].topic);
			$("#content").html(res.data[0].content);
			$("#attachment").html(res.data[0].attachment);
			
			$("#period").html(res.data[0].period);
			$("#createDatetime").html(dateFormatter(res.data[0].createDatetime));
			$("#checkUser").html(res.data[0].checkUser);
			$("#checkResult").html(checkFormatter(res.data[0].checkResult));
			$("#checkDetail").html(res.data[0].checkDetail);
			
			$("#checkDatetime").html(dateFormatter(res.data[0].checkDatetime));
			$("#alipay").html(res.data[0].alipay);
			$("#money").html(moneyFormat(res.data[0].money));
			$("#moneyDatetime").html(dateFormatter(res.data[0].moneyDatetime));
			$("#status").html(statusFormatter(res.data[0].status));
		}else{
			alert("根据Bug编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}
function resultFormatter(value, row){
	return resultFormat(value,'remark');
}