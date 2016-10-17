$(function() {
	//按钮权限判断
	showPermissionControl();
	
	var global = !getQueryString('b') ? 1 : 0;
	var cityId;
	if (!global) {
		cityId = getCityId(getUserId()) || '0';
	}
	
	$('#status').renderDropdown(Dict.getName('active_status'));
	$('#type').renderDropdown(Dict.getName('view_type'));
	
	// 列表查询
	function queryTableData(){
		var columns = [{
			field : '',
			title : '',
			checkbox : true
		}, {
			field : 'name',
			title : '名字'
		}, {
			field : 'type',
			title : '类型',
			formatter : Dict.getNameForList('view_type')
		}, {
			field : 'status',
			title : '状态',
			formatter : Dict.getNameForList('active_status')
		}];
		
		// 绑定列表
		$('#tableList').bootstrapTable({
			method : "get",
			url : $("#basePath").val()+"/view/page",
			 
			striped : true,
			clickToSelect : true,
			singleSelect : true,
			queryParams : function(params) {
				return {
					name : $("#name").val(),
					type : $("#type").val(),
					status : $("#status").val(),
					siteCode: cityId,
					isGlobal: global,
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
			columns : columns
		});
	}

	// 表格初始化
	queryTableData();
	
	// 查询
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $("#basePath").val()+"/view/page"});
	});
	
	$('#addBtn').click(function() {
		location.href = $("#basePath").val()+"/view/menu_addedit.htm?b=" + (getQueryString('b') || 0);
	});
	
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/view/menu_addedit.htm?code="+selRecords[0].code + '&b=' + (getQueryString('b') || 0);
	});
	
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		location.href = $("#basePath").val()+"/view/menu_detail.htm?code=" + selRecords[0].code;
	});
});

