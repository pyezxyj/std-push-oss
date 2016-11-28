$(function() {
	var code = getQueryString('code');
	var router = '/general/city';
	
	var fields = [{
		title: '名称',
		field: 'name'
	}, {
		title: '负责人',
		field: 'userId',
		type: 'select',
		url: $("#basePath").val() + '/user/detail',
		keyName: 'userId',
		valueName: 'loginName'
	}, {
		title: '优先级',
		field: 'location',
		type: 'select',
		key: 'city_location'
	}, {
		title: '电话',
		field: 'mobile'
	}, {
		title: '邮箱',
		field: 'email'
	}, {
		title: '地址',
		field: 'province',
		formatter: function(v, r) {
			var res = $.unique([r.province, r.city, r.area]).reverse();
			return res.join(' ') + ' ' + r.address;
		}
	}, {
		title: '域名',
		field: 'domain'
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
		field: 'remark'
	}, {
		title: '公司简介',
		field: 'description'
	}];
	
	buildDetailView(router, fields, code);
});