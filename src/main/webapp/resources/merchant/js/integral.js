$(function(){
	showPermissionControl();
	var userId = getQueryString('userId');
	var router = '/merchant/integral';
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'bizType',
		title : '业务类型',
		type: 'select',
		formatter: Dict.getNameForList('biz_type'),
		type: 'biz_type',
		search: true
	}, {
		field: 'refNo',
		title: '关联单号'
	}, {
		field: 'transAmount',
		title: '变动金额',
		formatter: moneyFormat
	}, {
		field: 'preAmount',
		title: '变动前金额',
		formatter: moneyFormat
    }, {
    	field: 'postAmount',
		title: '变动后金额',
		formatter: moneyFormat
    }, {
    	field : 'createDatetime',
    	title : '产生时间',
    	formatter: dateTimeFormat
    }, {
    	field: 'remark',
    	title: '备注'
    }];
	buildList(router, columns, {
		searchParams: {
			userId: userId
		}
	});
	$('#freeze-upBtn').hide();
	$('#freeze-downBtn').hide();
	$('#integralBtn').hide();
})