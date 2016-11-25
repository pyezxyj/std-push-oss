$(function() {
	
	var code = getQueryString('code');
	var companyCode = getCompanyId(getUserId());
	var view = true;
	var router = '/merchant/input';
	
	var fields = [{
		title: '商品名称',
		field: 'name',
		maxlength: 255,
		readonly: true
	}, {
		field: 'category',
		title: '大类',
		url: !!view ? ($('#basePath').val() + '/merchant/genre/detail') 
				: ($('#basePath').val() + '/merchant/genre/list?parentCode=0&companyCode=' + companyCode),
		keyName: 'code',
		valueName: 'name',
		type: 'select',
		required: true,
		readonly: !!view,
		onChange: function(v, r) {
			$('#type').renderDropdown({
				url: $('#basePath').val() + '/merchant/genre/list?companyCode=' + companyCode + '&parentCode=' + v,
				keyName: 'code',
				valueName: 'name'
			});
		}
	}, {
		field: 'type',
		title: '小类',
		type: 'select',
		url: !!view ? ($('#basePath').val() + '/merchant/genre/detail') : '',
		keyName: 'code',
		valueName: 'name',
		readonly: !!view,
		required: true
	}, {
		title: '库存',
		field: 'quantity',
		'Z+': true,
		maxlength: 11,
		readonly: true
	}, {
		title: '原价',
		field: 'originalPrice',
		amount: true,
		required: true
	}, {
		title: '折扣价',
		amount: true,
		field: 'discountPrice',
		required: true
	}, {
		title: '位置',
		field: 'location',
		type: 'select',
		url: $('#basePath').val() + '/merchant/position/list?companyCode=' + companyCode,
		keyName: 'code',
		valueName: 'name',
		defaultOption: '普通',
		required: true
	}, {
		title: '顺序',
		maxlength: 11,
		number: true,
		field: 'orderNo',
		required: true
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 255
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