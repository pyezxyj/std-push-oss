$(function(){
	showPermissionControl();
	
	var router = '/merchant/message';
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : '',
		title : '标题',
		search: true,
	}, {
		field: '',
		title: '类型',
		search: true,
		type : 'select',
	}, {
		field: '',
		title: '状态',
		type: 'select',
	}, {
		field : '',
		title : '发布人',
    }, {
    	field : '',
    	title : '发布时间',
    	formatter: dateTimeFormat,
    }, {
    	field: 'remark',
    	title: '备注'
    }];
	buildList(router, columns);
})