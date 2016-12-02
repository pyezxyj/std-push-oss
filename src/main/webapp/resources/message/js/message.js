$(function() {
	
	showPermissionControl();
	
	var router = '/message';
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
    	field : 'channelType',
		title : '渠道大类',
		key: 'channel_type',
		formatter: Dict.getNameForList('channel_type'),
		search: true
    }, {
    	field : 'pushType',
		title : '渠道小类',
		key: 'push_type',
		formatter: Dict.getNameForList('push_type'),
		search: true
    }, {
    	field : 'smsType',
		title : '类别',
		key: 'sms_type',
		formatter: Dict.getNameForList('sms_type'),
		search: true
    }, {
    	field : 'toSystemCode',
		title : '接收单位',
		key: 'system',
		formatter: Dict.getNameForList('system'),
		search: true
    }, {
		field : 'toMobile',
		title : '接收者手机号'
	},{
		field: 'status',
		title: '状态',
		key: 'push_status',
		formatter: Dict.getNameForList('push_status'),
		search: true
	}];
	buildList(router, columns, {
		pageRouter: '/message/message'
	});
	$('#send1Btn').click(function() {
		window.location.href = $("#basePath").val()+ "/message/message_addedit.htm?t=1";
	});
	$('#send2Btn').click(function() {
		window.location.href = $("#basePath").val()+ "/message/message_addedit.htm?t=2";
	});
});