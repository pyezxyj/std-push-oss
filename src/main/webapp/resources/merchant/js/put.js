$(function() {
	
	var code = getQueryString('code');
	var companyCode = getCompanyId(getUserId());
	var router = '/merchant/input';
	
	var fields = [{
		title: '商品名称',
		field: 'name',
		maxlength: 255
	}, {
		field: 'category',
		title: '大类',
		url: $('#basePath').val() + '/merchant/genre/list?parentCode=0&companyCode=' + companyCode,
		keyName: 'code',
		valueName: 'name',
		type: 'select',
		required: true
	}, {
		field: 'type',
		title: '小类',
		url: $('#basePath').val() + '/merchant/genre/list?companyCode=' + companyCode,
		keyName: 'code',
		valueName: 'name',
		type: 'select',
		required: true
	}, {
		title: '库存',
		field: 'quantity',
		maxlength: 11
	}, {
		title: '原价',
		field: 'originalPrice',
		amount: true,
		required: true
	}, {
		title: '折扣价',
		amount: true,
		field: 'discountPrice'
	}, {
		title: '位置',
		field: 'location',
		type: 'select',
		data: {'1':'普通','2':'热门'},
		required: true
	}, {
		title: '顺序',
		maxlength: 11,
		field: 'orderNo'	
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 255,
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '上架',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					$('#jsForm').find('input[type=file]').parent().next().each(function(i, el) {
						data[el.id] = $(el).attr('src');
					});
					
					var url = $("#basePath").val()+ '/merchant/input/put';
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