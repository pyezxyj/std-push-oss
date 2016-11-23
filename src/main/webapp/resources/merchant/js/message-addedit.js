$(function() {
	var code = getQueryString('code');
	var view = getQueryString('v');
	var router = '/merchant/message';
	
	var fields = [{
		title: '标题',
		field: 'title',
		maxlength: 60,
		required: true,
		readonly: !!view,
	}, {
		title: '类型',
		field: 'type',
		type: 'select',
		data: {'1':'公告'},
		required: true,
		readonly: !!view,
	}, {
		title: '内容',
		field: 'content',
		type: 'textarea',
		required: true,
		readonly: !!view,
	},{
		title: '备注',
		field: 'remark',
		maxlength: 30,
		readonly: !!view,
	},{
		title: '',
		field: 'companyCode',
		type: 'hidden',
		value: '1',
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