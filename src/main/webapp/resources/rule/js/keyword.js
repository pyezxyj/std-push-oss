$(function(){
	showPermissionControl();
	
	var router = '/rule/keyword';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'word',
		title : '关键字',
		search: true
	},{
		field : 'weight',
		title : '权重',
		search: true
    },{
    	field : 'level',
		title : '作用等级',
		search: true,
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=100000',
		keyName: 'level',
		valueName: 'level',
		defaultOption: 'All'
    },{
    	field : 'reaction',
		title : '反应',
		formatter: Dict.getNameForList('kw_reaction'),
		search: true,
		key: 'kw_reaction'
	}, {
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

