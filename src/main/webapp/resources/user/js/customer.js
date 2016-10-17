$(function(){
	showPermissionControl();
	
	var router = '/customer';
	
	var isGlobal = !getQueryString('b');
	
	if (!isGlobal) {
		router = '/customer/branch'
	}
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'loginName',
		title : '用户名',
		search: true
	},{
		field : 'mobile',
		title : '手机号'
    },{
    	field : 'status',
		title : '状态',
		type: 'select',
		key: 'user_status',
		formatter: Dict.getNameForList('user_status')
    },{
		field : 'level',
		title : '用户组',
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=1000000',
		keyName: 'level',
		valueName: 'level',
		search: true
	},{
		field : '',
		title : '发帖数'
	},{
		field : '',
		title : '积分'
	},{
		field : 'remark',
		title : '备注'
	}];
	
	var options = {pageRouter: 'user/customer'};
	if (!isGlobal) {
		options.searchParams = {
			'ossUserId': getUserId()
		};
	}
	buildList(router, columns, options);
})

