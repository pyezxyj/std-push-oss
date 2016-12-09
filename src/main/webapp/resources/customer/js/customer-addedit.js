$(function() {
	var view = !!getQueryString('v');
	var systemCode = getSystemId();
	var router = '/customer';
	
	var fields = [{
    	field : 'systemCode',
		title : '所属系统',
		key: 'system',
		type: 'select',
		required: true,
		defaultValue: systemCode,
		readonly: view,
		hidden: !!systemCode
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
		mobile: true,
		afterSet: function(v) {
			if (v && !view) {
				$('#mobile').parent().hide();
			}
		}
	}, {
		title: '用户分组',
		field: 'level',
		key: 'user_kind',
		type: 'select',
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
	
	if (view) {
		$('#subBtn').remove();
	}
});