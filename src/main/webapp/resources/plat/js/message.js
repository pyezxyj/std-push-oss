$(function() {
	initData();
	var companyCode = '';
	var url = $("#basePath").val() + "/plat/company/detailuserid";
	   doGetAjaxIsAsync(url, {
		   userid: getUserId()
	   }, false, function(res) {
		   companyCode = res.data.code;
	   });
	
	//按钮权限判断
	showPermissionControl();
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/plat/search/page"});
	});
	
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/plat/search/page",
		height : $(window).height() - 180,
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				type : 1,
				companyCode: companyCode,
				person :$("#person").val(),
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
		}, {
			field : 'person',
			title : '姓名',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'content1',
			title : '留言内容',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'createDatatime',
			title : '创建时间',
			align : 'left',
			valign : 'middle',
			sortable : false,
			formatter : dateFormatter
		}]
	});
	
	
	// 详情菜单绑定事件
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/plat/message_detail.htm?code="+selRecords[0].code;
	});
	
	
	
	
});




function initData(){
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}

function doSuccessBackCompany(res){
	dictCompany = res.data;
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