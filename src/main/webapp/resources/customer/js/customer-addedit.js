$(function() {
	var view = !!getQueryString('v');
	var router = '/customer';
	
	var fields = [{
    	field : 'systemCode',
		title : '所属系统',
		key: 'system',
		type: 'select',
		required: true,
		readonly: view
    }, {
		title: '名称',
		field: 'name',
		maxlength: 30,
		required: true,
		readonly: view
	}, {
		title: '手机号',
		field: 'mobile',
		maxlength: 30,
		required: true,
		readonly: view,
		mobile: true
	}, {
		title: '用户等级',
		field: 'level',
		maxlength: 30,
		readonly: view
	}, {
		title: '微信id',
		field: 'wechatId',
		maxlength: 60,
		readonly: view
	}, {
		title: '极光id',
		field: 'jpushId',
		maxlength: 60,
		readonly: view
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250,
		readonly: view
	}];
	
	buildDetail(router, fields, {
		mobile: getQueryString('mobile'),
		systemCode: getQueryString('systemCode')
	});
});