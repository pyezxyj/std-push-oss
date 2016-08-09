var templetCode = null;
var contentType = null;

$(function() {
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/menu/list";
	doGetAjax(url, data, doGetDetailBack);
	
	doGetAjaxIsAsync(url, null, false, doSuccessBackCode);
	
	$('#addBtn').click(function() {
		window.location.href = $("#basePath").val()+"/plat/comp_menu_addcontent1.htm?menuCode="+code;
	});
	
	//修改公司菜单
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/plat/comp_menu_addcontent1.htm?code="+selRecords[0].code + '&menuCode=' + code;
	});
	
	//删除
	$('#dropBtn').click(function(){
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		if(!confirm("确认删除公司菜单["+selRecords[0].code+":"+selRecords[0].title+"]?")){
    		return false;
    	}
		var data = {"code":selRecords[0].code};
		var url = $("#basePath").val()+"/plat/content/drop";
		doPostAjax(url, data, doSuccessDelBack);
	});
	
	//详情
	$('#detailBtn').click(function(){
		var selRecords = $('#tableList').bootstrapTable('getSelections')
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+"/plat/comp_menu_addcontent1_detail.htm?code="+selRecords[0].code+"&menuCode="+selRecords[0].menuCode;
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/comp_menu.htm";
	});
	
	
	
	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val()+"/plat/content/list",
		
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return {
				menuCode:code,
			};
		},
		columns : [{
			field : 'picture1',
			title : '小图',
			formatter: function(v) {
				return '<img width="100" src="'+v+'"/>';
			}
		},{
			field : 'title',
			title : '标题',
		},{
			field : 'description',
			title: '备注',
		},{
			field : 'menuCode',
			title: '菜单编号',
			visible : false
		},{
			field : 'endNote',
			title: '尾注',
			visible : false
		}]
	});
});


//状态转化
function statusFormatter(value, row) {
	dictStatus = Dict.getName('content_status');
	for(var i = 0;i < dictStatus.length;i++){
		if(dictStatus[i].value == value){
			return dictStatus[i].remark;
		}
	}
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#name").html(res.data[0].name);
			$("#orderNo").html(res.data[0].orderNo);
			$("#parentCode").html(codeFormatter(res.data[0].parentCode));
			$("#endNote").html(res.data[0].endNote);
			$("#contentType").html(res.data[0].contentType);
			if (res.data[0].templetCode == '001') {
				$('#content-type-2').addClass('active');
			} else {
				$('#content-type-1').addClass('active');
			}
		}else{
			alert("根据菜单编号添加修改失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/plat/comp_menu.htm";
	}else{
		alert(res.msg);
	}
}

function clickPic(value,me) {
	$(me).parent().find('img').removeClass('active');
	$(me).addClass('active');
	if(value == 1){
		templetCode = "002";
		contentType = "list";
	}else if(value == 2){
		templetCode = "001";
		contentType = "ele";
	}
}

function doSuccessDelBack(res) {
	if (res.success == true) {
		alert("删除成功");
		$('#tableList').bootstrapTable('refresh');
	}else{
		alert("删除失败");
	}
}

var dictCode = null;




function doSuccessBackCode(res){
	dictCode = res.data;
}

function codeFormatter(value, row) {
	for(var i = 0;i < dictCode.length;i++){
		if(dictCode[i].code == value){
			return dictCode[i].name;
		}
	}
}