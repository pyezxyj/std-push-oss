var dictCompany = null;
$(function() {
	//按钮权限判断
    showPermissionControl();
	
	initData();
	
	// 表格初始化
	queryTableData();
	
	//添加
	$('#addBtn').click(function() {
		location.href = $("#basePath").val()+"/web/partner_addedit.htm";
	});
	
	//修改
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/web/partner_addedit.htm?code="+selRecords[0].code;
	});
	
	//删除
	$('#dropBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(!confirm("确认删除合伙人["+selRecords[0].code+":"+selRecords[0].name+"]?")){
    		return false;
    	}
		var data = {"code":selRecords[0].code};
		var url = $("#basePath").val()+"/web/partner/drop";
		doPostAjax(url, data, doSuccessDelBack);
	});
	
	// 详情

	
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/web/partner_detail.htm?code="+selRecords[0].code;
	});
	
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/web/partner/page"});
	});
});

function initData(){
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
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

// 列表查询
function queryTableData(){
	// 绑定列表
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/web/partner/page",
		height : $(window).height() - 180,
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				code : $("#code").val(),
				name : $("#name").val(),
				companyCode :$("#companyCode").val(),
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
			field : 'name',
			title : '名称',
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
			field : 'companyCode',
			title : '所属公司',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : companyFormatter
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