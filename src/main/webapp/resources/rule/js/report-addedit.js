$(function() {
	
	var code = getQueryString('code');
	var router = '/rule/report';
	
	var fields = [{
		title: '次数',
		field: 'value',
		required: true,
		maxlength: 30,
		'Z+': true
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