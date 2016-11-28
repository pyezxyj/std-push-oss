$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/message/wx';
	
	var fields = [{
    	field : 'fromSystemCode',
		title : '发送单位',
		key: 'system',
		type: 'select',
		readonly: true
    }, {
    	field : 'pushType',
		title : '发送渠道',
		key: 'push_type',
		type: 'select',
		readonly: true
    }, {
		title: '办理进度',
		field: 'status',
		type: 'select',
		key: 'push_status',
		required: true,
		readonly: view
	}, {
		title: '接收者',
		field: 'customerCode',
		type: 'select',
		url: $('#basePath').val() + '/customer/list?systemCode=' + getSystemId(),
		required: true,
		keyName: 'mobile',
		valueName: 'name',
		readonly: view,
		hidden: true,
		onChange: function(v) {
			if (v) {
				$('#toMobile').val(v);
			} else {
				$('#toMobile').val('');
			}
		}
	}, {
		title: '接收者手机号',
		field: 'toMobile',
		required: true,
		mobile: true,
		readonly: view
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			'title': '返回',
			handler: function() {
				goBack();
			}
		}]
	});

});