$(function() {
	
	var code = getQueryString('code');
	var router = '/merchant/order';
//取消退款	
	var fields = [{
		title: '订单编号',
		field: 'code1',
		'[value]': 'code',
		readonly: true
	}, {
		title: '订单类型',
		field: 'type',
		type: 'select',
		data: {'0':'散买','1':'批发'},
		readonly: true
	}, {
		title: '订单总额',
		field: 'amount',
		readonly: true,
		amount: true
	}, {
		title: '下单用户',
		field: 'applyUser',
		readonly: true
	}, {
		title: '下单时间',
		field: 'applyDatetime',
		formatter: dateTimeFormat,
		readonly: true
	}, {
		title: '状态',
		field: 'status',
		type: 'select',
		formatter: Dict.getNameForList('order_status'),
		key: 'order_status',
		readonly: true
	}, {
		title: '取消理由',
		field: 'remark',
		required: true,
		maxlength: 255,
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '确定',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					$('#jsForm').find('input[type=file]').parent().next().each(function(i, el) {
						data[el.id] = $(el).attr('src');
					});
					
					var url = $("#basePath").val()+ '/merchant/order/refund';
					ajaxPost(url, data).then(function(res) {
						if (res.success) {
							alert("操作成功");
							goBack();
						}
					});
				}
			}
		}, {
			title: '返回',
			handler: function() {
				goBack();
			}
		}]
	});
	
	
});