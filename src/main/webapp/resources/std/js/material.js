$(function() {
	
	showPermissionControl();
	
	var router = '/std/material';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'title',
		title : '标题',
		search: true
	},{
		field : 'kind',
		title : '种类',
		type: 'select',
		formatter: Dict.getNameForList('material_kind'),
		key: 'material_kind',
		search: true
	},{
    	field: 'menuCode',
    	title: '隶属',
    	type: 'select',
    	url: $('#basePath').val() + '/std/menu/list?companyCode=' + getCompanyId(getUserId()),
    	search: true,
    	keyName: 'code',
    	valueName: 'name'
    }, {
    	field: 'createTime',
    	title: '上传时间',
    	formatter: dateTimeFormat
    }, {
		field : 'remark',
		title : '备注'
	}];
	
	var options = {
			searchParams: {
				companyCode: getCompanyId(getUserId())
			}
	};
	
	if (getCompanyId(getUserId()) == 0) {
		options.searchParams.menuCode = 0;
	}
	
	buildList(router, columns, options);
});

