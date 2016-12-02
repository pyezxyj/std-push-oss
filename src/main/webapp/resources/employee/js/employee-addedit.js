$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/employee';
	
	var fields = [{
    	field : 'systemCode',
		title : '微信服务号',
		key: 'system',
		type: 'select',
		required: true,
		readonly: view
    }, {
		title: '姓名',
		field: 'realName',
		maxlength: 30,
		required: true,
		readonly: view
	}, {
		title: '登录名',
		field: 'loginName',
		maxlength: 30,
		required: true,
		readonly: view,
		hidden: code
	}, {
		title: '登录名',
		field: 'userId',
		url: view ? $('#basePath').val() + '/user/detail' : '',
		type: 'select',
		keyName: 'userId',
		valueName: 'loginName',
		readonly: view,
		hidden: !view
	}, {
		title: '联系电话',
		field: 'telephone',
		maxlength: 30,
		required: true,
		readonly: view,
		tm: true
	}, {
		field: 'first',
		title: '问候语',
		maxlength: 100,
		required: true,
		readonly: view
	}, {
		title: '审批部门',
		field: 'department',
		maxlength: 100,
		required: true,
		readonly: view
	}, {
		title: '办理事项',
		field: 'content',
		maxlength: 100,
		required: true,
		readonly: view
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250,
		readonly: view,
		required: true
	}];
	var options = {};
	if (view) {
		options.buttons = [{
			title: '返回',
			handler: function() {
				goBack();
			}
		}];
	}
	buildDetail(router, fields, code, options);
});