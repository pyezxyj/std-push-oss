$(function() {
	var code = getQueryString('code');
	var router = '/rule/keyword';
	
	var fields = [{
		title: '关键字',
		field: 'word',
		readonly: true
	}, {
		title: '权重',
		field: 'weight',
		readonly: true
	}, {
		field : 'level',
		title : '作用等级',
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=100000',
		keyName: 'level',
		valueName: 'level',
		defaultOption: 'All',
		readonly: true
	}, {
		title: '反应',
		field: 'reaction',
		type: 'select',
		key: 'kw_reaction',
		readonly: true
	}, {
		title: '备注',
		field: 'remark',
		readonly: true
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '返回',
			handler: function() {
				goBack();
			}
		}]
	});
});