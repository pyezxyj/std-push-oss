$(function() {
	var code = getQueryString('code');
	var view = !!getQueryString('v');
	var type = getQueryString('t') || '';
	var router = '/message';
	
	var fields = [{
    	field : 'fromSystemCode',
		title : '发送单位',
		key: 'system',
		type: 'select',
		required: true,
		onChange: function(v, r) {
			!view && $('#toMobile').renderDropdown({
				url: $('#basePath').val() + '/customer/list?systemCode=' + v,
				keyName: 'mobile',
				valueName: 'mobile'
			});
			$('#toSystemCode').val(v);
			if (!view) {
				ajaxGet($('#basePath').val() + '/channel/list', {
					status: 1,
					systemCode: v || '0'
				}).then(function(res) {
					if (res.success) {
						var data = res.data || [];
						var html = "<option value=''></option>";
						for(var i = 0;i < data.length;i++){
							html += "<option value='"+data[i]['channelType']+ '|' +data[i]['pushType'] +"'>"+
							Dict.getName('channel_type',data[i]['channelType'])+ '/' +Dict.getName('push_type',data[i]['pushType'])+"</option>";
						}
						$('#channelCode').empty();
						$('#channelCode').html(html);
						$('#channelCode').trigger('change');
					}
				});
			}
		},
		readonly: view
    }, {
    	field: 'channelCode',
    	title: '发送渠道',
    	type: 'select',
    	onChange: function(v, r) {
    		var vs = v.split('|');
    		$('#channelType').val(vs[0]);
    		$('#pushType').val(vs[1]);
    		$('#pushType').trigger('change');
    		$('#kind').trigger('change');
    	},
    	hidden: view,
    	required: true
    }, {
    	field: 'templateId',
    	title: '发送模板',
    	type: 'select',
    	url: $('#basePath').val() + '/tpl/list',
		keyName: 'templateId',
		valueName: 'title',
		required: true,
		hidden: true,
		onChange: function(v, r) {
			$('#first').parent().hide();
			$('#keyword1').parent().hide();
			$('#keyword2').parent().hide();
			$('#keyword3').parent().hide();
			$('#keyword4').parent().hide();
			$('#keyword5').parent().hide();
			$('#remark').parent().hide();
			$('#dateT1').parent().hide();
			$('#adChargeT1').parent().hide();
			$('#typeT1').parent().hide();
			$('#cashBalanceT1').parent().hide();
			if (v == "E1KoO96UdD5-xAuUDhEIktkQBDarcsRJxhljsDEOk3M") {
				$('#first').parent().show();
				$('#keyword1').parent().show();
				$('#keyword2').parent().show();
				$('#keyword3').parent().show();
				$('#keyword4').parent().show();
				$('#keyword5').parent().show();
				$('#remark').parent().show();
			} else if (v == "2rB1FVWGvvqkL0uOnuTuMB2jhyn3e8sd_a2caRvXGyQ") {
				$('#first').parent().show();
				$('#dateT1').parent().show();
				$('#adChargeT1').parent().show();
				$('#typeT1').parent().show();
				$('#cashBalanceT1').parent().show();
				$('#remark').parent().show();
			}
		}
    }, {
    	field : 'channelType',
		title : '渠道大类',
		key: 'channel_type',
		type: 'select',
		readonly: view,
		hidden: !view
    }, {
    	field : 'pushType',
		title : '渠道小类',
		key: 'push_type',
		type: 'select',
		onChange: function(v) {
			if (v == '31') {
				$('#smsContent').parent().hide();
				$('#smsContent1').parent().hide();
				$('#smsTitle').parent().hide();
//				$('#first').parent().show();
//				$('#keyword1').parent().show();
//				$('#keyword2').parent().show();
//				$('#keyword3').parent().show();
//				$('#keyword4').parent().show();
//				$('#keyword5').parent().show();
//				$('#remark').parent().show();
				view && $('#smsContent').parent().show();
				$('#templateId').parent().show();
			} else if (v == '41') {
				$('#smsContent').parent().show();
				$('#smsContent1').parent().hide();
				$('#smsTitle').parent().show();
//				$('#first').parent().hide();
//				$('#keyword1').parent().hide();
//				$('#keyword2').parent().hide();
//				$('#keyword3').parent().hide();
//				$('#keyword4').parent().hide();
//				$('#keyword5').parent().hide();
//				$('#remark').parent().hide();
				$('#kind').val('2');
				$('#kind').trigger('change');
				$('#templateId').parent().hide();
				$('#templateId').val('');
				$('#templateId').trigger('change');
			} else {
				$('#smsContent').parent().hide();
				$('#smsContent1').parent().show();
				$('#smsTitle').parent().hide();
//				$('#first').parent().hide();
//				$('#keyword1').parent().hide();
//				$('#keyword2').parent().hide();
//				$('#keyword3').parent().hide();
//				$('#keyword4').parent().hide();
//				$('#keyword5').parent().hide();
//				$('#remark').parent().hide();
				$('#templateId').parent().hide();
				$('#templateId').val('');
				$('#templateId').trigger('change');
			}
			if (view) {
				$('#keyword2').parent().hide();
				$('#keyword3').parent().hide();
				$('#keyword4').parent().hide();
				$('#keyword5').parent().hide();
				$('#keyword1').parent().hide();
				$('#first').parent().hide();
				$('#remark').parent().hide();
				$('#smsContent').parent().show();
				$('#smsContent1').parent().hide();
				$('#templateId').parent().hide();
			}
		},
		readonly: view,
		hidden: !view
    }, {
    	field : 'toSystemCode',
    	title: '接收单位',
    	hidden: !view,
    	key: 'system',
		type: 'select',
    	readonly: view
    }, {
		title: '发送类型',
		field: 'kind',
		type: 'select',
		data: {'1': '单发', '2': '群发'},
		required: true,
		defaultValue: '1',
		onChange: function(v) {
			if (v == 2) {
				$('#toMobile').parent().hide();
				$('#toMobile').val('');
//				$('#keyword4').parent().hide();
//				$('#keyword4').val('');
				if ($('#pushType').val() == '41') {
					$('#toKind').parent().show();
				} 
			} else if(v == 1) {
				$('#toMobile').parent().show();
//				$('#keyword4').parent().show();
				if ($('#pushType').val() == '41') {
					alert('公告只能群发');
					$('#kind').val('2');
					$('#kind').trigger('change');
					$('#toKind').parent().show();
				} 
			} else {
				$('#toMobile').parent().show();
			}
			
			if (!($('#kind').val() == 2 && $('#pushType').val() == '41')) {
				$('#toKind').parent().hide();
			}
		},
		readonly: view,
		hidden: view
	}, {
		title: '针对分组',
		field: 'toKind',
		type: 'select',
		key: 'user_kind',
		required: true,
		readonly: view,
		hidden: true
	}, {
		title: '接收者手机号',
		field: 'toMobile',
		type: !view ? 'select' : '',
		required: true,
		readonly: view,
		afterSet: function(v, r) {
			view && v && $('#kind').html('单发');
			view && !v && $('#kind').html('群发');
			view && !v && $('#toMobile').parent().hide();
		}
	}, {
		field: 'smsType',
		title: '类别',
		type: 'select',
		key: 'sms_type',
		value: type,
		readonly: view,
		hidden: !view
	}, {
		field: 'status',
		title: '状态',
		key: 'push_status',
		formatter: Dict.getNameForList('push_status'),
		readonly: view,
		hidden: !view
	}, {
		title: '发送内容',
		field: 'smsContent1',
		required: true,
		equal: 'smsContent',
		maxlength: 100,
		readonly: view
	}, {
		title: '发送标题',
		field: 'smsTitle',
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '发送内容',
		field: 'smsContent',
		required: true,
		type: 'textarea',
		hidden: true,
		readonly: view,
		afterSet: function(v) {
			$('#smsContent').html($('#smsContent').html().replace(/\n/g,"<br/>"));
		}
	}, {
		title: '问候语',
		field: 'first',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '办理事项',
		field: 'keyword1',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '审批部门',
		field: 'keyword2',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '处理进度',
		field: 'keyword3',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '申请人',
		field: 'keyword4',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '申请时间',
		field: 'keyword5',
		required: true,
		maxlength: 100,
		type: 'datetime',
		readonly: view,
		hidden: true
	}, {
		title: '变动时间',
		field: 'dateT1',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true,
		type: 'datetime'
	}, {
		title: '变动金额',
		field: 'adChargeT1',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true,
		amount: true
	}, {
		title: '账户类型',
		field: 'typeT1',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '账户余额',
		field: 'cashBalanceT1',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true,
		amount: true
	}, {
		title: '备注',
		field: 'remark',
		required: true,
		maxlength: 100,
		readonly: view,
		hidden: true
	}, {
		title: '预览',
		field: 'preview',
		readonly: true,
		value: '',
		hidden: true
	}, {
		title: '创建时间',
		field: 'createDatetime',
		formatter: dateTimeFormat,
		readonly: view,
		hidden: !view
	}, {
		title: '拟发送时间',
		field: 'topushDatetime',
		formatter: dateTimeFormat,
		readonly: view,
		hidden: !view
	}, {
		title: '发送时间',
		field: 'pushedDatetime',
		formatter: dateTimeFormat,
		readonly: view,
		hidden: !view
	}];
	var buttons = [{
		'title': '返回',
		handler: function() {
			goBack();
		}
	}];
	if (!view) {
		buttons.unshift({
			'title': '保存',
			handler: function() {
				if ($('#jsForm').valid()) {
					var data = $('#jsForm').serializeObject();
					for (var i = 0, len = fields.length; i < len; i++) {
						var item = fields[i];
						if (item.equal && !$('#' + item.field).is(':hidden')) {
							data[item.equal] = $('#' + item.field).val() || $('#' + item.field).attr('src');
						} else if (item.emptyValue && !data[item.field]) {
							data[item.field] = item.emptyValue;
						}
					}
					if ($('#templateId').val() == 'E1KoO96UdD5-xAuUDhEIktkQBDarcsRJxhljsDEOk3M') {
						var wxContent = {};
						wxContent.first = $('#first').val();
						wxContent.keyword1 = $('#keyword1').val();
						wxContent.keyword2 = $('#keyword2').val();
						wxContent.keyword3 = $('#keyword3').val();
						wxContent.keyword4 = $('#keyword4').val();
						wxContent.keyword5 = $('#keyword5').val();
						wxContent.remark = $('#remark').val();
						data.wxContent = wxContent;
					} else if ($('#templateId').val() == '2rB1FVWGvvqkL0uOnuTuMB2jhyn3e8sd_a2caRvXGyQ') {
						var wxContent = {};
						wxContent.first = $('#first').val();
						wxContent.date = $('#dateT1').val();
						wxContent.adCharge = $('#adChargeT1').val();
						wxContent.type = $('#typeT1').val();
						wxContent.cashBalance = $('#cashBalanceT1').val();
						wxContent.remark = $('#remark').val();
						data.wxContent = wxContent;
					}
					var url = $("#basePath").val()+ router + "/send";
					ajaxPost(url, data).then(function(res) {
						if (res.success) {
							alert("操作成功");
							goBack();
						}
					});
				}
			}
		});
	}
	
	buildDetail(router, fields, code, {
		buttons: buttons,
		afterData: function(data) {
			//view && data.smsType != 2 && $('#topushDatetime').parent().hide();
			//view && data.pushType == '41' && $('#toKind').parent().show();
		}
	});
});