$(function(){
	showPermissionControl();
	var companyCode = getCompanyId(getUserId());
	var router = '/merchant/order';
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'code',
		title : '订单编号',
		search : true
	}, {
		field: 'status',
		title: '订单状态',
		search: true,
		type : 'select',
		formatter: Dict.getNameForList('order_status'),
		key: 'order_status'
	}, {
		field: 'amount',
		title: '订单总额',
		formatter: moneyFormat
	}, {
		field : 'applyUser',
		title : '下单用户',
		search: true
    }, {
    	field : 'applyDatetime',
    	title : '下单时间',
    	formatter: dateTimeFormat
    }, {
    	field: 'remark',
    	title: '备注'
    }];
	buildList(router, columns, {
		searchParams: {
			companyCode: companyCode
		}
	});
	
	$('#refundBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/merchant/refund.htm?code="+selRecords[0].code+"&name="+encodeURI(encodeURI(selRecords[0].name));
	});
	
	$('#logisticsBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/merchant/logistics.htm?code="+selRecords[0].code+"&name="+encodeURI(encodeURI(selRecords[0].name));
	});
	
	$('#siteBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/merchant/site.htm?code="+selRecords[0].code+"&name="+encodeURI(encodeURI(selRecords[0].name));
	});
	
	$('#sureBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/merchant/sure.htm?code="+selRecords[0].code+"&name="+encodeURI(encodeURI(selRecords[0].name));
	});
	
	$('#abnormalBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/merchant/abnormal.htm?code="+selRecords[0].code+"&name="+encodeURI(encodeURI(selRecords[0].name));
	});
})