$(function() {
	
	var code = getQueryString('code');
	var router = '/forum/post';
	
	var fields = [{
		title: '标题',
		field: 'title',
		readonly: true
	}, {
		title: '内容',
		field: 'content',
		readonly: true
	}, {
		title: '图片',
		field: 'pic',
		readonly: true,
		type: 'img'
	}, {
		title: '发帖人',
		field: 'publisher',
		readonly: true
	}, {
		title: '发帖时间',
		field: 'publishDatetime',
		readonly: true,
		formatter: dateTimeFormat
	}, {
		title: '状态',
		field: 'status',
		readonly: true,
		type: 'select',
		key: 'post_status'
	}, {
		title: '举报',
		field: 'isReport',
		readonly: true,
		type: 'select',
		key: 'true_false'
	}, {
		title: '推荐',
		field: 'isHeadline',
		readonly: true,
		type: 'select',
		key: 'true_false'
	}, {
		title: '置顶',
		field: 'isTop',
		readonly: true,
		type: 'select',
		key: 'true_false'
	}, {
		title: '精华',
		field: 'isEssence',
		readonly: true,
		type: 'select',
		key: 'true_false'
	}, {
		title: '复核人',
		field: 'approver',
		readonly: true
	}, {
		title: '复核时间',
		field: 'approveDatetime',
		readonly: true,
		formatter: dateTimeFormat
	}, {
		title: '复核说明',
		field: 'approveNote1',
		'[value]': 'approveNote',
		readonly: true
	}, {
		title: '意见说明',
		field: 'approveNote',
		value: '',
		required: true,
		maxlength: 250
	}];
	
	buildDetail(router, fields, code, {
		buttons: [{
			title: '通过',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					data.status = 1;
					var url = $("#basePath").val()+ router + "/check";
					ajaxPost(url, data).then(function(res) {
						if (res.success) {
							alert("操作成功");
							goBack();
						}
					});
				}
			}
		}, {
			title: '删除',
			handler: function() {
				 $("#approveNote").rules("add",{required:false}); 
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					var url = $("#basePath").val()+ router + "/delete";
					ajaxPost(url, data).then(function(res) {
						if (res.success) {
							alert("操作成功");
							goBack();
						}
					});
				}
			}
		}, {
			title: '忽略',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					data.status = 0;
					var url = $("#basePath").val()+ router + "/check";
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