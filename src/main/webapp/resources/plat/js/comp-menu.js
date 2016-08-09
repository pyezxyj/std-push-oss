var dictCode = null;
$(function() {
	//按钮权限判断
	showPermissionControl();
	initData();
	
    var companyCode ='';
	doGetAjaxIsAsync($("#basePath").val()+"/plat/company/list", {userid:getUserId()}, false, function(res) {
		if (res.data.length > 0) {
			companyCode = res.data[0].code;
		}
	});
	
	// 表格初始化
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/plat/menu/page",
		height : $(window).height() - 180,
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				name:$("#name").val(),
				parentCode:$("#parentCode").val(),
				companyCode:companyCode,
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
			field : 'parentCode',
			title : '对应父菜单',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : codeFormatter
			
		}, {
			field : 'orderNo',
			title : '顺序',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'templetCode',
			title : '内容格式',
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
		}]
	});
	
	$('#addBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/comp_menu_addedit.htm";
	});
	
	//修改公司菜单
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		else if(selRecords[0].code == "Menu2016072030990400" || selRecords[0].code == "Menu2016072050826150"){
			alert("该菜单为定制页面不可修改！");
			return;
		}
		else
			window.location.href = $("#basePath").val()+"/plat/comp_menu_addedit.htm?code="+selRecords[0].code;
	});
	
	//插入内容
	$('#insertBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		else if(selRecords[0].code == "Menu2016072030990400" || selRecords[0].code == "Menu2016072050826150"){
			alert("该菜单为定制页面不可插入内容！");
			return;
		}
		else
			window.location.href = $("#basePath").val()+"/plat/comp_menu_addcontent.htm?code="+selRecords[0].code;
	});
	
	// 详情菜单绑定事件
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/plat/comp_menu_detail2.htm?code="+selRecords[0].code+"&companyCode="+selRecords[0].companyCode;
	});
	
	//上下架
	$('#dropBtn').click(function(){
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(selRecords[0].status == 0) {
				if(!confirm("确认上架["+selRecords[0].name+"]?")){
					return false;
				}
			}
		else {
			if(!confirm("确认下架["+selRecords[0].name+"]?")){
	    		return false;
	    	}
		}
		var data = {"code":selRecords[0].code};
		var url = $("#basePath").val()+"/plat/menu/change";
		doPostAjax(url, data, doSuccessDelBack);
	});
	
	// 查询
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/plat/menu/page"});
	});
});

//状态转化
function statusFormatter(value, row) {
	dictStatus = Dict.getName('menu_status');
	for(var i = 0;i < dictStatus.length;i++){
		if(dictStatus[i].value == value){
			return dictStatus[i].remark;
		}
	}
}

// 列表查询
function doSuccessDelBack(res) {
	if (res.success == true) {
		alert("操作成功");
		$('#tableList').bootstrapTable('refresh');
	}else{
		alert("操作失败");
	}
}

function initData(){
	// 列表
    var url = $("#basePath").val() + "/plat/menu/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCode);
}

//类型转化
function doSuccessBackCode(res){
	dictCode = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictCode) != "undefined"){//判断undifined
		for(var i = 0;i < dictCode.length;i++){
			html += "<option value='"+dictCode[i].code+"'>"+dictCode[i].name+"</option>";
		}
	}
	$("#parentCode").html(html);
}

function codeFormatter(value, row) {
	for(var i = 0;i < dictCode.length;i++){
		if(dictCode[i].code == value){
			return dictCode[i].name;
		}
	}
}