$(function() {
	
	showPermissionControl();
	var toSystemCode = "";
	ajaxGet($('#basePath').val() + '/role/sysCode', {}, false, true).then(function(res) {
		if (res.success) {
			toSystemCode = res.data[0].systemCode;
		}
	});
	var router = '/message/wx';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
    	field : 'fromSystemCode',
		title : '发送单位',
		key: 'system',
		formatter: Dict.getNameForList('system'),
		search: true
    }, {
    	field : 'pushType',
		title : '发送渠道',
		key: 'push_type',
		formatter: Dict.getNameForList('push_type')
    }, {
    	field : 'toSystemCode',
		title : '接收单位',
		key: 'system',
		formatter: Dict.getNameForList('system'),
		search: true
    }, {
		field : 'toMobile',
		title : '接收者'
	},{
		field: 'status',
		title: '状态',
		key: 'push_status',
		formatter: Dict.getNameForList('push_status'),
		search: true
	}];
	buildList(router, columns, {
		searchParams: {
			pushType: '31',
			toSystemCode: toSystemCode || ""
		}
	});
	$('#singleBtn').click(function() {
		window.location.href = $("#basePath").val()+ "/message/wx_send.htm?-=-";
	});
	$('#groupBtn').click(function() {
		window.location.href = $("#basePath").val()+ "/message/wx_sends.htm?-=-&g=1";
	});
});