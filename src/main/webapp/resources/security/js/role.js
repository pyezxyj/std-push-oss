//数据字典
var dictLevel = null;
$(function() {
	//按钮权限判断
	showPermissionControl();
	
	//数据字典初始化
	initData();
	
	//表格初始化
	queryTableData();

	//查询
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/role/page"});
	});
	
	//新增
	$('#addBtn').click(function() {
		window.location.href = $("#basePath").val()+"/security/role_detail.htm";
	});
	
	//修改
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/security/role_detail.htm?roleCode="+selRecords[0].roleCode;
	});
	
	//删除
	$('#dropBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(!confirm("确认删除角色["+selRecords[0].roleName+":"+selRecords[0].roleCode+"]?")){
    		return false;
    	}
    	var url = $("#basePath").val()+"/role/drop";
    	var data = {roleCode:selRecords[0].roleCode};
    	doPostAjax(url, data, doSucBackDrop);
	});
	
	// 分配菜单
	$('#changeBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
      	window.location.href = $("#basePath").val()+"/security/role_menu.htm?roleCode="+selRecords[0].roleCode+"&roleName="+encodeURI(encodeURI(selRecords[0].roleName));
	});
});

//数据字典初始化
function initData(){
	//获取数据字典
	var data = {"key":"role_level"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackLevel);
}

// 下拉框初始化数据
function doSucBackLevel(res){
	var data = res.data;
	dictLevel = data;
	var html = "<option value=''>请选择</option>";
	if(typeof(data) != "undefined"){//判断undifined
		for(var i = 0;i < data.length;i++){
			if(data[i].key == $("#roleLevelSearch").val()){
				html += "<option selected='selected' value='"+data[i].value+"'>"+data[i].remark+"</option>";
			}else{
				html += "<option value='"+data[i].value+"'>"+data[i].remark+"</option>";
			}
		}
	}
	$("#roleLevelSearch").html(html);
}

//表格初始化
function queryTableData(){
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/role/page",
		height : $(window).height() - 180,
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				roleCode : $("#roleCodeSearch").val(),
				roleLevel : $("#roleLevelSearch").val(),
				createDatetimeStart : $("#createDatetimeStartSearch").val(),
				createDatetimeEnd : $("#createDatetimeEndSearch").val(),
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
			field : 'roleCode',
			title : '角色编号',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'roleName',
			title : '角色名称',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'roleLevel',
			title : '角色等级',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : roleLevelFormatter
		}, {
			field : 'creator',
			title : '创建人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'createDatetime',
			title : '创建时间',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		}, {
			field : 'remark',
			title : '备注',
			align : 'left',
			valign : 'middle',
			sortable : false
		}]
	});
}

//表格数据字典转化
function roleLevelFormatter(value, row) {
	var dictLevel=["","管理员级别","运营级别","财务级别"]
	for(var i = 1;i < dictLevel.length;i++){
		if(i == value){
			return dictLevel[i];
		}
	}
}

//表格时间格式转化
function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}

//操作回调方法
function doSucBackDrop(res) {
	if (res.success == true) {
		alert("删除成功");
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/role/page"});
	}
}