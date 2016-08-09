$(function(){
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/attach/bug/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//通过
	$('#passBtn').click(function() {
		doCheck("1");
	});
	
	//不通过
	$('#nopassBtn').click(function() {
		doCheck("0");
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/attach/bug.htm";
	});
});

function doCheck(checkResult){
	if(isBlank($("#checkDetail").val())){
		alert("审核意见说明不能为空！");
		return;
	}
	var data = {"code":$("#code").html(),"checkResult":checkResult,"checkDetail":$("#checkDetail").val()};
	var url = $("#basePath").val()+"/attach/bug/check";
	doPostAjax(url,data,doSuccessBackCheck);
}

function doSuccessBackCheck(res){
	if (res.success == true) {
		alert("审核操作成功");
		location.href = $("#basePath").val()+"/attach/bug.htm";
	}else{
		alert(res.msg);
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
			$("#alipay").html(res.data[0].alipay);
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
