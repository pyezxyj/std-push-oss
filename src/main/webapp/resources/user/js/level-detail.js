$(function() {
	var code = getQueryString('code');
	var router = '/user/level';
	
	var fields = [{
		title: '名称',
		field: 'level',
		readonly: true
	}, {
		title: '积分上',
		field: 'amountMax',
		readonly: true
	}, {
		title: '积分下',
		field: 'amountMin',
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