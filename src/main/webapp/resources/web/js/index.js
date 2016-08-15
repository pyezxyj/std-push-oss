var dictCompany = null;
$(function() {
	//按钮权限判断
    showPermissionControl();
	
	initData();
	
	// 表格初始化
	queryTableData();
	
	//修改
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/web/index_addedit.htm?code="+selRecords[0].code;
	});
	
	// 详情

	
//	$('#detailBtn').click(function() {
//		var selRecords = $('#tableList').bootstrapTable('getSelections')
//		if(selRecords.length <= 0){
//			alert("请选择记录");
//			return;
//		}
//		window.location.href = $("#basePath").val()+"/web/partner_detail.htm?code="+selRecords[0].code;
//	});
	
	
	// 查询事件绑定
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/web/index/page"});
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
		url : $("#basePath").val()+"/web/index/page",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				companyCode :getCompanyId(),
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
			checkbox : true
		}, {
			field : 'banner1',
			title : 'banner1',
			formatter: function(v) {
				return v ? '<img width=100 src="'+v+'"/>' : '';
			}
		}, {
			field : 'banner2',
			title : 'banner2',
			formatter: function(v) {
				return v ? '<img width=100 src="'+v+'"/>' : '';
			}
		}, {
			field : 'banner3',
			title : 'banner3',
			formatter: function(v) {
				return v ? '<img width=100 src="'+v+'"/>' : '';
			}
		}, {
			field : 'fullSizePic',
			title : '全尺寸图',
			formatter: function(v) {
				return v ? '<img width=100 src="'+v+'"/>' : '';
			}
		}, {
			field : 'url',
			title : 'url'
		}, {
			field : 'urlText',
			title : 'text'
		}, {
			field : 'companyCode',
			title : '所属公司',
			formatter : companyFormatter,
			visible: false
		}]
	});
}