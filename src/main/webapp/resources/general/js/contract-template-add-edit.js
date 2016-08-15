//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('content');
//页面初始化
var dictContractStatus = null;
var dictContractType = null;
$(function(){
	//页面数据字典初始化
	initData();
	var id = getQueryString("id");
	//新增修改判断
	if(isBlank(id)){
		$("#operate").val("add");
	}else{
		$("#operate").val("edit");
		$("#operContent").text("修改合同模板");
		var data = {"id":id,"start":"0",limit:"10"};
		var url = $("#basePath").val()+"/general/contractTemplate/page";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交
	$('#subBtn').click(function() {
	    if(!$("#jsForm").valid()){
			return false;
		}
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		var url = $("#basePath").val()+"/general/contractTemplate/" + $("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/general/contract_template.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			title: {
				required: true,
				maxlength: 255
			},
			content: {
				required: true,
			},
			type: {
				required: true,
			},
			status: {
				required: true,
			},
			remark: {
				required: false,
				maxlength: 255
			}
		}
	});
});
function initData(){
	//状态
	var data= {"key":"contract_template_status"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackStatus);
	//类型
	var data= {"key":"contract_template_type"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackType);
}

//数据字典（合同状态）关联的回执方法
function doSucBackStatus(res){
	dictContractStatus = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictContractStatus) != "undefined"){//判断undifined
		for(var i = 0;i < dictContractStatus.length;i++){
			html += "<option value='"+dictContractStatus[i].value+"'>"+dictContractStatus[i].remark+"</option>";
		}
	}
	$("#status").html(html);
}//数据字典（合同类型）关联的回执方法
function doSucBackType(res){
	dictContractType = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictContractType) != "undefined"){//判断undifined
		for(var i = 0;i < dictContractType.length;i++){
			html += "<option value='"+dictContractType[i].value+"'>"+dictContractType[i].remark+"</option>";
		}
	}
	$("#type").html(html);
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.list != null){
			var result = res.data.list[0];
			$("#id").val(result.id);
			$("#title").val(result.title);
			ue.ready(function(){
				ue.setContent(result.content);
			});
			$("#type").val(result.type);
			$("#status").val(result.status);
			$("#remark").val(result.remark);
		}else{
			alert("根据合同编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/general/contract_template.htm";
	}else{
		alert(res.msg);
	}
}

//状态列表格式化
function statusFormatter(value, row) {
	for(var i = 0;i < dictContractStatus.length;i++){
		if(dictContractStatus[i].value == value){
			return dictContractStatus[i].remark;
		}
	}
}
//类型列表格式化
function typeFormatter(value, row) {
	for(var i = 0;i < dictContractType.length;i++){
		if(dictContractType[i].value == value){
			return dictContractType[i].remark;
		}
	}
}