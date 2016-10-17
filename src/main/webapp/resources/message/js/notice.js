$(function(){
	showPermissionControl();
	
	var isGlobal = !getQueryString('b');
	
	var router = '/message';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'title',
		title : '标题',
		search: true
	},{
    	field : 'toLevel',
		title : '作用等级',
		search: true,
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=100000',
		keyName: 'level',
		valueName: 'level',
		defaultOption: 'All'
    }, {
    	field : 'status',
		title : '状态',
		formatter: Dict.getNameForList('msg_status'),
		search: true,
		key: 'msg_status'
	}, {
		field : 'updater',
		title : '最近修改人'
	}, {
		field : 'updateDatetime',
		title : '最近修改时间',
		formatter: dateTimeFormat
	}, {
		field : 'remark',
		title : '备注'
	}];
	var options = {pageRouter: '/message/notice'};
	if (!isGlobal) {
		options.searchParams = {
			'companyCode': getCityId(getUserId()),
			type: 1
		};
		options.urlParams = {
			'b': '1'
		}
	}
	buildList(router, columns, options);
	
	$('#publishBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		
		if(!confirm("确认发布该公告？")){
    		return false;
    	}
    	var url = $("#basePath").val()+ router + '/publish';
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
})

