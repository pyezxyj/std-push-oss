var dictStatus = null;
var dictCompany = null;
var ueDescription = UE.getEditor('description');
var ueContent = UE.getEditor('content');
$(function() {
	var code = getQueryString('code');
	initData();
	$("#companyCode").val(getCompanyId());
    //	新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改岗位");
		var data = {"code":code};
		var url = $("#basePath").val()+"/web/jobs/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	//提交(保存)
	$('#subBtn').click(function() {
		//1、数据长度校验
		if(!$("#jsForm").valid()){
			return false;
		}
		//2、组装数据
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		//3、发送请求
		var url = $("#basePath").val() + "/web/jobs/"+$("#operate").val();
		doPostAjax(url,data,doSuccessSubBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/jobs.htm";
	});
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 64
			},
			department: {
				required: true,
				maxlength: 15
			},
			duty: {
				required: true,
				maxlength: 64
			},
			claim: {
				required: true,
				maxlength: 255
			},
			companyCode: {
				required: true,
			},
			description: {
				required: true,
				maxlength:255
			},
			area: {
				required: true,
				maxlength:15
			},
			content: {
				required: true,
				maxlength:255
			},
			status: {
				required: true
			}
		}
	});
});
function doSuccessSubBack(res){
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/web/jobs.htm";
	}else{
		alert(res.msg);
	}
}
function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#name").val(res.data[0].name);
			$("#status").val(res.data[0].status);
			$("#department").val(res.data[0].department);
			$("#area").val(res.data[0].area);
			$("#duty").val(res.data[0].duty);
			$("#claim").val(res.data[0].claim);
			ueContent.addListener("ready", function () {
				ueContent.setContent(res.data[0].content);
			});
			ueDescription.addListener("ready", function () {
				ueDescription.setContent(res.data[0].description);
			});
		}else{
			alert("根据岗位信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}
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
	var html = "<option value=''>请选择</option>";
	if(typeof(dictStatus) != "undefined"){//判断undifined
		for(var i = 0;i < dictStatus.length;i++){
			html += "<option value='"+dictStatus[i].value+"'>"+dictStatus[i].remark+"</option>";
		}
	}
	$("#status").html(html);
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