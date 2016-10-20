$(function() {
	
	var code = getQueryString('code');
	var router = '/user';
	
	var fields = [{
		title: '用户名',
		field: 'loginName',
		required: true,
		maxlength: 30
	}, {
		title: '角色',
		field: 'roleCode',
		required: true,
		type: 'select',
		url: $('#basePath').val() + '/role/list',
		keyName: 'code',
		valueName: 'name'
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250
	}];
	
	buildDetail(router, fields, code, {
		pageRouter: '/security/user'
	});
	
});