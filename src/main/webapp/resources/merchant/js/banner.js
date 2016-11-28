$(function() {
	
	showPermissionControl();
	
	var isBranch = getQueryString('b') || '';
	
	var router = '/std/banner';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'name',
		title : '名字',
		search: true
	},{
		field : 'location',
		title : '位置',
		type: 'select',
		key: 'banner_location',
		formatter: Dict.getNameForList('banner_location'),
		search: true
	},{
    	field : 'orderNo',
		title : '顺序'
    },{
		field : 'remark',
		title : '备注'
	}];
	buildList(router, columns, {
		searchParams: {
			companyCode: getCompanyId(getUserId())
		},
		urlParams: {
			b: isBranch
		},
		pageRouter: '/merchant/banner'
	});
});

