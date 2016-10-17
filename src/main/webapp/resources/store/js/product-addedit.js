$(function() {
	
	var code = getQueryString('code');
	var router = '/store/product';
	
	var fields = [{
		field: 'siteCode',
		type: 'hidden',
		value: getCityId(getUserId())
	}, {
		title: '名称',
		field: 'name',
		required: true,
		maxlength: 30
	}, {
		title: '广告图',
		field: 'pic',
		required: true,
		type: 'img'
	}, {
		title: '定价',
		field: 'price',
		required: true,
		amount: true
	}, {
		title: '状态',
		field: 'status',
		required: true,
		type: 'select',
		key: 'prod_status',
		defaultValue: '0',
		hidden: true
	}, {
		title: '大类',
		field: 'kind',
		required: true,
		type: 'select',
		key: 'prod_kind'
	}, {
		title: '简介',
		field: 'description',
		required: true,
		type: 'textarea'
	}];
	
	buildDetail(router, fields, code);
	
});