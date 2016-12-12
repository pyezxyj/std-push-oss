$(function() {
	
	showPermissionControl();
	
	var router = '/general/dict';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'parentKey',
		title : '种类',
		search: true,
		type: 'select',
		url: $('#basePath').val() + '/general/dict/list?type=0',
		keyName: 'dkey',
		valueName: 'dvalue'
	},{
		field : 'dkey',
		title : '字典键'
	},{
    	field : 'dvalue',
		title : '字典值'
    },{
		field: 'remark',
		title: '备注'
	}];
	buildList(router, columns);
});