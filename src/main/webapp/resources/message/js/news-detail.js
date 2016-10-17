$(function() {
	var code = getQueryString('code');
	var router = '/message';
	
	var fields = [{
		title: '标题',
		field: 'title',
		readonly: true
	}, {
		title: '内容',
		field: 'content',
		readonly: true
	}, {
		field : 'toLevel',
		title : '作用等级',
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=100000',
		keyName: 'level',
		valueName: 'level',
		defaultOption: 'All',
		readonly: true
	}, {
		title: '备注',
		field: 'remark',
		readonly: true
	}];
	
	buildDetail(router, fields, code, {
		pageRouter: '/message/news',
		buttons: [{
			title: '返回',
			handler: function() {
				goBack();
			}
		}]
	});
});