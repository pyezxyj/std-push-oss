$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/std/menu';
	
	var fields = [{
		field: 'companyCode',
		type: 'hidden',
		value: getCompanyId(getUserId())
	}, {
		title: '名称',
		field: 'name',
		required: true,
		maxlength: 30,
		readonly: view
	}, {
		title: '位置',
		field: 'parentCode',
		type: 'select',
		url: $('#basePath').val() + '/std/menu/'+(view ? 'detail' : 'list')+'?parentCode=0&companyCode=' + getCompanyId(getUserId()),
		keyName: 'code',
		valueName: 'name',
		emptyValue: '0',
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
		key: 'menu_updown',
		readonly: view
	}, {
		title: '内容格式',
		field: 'contentType',
		required: true,
		type: 'radio',
		readonly: view,
		items: [{
			key: 'list',
			icon: 'zmdi-view-list-alt',
			value: '多条列表'
		}, {
			key: 'ele',
			icon: 'zmdi-apps',
			value: '单条详情'
		}]
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