$(function() {
	initData();
	
	//按钮权限判断
	showPermissionControl();
	
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/plat/search/page",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				type: 2,
				organization : $("#organization").val(),
				person : $("#person").val(),
				contact : $("#contact").val(),
				dateStart :$("#dateStart").val(),
				dateEnd :$("#dateEnd").val(),
				companyCode: getCompanyId(),
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
			field : 'organization',
			title : '单位名称',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'content1',
			title : '合作内容',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'person',
			title : '联系人',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'contact',
			title : '联系方式',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'organizationDesc',
			title : '公司简介',
			align : 'left',
			valign : 'middle',
			sortable : false
		}, {
			field : 'createDatatime',
			title : '提交时间',
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
		window.location.href = $("#basePath").val()+"/plat/cooprate_detail.htm?code="+selRecords[0].code;
	});
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/plat/search/page"});
	});
	
	
	
});


function statusFormatter(value, row) {
	for(var i = 0;i < statusData.length;i++){
		if(statusData[i].key == value){
			return statusData[i].value;
		}
	}
}

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

