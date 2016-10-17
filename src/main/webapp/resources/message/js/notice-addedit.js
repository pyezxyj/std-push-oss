$(function() {
	
	var code = getQueryString('code');
	var isGlobal = !getQueryString('b');
	var router = '/message';
	
	var fields = [{
		title: '标题',
		field: 'title',
		required: true,
		maxlength: 30
	}, {
		title: '内容',
		field: 'content',
		required: true,
		type: 'textarea'
	}, {
		title: '类型',
		field: 'type',
		required: true,
		type: 'select',
		key: 'msg_type',
		value: '1',
		hidden: true
	}, {
		field : 'toLevel',
		title : '作用等级',
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=100000',
		keyName: 'level',
		valueName: 'level',
		defaultOption: 'All',
		defaultValue: '0',
		required: true
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250
	}];
	
	if (!isGlobal) {
		fields.push({
			field: 'companyCode',
			type: 'hidden',
			value: getCityId(getUserId())
		});
	}
	
	buildDetail(router, fields, code);
	
});