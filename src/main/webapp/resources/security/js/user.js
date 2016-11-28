$(function(){
	showPermissionControl();
	
	var router = '/user';
	
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	}, {
		field : 'loginName',
		title : '用户名',
		search: true
	}, {
		field: 'status',
		title: '状态',
		formatter : Dict.getNameForList('user_status'),
		type: 'select',
		key: 'user_status'
	}, {
		field: 'roleCode',
		title: '角色',
		type: 'select',
		url: $('#basePath').val() + '/role/list',
		keyName: 'code',
		valueName: 'name',
		search: true
	}, {
    	field: 'remark',
    	title: '备注'
    }];
	buildList(router, columns, {
		pageRouter: '/security/user'
	});
	
	$('#assignBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/security/user_role.htm?userId="+selRecords[0].userId+"&loginName="+encodeURI(encodeURI(selRecords[0].loginName))+"&kind="+selRecords[0].kind;
	});
	
	$('#resetBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		location.href = $("#basePath").val()+"/security/user_pwd_reset.htm?userId=" + selRecords[0].userId + '&loginName=' + selRecords[0].loginName;
	});
	
	$('#rockBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
    	var url = $("#basePath").val()+ router + '/cancel';
    	var data = {userId:selRecords[0].userId};
		ajaxPost(url, data).then(function(res) {
			if (res.success) {
				alert('操作成功');
				$('#tableList').bootstrapTable('refresh',{url: $('#tableList').bootstrapTable('getOptions').url});
			} else {
				alert(res.msg);
			}
		});
	});
})

