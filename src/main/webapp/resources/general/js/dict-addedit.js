$(function() {
	var code = getQueryString('code');
	var router = '/general/dict';
	
	var fields = [{
		title: '类型',
		field: 'type',
		hidden: true,
		defaultValue: '0'
	}, {
		title: '种类',
		field: 'parentKey',
		required: true,
		type: 'select',
		url: $('#basePath').val() + '/general/dict/list?type=0',
		keyName: 'dkey',
		valueName: 'dvalue',
		readonly: !!code,
		defaultOption: '选此创建种类'
	}, {
		title: '字典键',
		field: 'dkey',
		required: true,
		maxlength: 15,
		readonly: !!code
	}, {
		title: '字典值',
		field: 'dvalue',
		required: true,
		maxlength: 15
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250
	}];
	
	buildDetail(router, fields, code);
	
	$('#parentKey').on('change', function() {
		$('#type').val(this.value == 0 ? '0' : '1');
	});
});