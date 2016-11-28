$(function() {
	var code = getQueryString('code');
	var group = !!getQueryString('g');
	var router = '/message/wx';
	
	var fields = [{
		field: 'fromSystemCode',
		type: 'hidden',
		value: getSystemId()
	}, {
		field: 'toSystemCode',
		type: 'hidden',
		value: getSystemId()
	}, {
		title: '办理进度',
		field: 'status',
		type: 'select',
		key: 'push_status',
		required: true
	}, {
		title: '接收者',
		field: 'customerCode',
		type: 'select',
		url: $('#basePath').val() + '/customer/list?systemCode=' + getSystemId(),
		required: true,
		keyName: 'mobile',
		valueName: 'name',
		onChange: function(v) {
			if (v) {
				$('#toMobile').val(v);
			} else {
				$('#toMobile').val('');
			}
		},
		hidden: group
	}, {
		title: '接收者手机号',
		field: 'toMobile',
		required: true,
		mobile: true,
		hidden: group
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '保存',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					var url = $("#basePath").val()+ "/message/wx/send";
					ajaxPost(url, data).then(function(res) {
						if (res.success) {
							alert("操作成功");
							goBack();
						}
					});
				}
			}
		}, {
			title: '返回',
			handler: function() {
				goBack();
			}
		}]
	});

});