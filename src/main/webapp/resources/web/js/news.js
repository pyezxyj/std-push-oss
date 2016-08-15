var dictType = null;
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
		window.location.href = $("#basePath").val()+"/web/news_addedit.htm";
	});
	
	// 修改菜单绑定事件
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/web/news_addedit.htm?code="+selRecords[0].code;
	});
	
	// 删除菜单绑定事件
	$('#dropBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		
		if(!confirm("确认删除信息["+selRecords[0].code+":"+selRecords[0].title+"]?")){
    		return false;
    	}
    	var url = $("#basePath").val()+"/web/news/drop";
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
		window.location.href = $("#basePath").val()+"/web/news_detail.htm?code="+selRecords[0].code;
	});
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/web/news/page"});
	});
})

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
	var html = "<option value=''>请选择</option>";
	if(typeof(dictType) != "undefined"){//判断undifined
		for(var i = 0;i < dictType.length;i++){
			html += "<option value='"+dictType[i].value+"'>"+dictType[i].remark+"</option>";
		}
	}
	$("#type").html(html);
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

//表格初始化
function queryTableData(){
	//绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/web/news/page",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		showColumns : true,
		queryParams : function(params) {
			return {
				code : $("#code").val(),
				title : $("#title").val(),
				type : $("#type").val(),
				companyCode :getCompanyId(),
				creator :$("#creator").val(),
				dateStart :$("#dateStart").val(),
				dateEnd :$("#dateEnd").val(),
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
			field : 'title',
			title : '标题',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'type',
			title : '新闻类型',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : typeFormatter
		}, {
			field : 'creator',
			title : '创建人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'publishDatetime',
			title : '发布日期',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		}, {
			field : 'companyCode',
			title : '所属公司',
			align : 'left',
			valign : 'middle',
			visible : false,
			formatter : companyFormatter
		}]
		
	});
}
function doSuccessBackNews(res){
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

function doSuccessBackNews(res){
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

//格式化时间
function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}