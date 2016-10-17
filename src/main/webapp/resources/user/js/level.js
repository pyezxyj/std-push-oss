$(function(){
	showPermissionControl();
	
	var router = '/user/level';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'level',
		title : '名称',
		search: true
	},{
		field : 'amountMin',
		title : '积分下'
    },{
    	field : 'amountMax',
		title : '积分上'
    },{
		field : 'updater',
		title : '最近修改人'
	}, {
		field : 'updateDatetime',
		title : '最近修改时间',
		formatter: dateTimeFormat
	}, {
		field : 'remark',
		title : '备注'
	}];
	buildList(router, columns);
})

