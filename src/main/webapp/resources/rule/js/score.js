$(function(){
	showPermissionControl();
	
	var router = '/rule/score';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		title: '产生项',
		field: 'type',
		type: 'select',
		formatter: Dict.getNameForList('rule_type'),
		key: 'rule_type',
		search: true
	}, {
		field : 'value',
		title : '权重'
	}, {
    	field : 'level',
		title : '作用等级',
		search: true,
		type: 'select',
		url: $('#basePath').val() + '/user/level/page?start=1&limit=100000',
		keyName: 'level',
		valueName: 'level',
		defaultOption: 'All'
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

