var dictStatus = null;
var dictCompany = null;
//页面初始化
$(function(){
	//按钮权限判断
	showPermissionControl();
	
	//分页查询
	queryTableData();
	
	//数据初始化
	initData();
	
	// 增加
	$('#addBtn').click(function() {
		window.location.href = $("#basePath").val()+"/web/jobs_addedit.htm";
	});
	
	// 修改菜单绑定事件
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/web/jobs_addedit.htm?code="+selRecords[0].code;
	});
	
	// 删除菜单绑定事件
	$('#dropBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		
		if(!confirm("确认删除岗位["+selRecords[0].code+":"+selRecords[0].name+"]?")){
    		return false;
    	}
    	var url = $("#basePath").val()+"/web/jobs/drop";
    	var data = {code:selRecords[0].code};
		doPostAjax(url, data, doSuccessDelBack);
	});
	
	// 详情菜单绑定事件
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/web/jobs_detail.htm?code="+selRecords[0].code;
	});
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/web/jobs/page"});
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
function queryTableData(){
	//绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/web/jobs/page",
		height : $(window).height() - 180,
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				code : $("#code").val(),
				name : $("#name").val(),
				status : $("#status").val(),
				companyCode :$("#companyCode").val(),
				dateStart: $("#dateStart").val(),
				dateEnd: $("#dateEnd").val(),
				start : params.offset / params.limit + 1,
				limit : params.limit
			};
		},
		queryParamsType : 'limit',
		responseHandler : function(res) {
			return {
				rows : res.data.list,
				total : res.data.totalCount
			};
		},
		pagination : true,
		sidePagination : 'server', // 服务端请求
		totalRows : 0,
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		columns : [{
			field : '',
			title : '',
			align : 'left',
			valign : 'middle',
			checkbox : true
		},{
			field : 'code',
			title : '编号',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'name',
			title : '名称',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'department',
			title : '招聘部门',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'area',
			title : '招聘地区',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'createDatetime',
			title : '创建日期',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		},{
			field : 'status',
			title : '状态',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : statusFormatter
		},{
			field : 'companyCode',
			title : '所属公司',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : companyFormatter
		}]
		
	});
}

function doSuccessBackCompany(res){
	dictCompany = res.data;
	var html = "<option value=''>请选择";
	if(typeof(dictCompany) != "undefined"){//判断undifined
		for(var i = 0;i < dictCompany.length;i++){
			html += "<option value='"+dictCompany[i].code+"'>"+dictCompany[i].name +"</option>";
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

function doSuccessBackJobs(res){
	dictBank = res.data;
	var html = "<option value=''>请选择";
	if(typeof(dictBank) != "undefined"){//判断undifined
		for(var i = 0;i < dictBank.length;i++){
			html += "<option value='"+dictBank[i].code+"'>"+dictBank[i].name +" "+ dictBank[i].subbranch+"</option>";
		}
	}
	$("#subbranchCode").html(html);
}
//删除事件回执方法
function doSuccessDelBack(res) {
	if (res.success == true) {
		alert("删除成功");
		$('#tableList').bootstrapTable('refresh');
	}else{
		alert("删除失败");
	}
}

function doSuccessBackJobs(res){
	dictCompany = res.data;
	var html = "<option value=''>请选择";
	if(typeof(dictCompany) != "undefined"){//判断undifined
		for(var i = 0;i < dictCompany.length;i++){
			html += "<option value='"+dictCompany[i].code+"'>"+dictCompany[i].name +"</option>";
		}
	}
	$("#jobs").html(html);
}

function companyFormatter(value, row) {
	for(var i = 0;i < dictCompany.length;i++){
		if(dictCompany[i].code == value){
			return dictCompany[i].name;
		}
	}
}
//格式化时间
function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}