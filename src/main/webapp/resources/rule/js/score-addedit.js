$(function() {
	
	var code = getQueryString('code');
	var router = '/rule/score';
	
	var fields = [{
		title: '项目',
		field: 'type',
		required: true,
		type: 'select',
		key: 'rule_type'
	}, {
		title: '权重',
		field: 'value',
		required: true,
		maxlength: 30,
		number: true
	}, {
		field : 'level',
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
	
	buildDetail(router, fields, code);
	
});