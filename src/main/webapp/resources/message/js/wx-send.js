String.prototype.temp = function(obj) {
		return this.replace(/\{\{(\w+)\.DATA\}\}/gi, function(matchs) {
			var returns = obj[matchs.replace(/\{\{(\w+)\.DATA\}\}/, '$1')];
			return (returns + "") == "undefined"? "": returns;
		});
	};
	
$(function() {
	var toSystemCode = "", userInfo = {}, tmplList = [];
	ajaxGet($('#basePath').val() + '/role/sysCode', {}, false, true).then(function(res) {
		if (res.success) {
			userInfo = res.data[0];
			toSystemCode = userInfo.systemCode;
		}else{
			alert(res.msg);
		}
	});
	ajaxGet($('#basePath').val() + '/tpl/list?channelType=3&pushType=31&systemCode='+toSystemCode, {}, false, true).then(function(res) {
		if (res.success) {
			var list = res.data;
			for(var i = 0; i < list.length; i++){
				if(list[i].content.indexOf("keyword1") != -1){
					list[i].first = userInfo.first;
					list[i].keyword1 = userInfo.content;
					list[i].keyword2 = userInfo.department;
					break;
				}
			}
			tmplList = list;
		}else{
			alert(res.msg);
		}
	});
	var code = getQueryString('id');
	var router = '/message';
	
	var tpl = '';
	var tpldata = {};
	
	var fields = [{
		title: '接收者',
		field: 'toMobile',
		type: 'select',
		url: $('#basePath').val() + '/customer/list?systemCode='+toSystemCode,
		keyName: 'mobile',
		valueName: 'mobile',
		required: true
	}, {
		title: '内容',
		field: 'templateId',
		type: 'select',
		//url: $('#basePath').val() + '/tpl/list?channelType=3&pushType=31&systemCode='+toSystemCode,
		wxData: tmplList,
		keyName: 'templateId',
		valueName: 'title',
		required: true,
		onChange: function(v, r) {
			tpl = r.content;
			tpldata.title = r.title;
			tpldata.keyword2 = r.keyword2;
			tpldata.keyword1 = r.keyword1;
			tpldata.first = r.first;
			//if(v == 'E1KoO96UdD5-xAuUDhEIktkQBDarcsRJxhljsDEOk3M'){
			if(r.content.indexOf("keyword1") != -1){
				$("#first").val(r.first);
				$('#keyword1').val(r.keyword1).parent().show();
				$('#keyword2').val(r.keyword2).parent().show();
				$('#keyword3').parent().show();
				$('#keyword4').parent().show();
				$('#keyword5').parent().show();
				$('#date').parent().hide();
				$('#adCharge').parent().hide();
				$('#type').parent().hide();
				$('#cashBalance').parent().hide();
			//}else if(v == '2rB1FVWGvvqkL0uOnuTuMB2jhyn3e8sd_a2caRvXGyQ'){
			}else if(r.content.indexOf("adCharge") != -1){
				$("#first").val("");
				$('#date').parent().show();
				$('#adCharge').parent().show();
				$('#type').parent().show();
				$('#cashBalance').parent().show();
				$('#keyword1').parent().hide();
				$('#keyword2').parent().hide();
				$('#keyword3').parent().hide();
				$('#keyword4').parent().hide();
				$('#keyword5').parent().hide();
			}
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		}
	}, {
		title: '问候语',
		field: 'first',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.first = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		}
	}, {
		title: '办理事项',
		field: 'keyword1',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.keyword1 = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '変动时间',
		field: 'date',
		required: true,
		maxlength: 100,
		type: 'datetime',
		onBlur: function(v) {
			setTimeout(function(){
				tpldata.date = $("#date").val();
				$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
			}, 100);
		},
		hidden: true
	}, {
		title: '审批部门',
		field: 'keyword2',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.keyword2 = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '変动金额',
		field: 'adCharge',
		required: true,
		maxlength: 100,
		formatter: moneyFormat,
		onKeyup: function(v) {
			tpldata.adCharge = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '办理进度',
		field: 'keyword3',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.keyword3 = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '変动类型',
		field: 'type',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.type = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '申请人',
		field: 'keyword4',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.keyword4 = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '账户余额',
		field: 'cashBalance',
		required: true,
		maxlength: 100,
		formatter: moneyFormat,
		onKeyup: function(v) {
			tpldata.cashBalance = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		},
		hidden: true
	}, {
		title: '申请时间',
		field: 'keyword5',
		required: true,
		maxlength: 100,
		type: 'datetime',
		onBlur: function(v) {
			setTimeout(function(){
				tpldata.keyword5 = $("#keyword5").val();
				$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
			}, 100);
		},
		hidden: true
	}, {
		title: '备注',
		field: 'remark',
		required: true,
		maxlength: 100,
		onKeyup: function(v) {
			tpldata.remark = v;
			$('#content').html('<b style="color: #000">' + tpldata.title + '</b><br/>' + tpl.temp(tpldata).replace(/\n/g,"<br/>"));
		}
	}, {
		title: '预览',
		field: 'content',
		readonly: true,
		value: ''
		
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
					var smsContent = {};
					smsContent.first = $('#first').val();
					smsContent.keyword1 = $('#keyword1').val();
					smsContent.keyword2 = $('#keyword2').val();
					smsContent.keyword3 = $('#keyword3').val();
					smsContent.keyword4 = $('#keyword4').val();
					smsContent.keyword5 = $('#keyword5').val();
					smsContent.date = $('#date').val();
					smsContent.adCharge = $('#adCharge').val();
					smsContent.type = $('#type').val();
					smsContent.cashBalance = $('#cashBalance').val();
					smsContent.remark = $('#remark').val();
					data.fromSystemCode = toSystemCode;
					data.toSystemCode = toSystemCode;
					data.smsType = "1";
					data.smsContent = smsContent;
					var url = $("#basePath").val()+ '/message/wx/send';
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