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
		field : 'location',
		title : '位置',
		type: 'select',
		formatter: Dict.getNameForList('banner_location'),
		key: 'banner_location',
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
    	field: 'updateDatetime',
    	title: '上传时间',
    	formatter: dateTimeFormat
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

