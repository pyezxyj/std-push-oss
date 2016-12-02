$(function() {
	
	showPermissionControl();
	
	var router = '/channel';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'systemCode',
		title : '系统名称',
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
    	field : 'status',
		title : '状态',
		key: 'active',
		formatter: Dict.getNameForList('active'),
		search: true
	}, {
		field: 'remark',
		title: '备注'
	}];
	buildList(router, columns, {
		pageRouter: '/channel/channel'
	});
});