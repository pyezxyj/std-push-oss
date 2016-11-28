$(function(){
	showPermissionControl();
	var router = '/merchant/position';
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field: 'pic',
		title: '图片',
		formatter: function(v) {
			return v ? '<img src="'+v+'" style="width: 60px;"/>' : '-';
		}
	}, {
		field : 'name',
		title : '位置名称',
		search: true
	}, {
		field: 'orderNo',
		title: '次序',
		sortable: true
	}];
	buildList(router, columns, {
		sortName: 'orderNo',
		sortOrder: 'asc',
		searchParams: {
			companyCode: getCompanyId(getUserId())
		}
	});
})