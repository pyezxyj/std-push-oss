$(function() {
	
	showPermissionControl();
	
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
		formatter: Dict.getNameForList('push_type'),
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
	buildList(router, columns);
	$('#singleBtn').click(function() {
		window.location.href = $("#basePath").val()+ "/message/wx_send.htm?-=-";
	});
	$('#groupBtn').click(function() {
		window.location.href = $("#basePath").val()+ "/message/wx_send.htm?-=-&g=1";
	});
});