$(function() {
	//按钮权限判断
	showPermissionControl();
	
	var router = '/general/city';
	var columns = [{
		field : '',
		title : '',
		checkbox : true
	},{
		field : 'name',
		title : '名称',
		search: true
	},{
		field : 'location',
		title : '优先级',
		formatter: Dict.getNameForList('city_location'),
		search: true,
		key: 'city_location'
    },{
    	field : 'userId',
		title : '负责人'
    },{
    	field : 'isDefault',
		title : '默认',
		formatter: Dict.getNameForList('true_false'),
		search: true,
		key: 'true_false'
	}, {
		field : 'remark',
		title : '备注'
	}];
	buildList(router, columns);
	
	$('#defaultBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
    	var url = $("#basePath").val()+"/general/city/default";
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

