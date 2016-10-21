$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var router = '/std/material';
	
	var fields = [{
		field: 'companyCode',
		type: 'hidden',
		value: getCompanyId(getUserId())
	}, {
		title: '隶属',
		field: 'menuCode',
		required: true,
		type: 'select',
		url: $('#basePath').val() + '/std/menu/list',
    	search: true,
    	keyName: 'code',
    	valueName: 'name',
		readonly: view
	}, {
		title: '标题',
		field: 'title',
		required: true,
		maxlength: 30,
		readonly: view
	}, {
		title: '缩略图',
		field: 'pic1',
		required: true,
		type: 'img',
		readonly: view
	}, {
		title: '种类',
		field: 'kind',
		required: true,
		type: 'select',
		key: 'material_kind',
		readonly: view,
		onChange: function(v) {
			if (v == 1) {
				$('#endNoteType').parent().show();
				$('#type').parent().show();
				$('#pic2').parent().show();
				$('#description').parent().show();
				$('#url').parent().hide();
			} else if (v == 2) {
				$('#endNoteType').parent().hide();
				$('#type').parent().hide();
				$('#pic2').parent().hide();
				$('#description').parent().hide();
				$('#url').parent().show();
			} else {
				$('#endNoteType').parent().hide();
				$('#type').parent().hide();
				$('#pic2').parent().hide();
				$('#description').parent().hide();
				$('#url').parent().hide();
			}
		}
	}, {
		title: '尾注类型',
		field: 'endNoteType',
		required: true,
		type: 'select',
		key: 'endnote_type',
		readonly: view,
		hidden: true,
		value: function(r) {
			if (!r.endNote) {
				return '0';
			}
			if (r.endNote.indexOf('http://') > -1) {
				return '2';
			} else {
				return '1';
			}
		},
		onChange: function(v) {
			if (v == 1) {
				$('#endNote1').parent().show();
				$('#endNote2').parent().hide();
			} else if (v == 2) {
				$('#endNote1').parent().hide();
				$('#endNote2').parent().show();
			} else {
				$('#endNote1').parent().hide();
				$('#endNote2').parent().hide();
			}
		}
	}, {
		title: '尾注',
		field: 'endNote1',
		required: true,
		maxlength: 100,
		readonly: view,
		equal: 'endNote',
		'[value]': 'endNote',
		hidden: true
	}, {
		title: '尾注',
		field: 'endNote2',
		required: true,
		hidden: true,
		readonly: view,
		equal: 'endNote',
		'[value]': 'endNote',
		type: 'img'
	}, {
		title: '详情类型',
		field: 'type',
		required: true,
		hidden: true,
		type: 'select',
		key: 'material_type',
		readonly: view,
		onChange: function(v) {
			if (v == 1) {
				$('#pic2').parent().show();
				$('#url').parent().hide();
			} else if (v == 2) {
				$('#pic2').parent().hide();
				$('#url').parent().show();
			} else {
				$('#pic2').parent().hide();
				$('#url').parent().hide();
				if ($('#kind').val() == 2) {
					$('#url').parent().show();
				}
			}
		}
	}, {
		title: '大图',
		field: 'pic2',
		required: true,
		type: 'img',
		readonly: view,
		hidden: true
	}, {
		title: 'url',
		field: 'url',
		required: true,
		readonly: view,
		hidden: true,
		maxlength: 250
	}, {
		title: '富文本内容',
		field: 'description',
		required: true,
		type: 'textarea',
		hidden: true,
		readonly: view
	}, {
		title: '备注',
		field: 'remark',
		maxlength: 250,
		readonly: view
	}];
	
	var options = {};
	if (view) {
		options.buttons = [{
			'title': '返回',
			handler: function() {
				goBack();
			}
		}];
	}
	
	buildDetail(router, fields, code, options);
});