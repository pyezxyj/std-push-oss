$(function() {
	
	var code = getQueryString('code');
	var router = '/user/level';
	
	var fields = [{
		title: '名称',
		field: 'level',
		required: true,
		maxlength: 4
	}, {
		title: '积分上',
		field: 'amountMax',
		required: true,
		number: true,
		maxlength: 15
	}, {
		title: '积分下',
		field: 'amountMin',
		required: true,
		number: true,
		maxlength: 15
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250
	}];
	
	buildDetail(router, fields, code);
	
});