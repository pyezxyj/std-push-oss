$(function() {
	var code = getQueryString('code');
	var router = '/general/city';
	
	var fields = [{
		field: 'isDefault',
		type: 'hidden',
		defaultValue: '0'
	}, {
		title: '名称',
		field: 'name',
		required: true,
		maxlength: 32
	}, {
		title: '负责人',
		field: 'userId',
		required: true,
		type: 'select',
		url: $("#basePath").val() + '/user/list',
		keyName: 'userId',
		valueName: 'loginName'
	}, {
		title: '优先级',
		field: 'location',
		required: true,
		type: 'select',
		key: 'city_location'
	}, {
		title: '电话',
		field: 'mobile',
		required: true,
		maxlength: 30
	}, {
		title: '邮箱',
		field: 'email',
		required: true,
		email: true,
		maxlength: 30
	}, {
		title: '地址',
		required: true,
		type: 'citySelect'
	}, {
		placeholder: '详细地址（如街道、门牌号等）',
		field: 'address',
		required: true,
		maxlength: 100
	}, {
		title: '域名',
		field: 'domain',
		maxlength: 100
	}, {
		title: 'logo',
		field: 'logo',
		type: 'img'
	}, {
		title: '二维码',
		field: 'qrCode',
		type: 'img'
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250
	}, {
		title: '公司简介',
		field: 'description',
		type: 'textarea',
		required: true
	}];
	
	buildDetail(router, fields, code);
//	$("#city-group").citySelect({
//		required:false
//	}); 
//	var description = UE.getEditor('description');
//	$('#location').renderDropdown(Dict.getName('city_location'));
//	$('#userId').renderDropdown({
//		url: $("#basePath").val() + '/user/list?roleCode=SR201600000000000002',
//		keyName: 'userId',
//		valueName: 'loginName'
//	});
//	var code = getQueryString('code');
//	if (code) {
//		doGetAjax($("#basePath").val()+"/city/detail", {
//			code: code
//		}, function(res) {
//			if (res.success) {
//				var data = res.data;
//				$('#code').val(data.code);
//				$('#name').val(data.name);
//				$('#userId').val(data.userId);
//				$('#location').val(data.location);
//				$('#domain').val(data.domain);
//				$('#mobile').val(data.mobile);
//				$('#email').val(data.email);
//				$('#address').val(data.address);
//				$('#province').val(data.province);
//				$('#city').val(data.city);
//				$('#area').val(data.area);
//				$('#remark').val(data.remark);
//				$('#img1').attr('src', data.logo);
//				$('#img2').attr('src', data.qrCode);
//				description.ready(function() {
//					description.setContent(data.description);
//				});
//			}
//		});
//	}
//	
//	//提交
//	$('#subBtn').click(function() {
//		if ($('#jsForm').valid()) {
//			if (!$('#img1').attr('src') || !$('#img2').attr('src')) {
//				alert('请上传图片！');
//				return;
//			}
//			var data = {};
//			var t = $('#jsForm').serializeArray();
//			$.each(t, function() {
//				data[this.name] = this.value;
//			});
//			data['logo'] = $('#img1').attr('src');
//			data['qrCode'] = $('#img2').attr('src');
//			var url = $("#basePath").val()+"/city/" + (code ? 'edit' : 'add');
//			ajaxPost(url, data).then(function(res) {
//				if (res.success) {
//					alert("操作成功");
//					goBack();
//				}
//			});
//		}
//		
//	});
//	
//	//返回
//	$('#backBtn').click(function() {
//		goBack();
//	});
//	
//	
//	$("#jsForm").validate({
//		rules: {
//			name: {
//				required: true,
//				maxlength: 30
//			},
//			userId: {
//				required: true,
//			},
//			location: {
//				required: true,
//			},
//			domain: {
//				required: true,
//				maxlength: 100
//			},
//			mobile: {
//				required: true,
//				maxlength: 30
//			},
//			email: {
//				required: true,
//				maxlength: 30
//			},
//			city: {
//				required: true
//			},
//			province: {
//				required: true
//			},
//			pic1: {
//				required: true
//			},
//			pic2: {
//				required: true
//			},
//			area: {
//				required: true
//			},
//			address: {
//				required: true,
//				maxlength: 100
//			},
//			description: {
//				required: true
//			},
//			remark: {
//				maxlength: 250
//			}
//		}
//	});
	
});