$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/std/banner';
	
	var fields = [{
		field: 'companyCode',
		type: 'hidden',
		value: getCompanyId(getUserId())
	}, {
		title: '名字',
		field: 'name',
		required: true,
		maxlength: 30,
		readonly: view
	}, {
		title: '位置',
		field: 'parentCode',
		required: true,
		type: 'select',
		url: $('#basePath').val() + '/std/menu/'+(view ? 'detail' : 'list')+'?parentCode=0&companyCode=' + getCompanyId(getUserId()),
		keyName: 'code',
		valueName: 'name',
		readonly: view
	}, {
		title: '顺序',
		field: 'orderNo',
		required: true,
		maxlength: 10,
		number: true,
		readonly: view
	}, {
		title: '状态',
		field: 'status',
		required: true,
		type: 'select',
		key: 'active_status',
		readonly: view
	},  {
		title: 'url',
		field: 'url',
		required: true,
		maxlength: 30,
		readonly: view
	}, {
		title: '图片',
		field: 'pic',
		required: true,
		type: 'img',
		readonly: view
	}, {
		title: '上传人',
		field: 'updater',
		readonly: view,
		hidden: !view
	}, {
		title: '上传时间',
		field: 'updateDatetime',
		readonly: view,
		formatter: dateTimeFormat,
		hidden: !view
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250,
		readonly: view
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