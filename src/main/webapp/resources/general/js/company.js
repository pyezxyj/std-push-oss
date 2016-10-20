$(function() {
	//按钮权限判断
	showPermissionControl();
	
	var router = '/general/company';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'name',
		title : '名称',
		search: true
	},{
		field : 'domain',
		title : '域名',
    },{
    	field : 'slogan',
		title : '广告语'
    },{
		field : 'corporation',
		title : '法人'
	}, {
		field: 'userId',
		title: '实际控制人'
	}, {
		field: 'location',
		title: 'UI位置',
		formatter: Dict.getNameForList('cmp_location'),
		key: 'cmp_location',
		type: 'select'
	}];
	buildList(router, columns);
	
	$('#rockBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
    	var url = $("#basePath").val()+ router + '/cancel';
    	var data = {code:selRecords[0].code};
		ajaxPost(url, data).then(function(res) {
			if (res.success) {
				alert('操作成功');
				$('#tableList').bootstrapTable('refresh',{url: $('#tableList').bootstrapTable('getOptions').url});
			} else {
				alert(res.msg);
			}
		});
	});
});

