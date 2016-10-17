$(function() {
	
	var code = getQueryString('code');
	var router = '/store/order';
	
	var fields = [{
		field: 'code1',
		title: '订单编号',
		'[value]': 'code',
		readonly: true
	}, {
		title: '商品',
		field: 'productName',
		readonly: true
	}, {
		title: '价格',
		field: 'payPrice',
		readonly: true,
		amount: true
	}, {
		title: '下单时间',
		field: 'payDatetime',
		readonly: true,
		formatter: dateTimeFormat
	}, {
		title: '状态',
		field: 'status',
		readonly: true,
		type: 'select',
		key: 'order_status'
	}, {
		title: '提货人',
		field: 'taker',
		required: true,
		maxlength: 10
	}, {
		title: '意见说明',
		field: 'takeNote',
		maxlength: 250
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '已提',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					var url = $("#basePath").val()+ '/store/order/confirm';
					ajaxPost(url, data).then(function(res) {
						if (res.success) {
							alert("操作成功");
							goBack();
						}
					});
				}
			}
		}, {
			title: '作废',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					var url = $("#basePath").val()+ '/store/order/end';
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