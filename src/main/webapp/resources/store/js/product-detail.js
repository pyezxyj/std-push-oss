$(function() {
	var code = getQueryString('code');
	var router = '/store/product';
	
	var fields = [{
		title: '名称',
		field: 'name',
		readonly: true
	}, {
		title: '广告图',
		field: 'pic',
		readonly: true,
		type: 'img'
	}, {
		title: '定价',
		field: 'price',
		readonly: true,
		amount: true
	}, {
		title: '状态',
		field: 'status',
		required: true,
		type: 'select',
		key: 'prod_status',
		readonly: true
	}, {
		title: '大类',
		field: 'kind',
		required: true,
		type: 'select',
		key: 'prod_kind',
		readonly: true
	}, {
		title: '简介',
		field: 'description',
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