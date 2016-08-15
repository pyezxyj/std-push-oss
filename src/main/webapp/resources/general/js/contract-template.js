//页面初始化
// 数据字典
var dictContractStatus = null;
var dictContractType = null;
$(function(){
	//按钮权限判断
	showPermissionControl();
	
	//页面数据字典初始化
	initData();
	
	//分页查询
	queryTableData();
	
	//查询
	$('#searchBtn').click(function() {
		//判空和判非数字
		if(isBlank($("#idSearch").val()) || checkNum($("#idSearch").val(),"模板编号")){
			$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/general/contractTemplate/page"});
		}
	});

	//新增
	$('#addBtn').click(function() {
		window.location.href = $("#basePath").val()+"/general/contract_template_add_edit.htm";
	});
	
	//编辑
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		location.href = $("#basePath").val()+"/general/contract_template_add_edit.htm?id="+selRecords[0].id;
	});
	
	//删除
	$('#dropBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(!confirm("确认删除案例["+selRecords[0].id+":"+selRecords[0].title+"]?")){
    		return false;
    	}
    	var url = $("#basePath").val()+"/general/contractTemplate/drop";
    	var data = {id:selRecords[0].id};
		doPostAjax(url, data, doSuccessDelBack);
    });
})
function initData(){
	//状态
	var data= {"key":"contract_template_status"};
	doGetAjaxIsAsync($("#dictUrl").val(), data,false, doSucBackStatus);
	//类型
	var data= {"key":"contract_template_type"};
	doGetAjaxIsAsync($("#dictUrl").val(), data,false, doSucBackType);
}
//表格初始化
function queryTableData(){
	//绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/general/contractTemplate/page",
		
		striped : true,
		singleSelect : true,
		clickToSelect : true,
		queryParams : function(params) {
			return {
				id : $("#idSearch").val(),
				title : $("#titleSearch").val(),
				type : $("#typeSearch").val(),
				status : $("#statusSearch").val(),
				//dateStart : $("#dateStartSearch").val(),
				//dateEnd : $("#dateEndSearch").val(),
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
			field : 'id',
			title : '模板编号',
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
			title : '类型',
			align : 'left',
			valign : 'middle',
			formatter : typeFormatter,
			sortable : false
		},{
			field : 'status',
			title : '状态',
			align : 'left',
			valign : 'middle',
			formatter : statusFormatter,
			sortable : false
		}, {
			field : 'creator',
			title : '创建者',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'createDatetime',
			title : '创建时间',
			align : 'left',
			valign : 'middle',
			formatter : dateFormatter,
			sortable : false
		}, {
			field : 'remark',
			title : '备注',
			align : 'left',
			valign : 'middle',
			sortable : false
		}]
		
	});
}

//时间格式化
function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
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
	$("#statusSearch").html(html);
}//数据字典（合同类型）关联的回执方法
function doSucBackType(res){
	dictContractType = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictContractType) != "undefined"){//判断undifined
		for(var i = 0;i < dictContractType.length;i++){
			html += "<option value='"+dictContractType[i].value+"'>"+dictContractType[i].remark+"</option>";
		}
	}
	$("#typeSearch").html(html);
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
//删除事件回执方法
function doSuccessDelBack(res) {
	if (res.success == true) {
		alert("删除成功");
		$('#tableList').bootstrapTable('refresh');
	}else{
		alert("删除失败");
	}
}