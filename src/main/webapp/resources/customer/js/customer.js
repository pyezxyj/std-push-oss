$(function() {
	
	showPermissionControl();
	
	var router = '/customer';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'name',
		title : '名称'
	}, {
    	field : 'systemCode',
		title : '所属系统',
		key: 'system',
		formatter: Dict.getNameForList('system'),
		search: true
    }, {
		field : 'mobile',
		title : '手机号'
	}, {
		field: 'level',
		title: '用户等级'
	}, {
		field: 'wechatId',
		title: '微信id'
	}, {
		field: 'jpushId',
		title: '极光id'
	}, {
		field: 'remark',
		title: '备注'
	}];
	buildList(router, columns, {
		pageRouter: '/customer/customer',
		uid: ['mobile', 'systemCode']
	});
});