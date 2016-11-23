$(function() {
	var code = getQueryString('code');
	var view = getQueryString('v');
	var companyCode = getCompanyId(getUserId());
	var router = '/merchant/input';
	
	var fields = [{
		field: 'category',
		title: '大类',
		url: $('#basePath').val() + '/merchant/genre/list?parentCode=0&companyCode=' + companyCode,
		keyName: 'code',
		valueName: 'name',
		type: 'select',
		readonly: !!view
	}, {
		field: 'type',
		title: '小类',
		url: $('#basePath').val() + '/merchant/genre/list?companyCode=' + companyCode,
		keyName: 'code',
		valueName: 'name',
		type: 'select',
		readonly: !!view,
		required: true
	}, {
		title: '商品名称',
		field: 'name',
		maxlength: 30,
		readonly: !!view,
		required: true
	}, {
		title: '广告语',
		field: 'advTitle',
		maxlength: 250,
		readonly: !!view,
		required: true
	}, {
		title: '广告图',
		field: 'advPic',
		type : 'img',
		readonly: !!view,
		required: true
	}, {
		title: '展示图1',
		field: 'pic1',
		type : 'img',
		readonly: !!view,
		required: true
	}, {
		title: '展示图2',
		field: 'pic2',
		type : 'img',
		readonly: !!view,
		required: true
	}, {
		title: '展示图3',
		field: 'pic3',
		type : 'img',
		readonly: !!view,
		required: true
	}, {
		title: '展示图4',
		field: 'pic4',
		type : 'img',
		readonly: !!view,
		required: true
	}, {
		title: '图文详情',
		field: 'description',
		type: 'textarea',
		readonly: !!view,
		required: true
	}, {
		title: '进货价',
		field: 'costPrice',
		readonly: !!view,
		amount: true,
		required: true
	}, {
		title: '库存数',
		field: 'quantity',
		readonly: !!view,
		maxlength: 11,
		required: true
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 255,
		readonly: !!view
	}, {
		field: 'companyCode',
		type: 'hidden',
		value: companyCode
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