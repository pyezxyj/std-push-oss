$(function() {
	initData();
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/attach/bug/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//奖励
	$('#payBtn').click(function() {
		if(isBlank($("#money").val())){
			alert("金额不能为空！");
			return;
		}
		var data = {"code":$("#code").html(),"money":$("#money").val()};
		var url = $("#basePath").val()+"/attach/bug/pay";
		doPostAjax(url,data,doSuccessBackPay);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/attach/bug.htm";
	});
});

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
			alert("根据bug编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function initData(){
	// 奖励
    var data ={"key":"bug_award"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackMoney);
}

function doSuccessBackMoney(res){
	dictMoney = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictMoney) != "undefined"){//判断undifined
		for(var i = 0;i < dictMoney.length;i++){
			html += "<option value='"+dictMoney[i].value+"'>"+dictMoney[i].remark+"</option>";
		}
	}
	$("#money").html(html);
}

function doSuccessBackPay(res){
	if (res.success == true) {
		alert("支付成功");
		location.href = $("#basePath").val()+"/attach/bug.htm";
	}else{
		alert(res.msg);
	}
}

function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}