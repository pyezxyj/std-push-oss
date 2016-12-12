$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/message/wx';
	var tpl = '', tpldata = {};
	
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
		title: '接收者手机号',
		field: 'toMobile',
		required: true,
		mobile: true,
		readonly: view
	}, {
		title: '状态',
		field: 'status',
		type: 'select',
		key: 'push_status',
		required: true,
		readonly: view
	}, {
		title: '预览',
		field: 'smsContent',
		readonly: true,
		hidden: true,
		value: function(data) {
			ajaxGet($('#basePath').val() + '/message/wx/tpl', {
				systemCode: getSystemId()
			}).then(function(res) {
				$('#smsContent').html(data.smsContent.replace(/\n/g,"<br/>"));
				$('#smsContent').parent().show();
			});
		}
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