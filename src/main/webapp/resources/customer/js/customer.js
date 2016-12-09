$(function() {
	
	showPermissionControl();
	var systemId = getSystemId();
	var router = '/customer';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'name',
		title : '名称',
		search: true
	}, {
    	field : 'systemCode',
		title : '所属系统',
		key: 'system',
		formatter: Dict.getNameForList('system'),
		search: !systemId
    }, {
		field : 'mobile',
		title : '手机号',
		search: true
	}, {
		field: 'level',
		title: '用户分组',
		key: 'user_kind',
		type: 'select',
		formatter: Dict.getNameForList('user_kind'),
		search: true
	}, {
		field: 'wechatId',
		title: '微信id'
	}, {
		field: 'jpushId',
		title: '极光id'
	}, {
		field: 'remark',
		title: '备注'
	}];
	buildList(router, columns, {
		searchParams: {
			systemCode: systemId
		},
		pageRouter: '/customer/customer',
		uid: ['mobile', 'systemCode'],
		beforeImport: function() {
			if ($('#systemCode').val()) {
				systemId = $('#systemCode').val();
				return true;
			} else {
				alert('请先选择搜索栏中"所属系统"再导入');
				$('#systemCode').highlight();
				return false;
			}
		},
		getImportData: function(data) {
			ajaxPost($('#basePath').val() + '/customer/import', {
				systemCode: systemId,
				receiverList: data
			}).then(function(res) {
				if (res.success) {
					alert('操作成功');
					$('#tableList').bootstrapTable('refresh',{url: $('#tableList').bootstrapTable('getOptions').url});
				}
			});
		}
	});
});