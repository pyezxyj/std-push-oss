$(function() {
	//按钮权限判断
	showPermissionControl();
	
	// 表格初始化
	queryTableData();
	
	// 查询
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/user/page"});
	});
	
	//添加用户
	$('#addBtn').click(function() {
		location.href = $("#basePath").val()+"/security/user_detail.htm";
	});
	
	//编辑用户
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/security/user_detail.htm?userCode="+selRecords[0].userCode;
	});
	
	//重置密码
	$('#resetBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/security/user_pwd_reset.htm?userCode="+selRecords[0].userCode+"&userName="+encodeURI(encodeURI(selRecords[0].userName));
	});
	
	//删除
	$('#dropBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(!confirm("确认删除用户["+selRecords[0].userCode+":"+selRecords[0].userName+"]?")){
    		return false;
    	}
		var data = {"userCode":selRecords[0].userCode};
		var url = $("#basePath").val()+"/user/drop";
		doPostAjax(url, data, doSuccessDelBack);
	});
	
	//更改角色
	$('#changeRoleBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/security/user_role.htm?userCode="+selRecords[0].userCode+"&userName="+encodeURI(encodeURI(selRecords[0].userName));
	});
});

// 列表查询
function queryTableData(){
	// 绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/user/page",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				userCode : $("#userCodeSearch").val(),
				userName : $("#userNameSearch").val(),
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
			field : 'userCode',
			title : '用户编号',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'userName',
			title : '用户名',
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
			title : '创建时间',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		}, {
			field : 'creator',
			title : '创建人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'updateDatetime',
			title : '更新时间',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		}, {
			field : 'updater',
			title : '更新人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}]
	});
}

function statusFormatter(value, row) {
	var statusData =[{"key":"1","value":"正常"},{"key":"2","value":"锁定"}];
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