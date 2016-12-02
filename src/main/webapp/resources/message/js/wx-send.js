$(function() {
	var code = getQueryString('code');
	var group = !!getQueryString('g');
	var router = '/message/wx';
	var tpl = '', tpldata = {};
	var emp = getEmployee() || {};
	
	ajaxGet($('#basePath').val() + '/message/wx/tpl', {
		systemCode: getSystemId()
	}).then(function(res) {
		tpl = res.data.content;
		tpldata.title = res.data.title;
		$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
	});
	tpldata.first = emp.first;
	tpldata.keyword1 = emp.content;
	tpldata.keyword2 = emp.department;
	tpldata.keyword5 = dateTimeFormat(new Date());
	tpldata.remark = emp.remark;
	
	var fields = [{
		field: 'smsType',
		hidden: true,
		value: '1'
	}, {
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
		data: {'正在办理中': '正在办理中', '办理完成': '办理完成'},
		required: true,
		onChange: function(v) {
			tpldata.keyword3 = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		}
	}, {
		title: '接收者姓名',
		field: 'realName',
		required: true,
		hidden: group,
		onBlur: function(v) {
			tpldata.keyword4 = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		}
	}, {
		title: '接收者手机号',
		field: 'toMobile',
		type: 'select',
		url: $('#basePath').val() + '/customer/list?systemCode=' + getSystemId(),
		required: true,
		keyName: 'mobile',
		valueName: 'mobile',
		onChange: function(v, r) {
			//tpldata.keyword4 = r.name;
			if (v) {
			} else {
				tpldata.keyword4 = '';
			}
		},
		hidden: group
	}, {
		title: '预览',
		field: 'content',
		readonly: true,
		value: ''
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '保存',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					data.smsContent = tpldata;
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
	group && (tpldata.keyword4 = '');
});