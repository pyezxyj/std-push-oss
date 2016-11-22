$(function(){
	showPermissionControl();
	
	var router = '/merchant/input';
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : '',
		title : '商品名称',
	}, {
		field: '',
		title: '小类',
		search: true,
		type : 'select',
	}, {
		field: '',
		title: '大类',
		search: true,
		type: 'select',
	}, {
		field : '',
		title : '库存',
    }, {
    	field : '',
    	title : '进货价',
    }, {
    	field: 'remark',
    	title: '备注'
    }];
	buildList(router, columns);
})