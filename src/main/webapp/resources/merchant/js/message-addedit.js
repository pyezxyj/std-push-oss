$(function() {
	var code = getQueryString('code');
	var view = getQueryString('v');
	var router = '/merchant/message';
	
	var fields = [{
		title: '类型',
		field: '',
		type: 'select',
		readonly: !!view,
		required: true,
	}, {
		title: '标题',
		field: '',
		maxlength: 30,
		readonly: !!view,
		required: true,
	}, {
		title: '内容',
		field: '',
		maxlength: 255,
		readonly: !!view,
		required: true,
	}, {
		title: '备注',
		field: 'remark',
		type : 'textarea',
		readonly: !!view,
	}];
	
	var options = {};
	if (view) {
		options.buttons = [{
			'title': '返回',
			handler: function() {
				goBack();
			}
		}];
	}
	buildDetail(router, fields, code, options);
});