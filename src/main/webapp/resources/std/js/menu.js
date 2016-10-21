$(function() {
	
	showPermissionControl();
	
	var router = '/std/menu';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'name',
		title : '名称',
		search: true
	},{
		field : 'parentCode',
		title : '位置',
		type: 'select',
		url: $('#basePath').val() + '/std/menu/list?parentCode=0&companyCode=' + getCompanyId(getUserId()),
		keyName: 'code',
		valueName: 'name',
		search: true
    }, {
		field : 'orderNo',
		title : '顺序'
	},{
    	field : 'contentType',
		title : '内容格式',
		formatter: function(v) {
			if (v == 'list') {
				return '<i class="zmdi zmdi-view-list-alt zmdi-hc-fw"></i>多条列表';
			} else if (v == 'ele') {
				return '<i class="zmdi zmdi-apps zmdi-hc-fw"></i>单条详情';
			}
		}
    },{
    	field: 'status',
    	title: '状态',
    	type: 'select',
    	formatter: Dict.getNameForList('menu_updown'),
    	key: 'menu_updown'
    }];
	buildList(router, columns, {
		searchParams: {
			companyCode: getCompanyId(getUserId())
		}
	});
	$('#updownBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		
    	var url = $("#basePath").val()+ router + '/updown';
    	var data = {code:selRecords[0].code};
		ajaxPost(url, data).then(function(res) {
			if (res.success) {
				alert('操作成功');
				$('#tableList').bootstrapTable('refresh',{url: $('#tableList').bootstrapTable('getOptions').url});
			} else {
				alert(res.msg);
			}
		});
	});
});

