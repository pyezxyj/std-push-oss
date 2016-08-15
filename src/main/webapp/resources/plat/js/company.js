$(function() {
	//按钮权限判断
	showPermissionControl();
	
	// 表格初始化
	queryTableData();
	
	$('#addBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/company_addedit.htm";
	});
	
	//修改公司
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/plat/company_addedit.htm?code="+selRecords[0].code;
	});
	
	// 详情菜单绑定事件
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/plat/company_detail.htm?code="+selRecords[0].code;
	});
	
	//删除
	$('#dropBtn').click(function(){
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(!confirm("确认删除公司["+selRecords[0].name+"]?")){
    		return false;
    	}
		var data = {"code":selRecords[0].code};
		var url = $("#basePath").val()+"/plat/company/drop";
		doPostAjax(url, data, doSuccessDelBack);
	});
	
	// 查询
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/plat/company/page"});
	});
});

// 列表查询
function queryTableData(){
	// 绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/plat/company/page",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				code:$("#code").val(),
				name:$("#name").val(),
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
			field : 'name',
			title : '名称',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'corporation',
			title : '法人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'slogan',
			title : '广告语',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'url',
			title : '域名',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'userid',
			title : '实际控制人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}]
	});
}

function statusFormatter(value, row) {
	for(var i = 0;i < statusData.length;i++){
		if(statusData[i].key == value){
			return statusData[i].value;
		}
	}
}

function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}

function doSuccessDelBack(res) {
	if (res.success == true) {
		alert("删除成功");
		$('#tableList').bootstrapTable('refresh');
	}else{
		alert("删除失败");
	}
}