$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/channel';
	
	var fields = [{
    	field : 'systemCode',
		title : '系统名称',
		key: 'system',
		type: 'select',
		required: true,
		readonly: view
    }, {
    	field : 'channelType',
		title : '渠道大类',
		key: 'channel_type',
		type: 'select',
		required: true,
		readonly: view
	}, {
		field : 'pushType',
		title : '渠道小类',
		key: 'push_type',
		type: 'select',
		required: true,
		readonly: view
	}, {
		field : 'status',
		title : '状态',
		key: 'active',
		type: 'select',
		required: true,
		readonly: view
	}, {
		title: '商户号',
		field: 'pushSystem',
		maxlength: 60,
		readonly: view
	}, {
		title: '密钥1',
		field: 'privateKey1',
		maxlength: 250,
		readonly: view
	}, {
		title: '密钥2',
		field: 'privateKey2',
		maxlength: 250,
		readonly: view
	}, {
		title: '密钥3',
		field: 'privateKey3',
		maxlength: 1000,
		readonly: view
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250,
		readonly: view
	}];
	
	buildDetail(router, fields, code);
	view && $('#subBtn').remove();
});