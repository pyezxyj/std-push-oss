$(function() {
	//按钮权限判断
	showPermissionControl();
	
	//数据初始化
	initData();
	
	// 表格初始化
    queryTableData();
	
	//bug详情
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		location.href = $("#basePath").val()+"/attach/bug_detail.htm?code="+selRecords[0].code;
	});
	
	//bug审核
	$('#checkBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(selRecords[0].status != "0"){
			alert("该记录不是待审核状态");
			return;
		}
		location.href = $("#basePath").val()+"/attach/bug_check.htm?code="+selRecords[0].code;
	});
	
	//bug奖励
	$('#payBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(selRecords[0].status != "1"){
			alert("该记录不是已审核待支付状态");
			return;
		}
		location.href = $("#basePath").val()+"/attach/bug_award.htm?code="+selRecords[0].code;
	});
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/attach/bug/page"});
	});
});

function initData(){
	// bug 状态
    var data ={"key":"bug_status"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackStatus);
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

//状态转化
function statusFormatter(value, row) {
	for(var i = 0;i < dictStatus.length;i++){
		if(dictStatus[i].value == value){
			return dictStatus[i].remark;
		}
	}
}

//详情菜单绑定事件
$('#detailBtn').click(function() {
	var selRecords = $('#tableList').bootstrapTable('getSelections')
	if(selRecords.length <= 0){
		alert("请选择记录");
		return;
	}
	window.location.href = $("#basePath").val()+"/attach/bug_detail.htm?code="+selRecords[0].code;
});

// 列表查询
function queryTableData(){
	// 绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/attach/bug/page",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				code : $("#code").val(),
				module : $("#module").val(),
				period : $("#period").val(),
				status : $("#status").val(),
				dateStart : $("#dateStart").val(),
				dateEnd : $("#dateEnd").val(),
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
		}, {
			field : 'code',
			title : '编号',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'module',
			title : '模块',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'topic',
			title : '主题',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'period',
			title : '期数',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'alipay',
			title : '支付宝账号',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'status',
			title : '状态',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : statusFormatter
		}, {
			field : 'createDatetime',
			title : '提交时间',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		}]
	});
}

function doSuccessDelBack(res) {
	if (res.success == true) {
		alert("删除成功");
		$('#tableList').bootstrapTable('refresh');
	}else{
		alert("删除失败");
	}
}

function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}