$(function(){
	showPermissionControl();
	
	var router = '/forum/post';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'title',
		title : '标题',
		search: true
	}, {
		field : 'status',
		title : '状态',
		formatter: Dict.getNameForList('post_status'),
		search: true,
		key: 'post_status'
    }, {
    	field : 'publisher',
		title : '发帖人'
	}, {
		field: 'publishDatetime',
		title: '发布时间',
		formatter: dateTimeFormat
	}, {
		field : 'remark',
		title : '备注'
	}];
	buildList(router, columns, {
		searchParams: {
			siteCode: getCityId(getUserId())
		}
	});
})

