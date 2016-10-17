$(function(){
	showPermissionControl();
	
	var router = '/forum/board';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'name',
		title : '名称',
		search: true
	},{
		field : 'kind',
		title : '大类',
		formatter: Dict.getNameForList('plate_kind'),
		search: true,
		key: 'plate_kind'
    },{
    	field : 'status',
		title : '状态',
		formatter: Dict.getNameForList('active_status'),
		search: true,
		key: 'active_status'
    },{
    	field : 'orderNo',
		title : '位置'
	}, {
		field : 'userId',
		title : '版主'
	}];
	buildList(router, columns, {
		searchParams: {
			'siteCode': getCityId(getUserId())
		}
	});
})

