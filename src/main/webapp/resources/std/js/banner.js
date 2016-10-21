$(function() {
	
	showPermissionControl();
	
	var router = '/std/banner';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'name',
		title : '名字'
	},{
		field : 'parentCode',
		title : '位置',
		type: 'select',
		url: $('#basePath').val() + '/std/menu/list?parentCode=0&companyCode=' + getCompanyId(getUserId()),
		keyName: 'code',
		valueName: 'name',
		search: true
	},{
    	field : 'orderNo',
		title : '顺序'
    },{
    	field: 'status',
    	title: '状态',
    	type: 'select',
    	formatter: Dict.getNameForList('active_status'),
    	search: true,
    	key: 'active_status'
    }, {
		field : 'remark',
		title : '备注'
	}];
	buildList(router, columns, {
		searchParams: {
			companyCode: getCompanyId(getUserId())
		}
	});
});

