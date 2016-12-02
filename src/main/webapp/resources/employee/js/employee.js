$(function() {
	
	showPermissionControl();
	
	var router = '/employee';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'realName',
		title : '姓名'
	}, {
		field : 'userId',
		title : '登录名',
		url: $('#basePath').val() + '/user/list?roleCode=SR201600000000000001',
		keyName: 'userId',
		valueName: 'loginName',
		type: 'select'
	}, {
		field: 'telephone',
		title: '联系电话'
	}, {
		field : 'department',
		title : '审批部门',
		search: true
	}, {
		field: 'content',
		title: '办理事项'
	}, {
    	field : 'systemCode',
		title : '微信服务号',
		key: 'system',
		formatter: Dict.getNameForList('system')
    }, {
		field: 'remark',
		title: '备注'
	}];
	buildList(router, columns, {
		pageRouter: '/employee/employee'
	});
});