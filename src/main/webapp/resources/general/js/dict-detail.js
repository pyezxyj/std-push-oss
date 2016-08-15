//页面初始化
var parentId = null;
$(function(){
	//页面数据字典初始化
	initData();
	
	var id = getQueryString('id');
	//新增修改判断
	if(isBlank(id)){
		$("#operate").val("add");
	}else{
		$("#operate").val("edit");
		$("#operContent").text("修改数据字典");
		var data = {"id":id,"type":"1"};
		var url = $("#basePath").val()+"/general/dict/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交
	$('#subBtn').click(function() {
	    if(!$("#jsForm").valid()){
			return false;
		}
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data["type"]="1";
		var url = $("#basePath").val()+"/general/dict/" + $("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/general/dict.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			pId: {
				required: true,
				maxlength: 32
			},
			key: {
				required: true,
				maxlength: 32
			},
			value: {
				required: true,
				maxlength: 64
			}
			
		}
	});
});
function initData(){
	//父编号
	var data = {"pId":"0","type":"1"};
	var url =$("#basePath").val()+"/general/dict/list";
	doGetAjaxIsAsync(url, data,false, doSucBackPId);
}
function selectChange(value){
	var id = value;
	var data= {"id":id,"type":"1"};
	var url =$("#basePath").val()+"/general/dict/list";
	doGetAjaxIsAsync(url, data,false, doSucBackValue);

}
function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#id").val(res.data[0].id);
			$("#pId").val(res.data[0].pId);
			$("#key").val(res.data[0].key);
			$("#value").val(res.data[0].value);
			$("#remark").val(res.data[0].remark);
		}else{
			alert("根据数据字典编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/general/dict.htm";
	}else{
		alert(res.msg);
	}
}
//数据字典（父编号）关联的回执方法
function doSucBackPId(res){
	parentId = res.data;
	var html = "<option value=''>请选择</option><option value='0'>无父编号</option>";
	if(typeof(parentId) != "undefined"){//判断undifined
		for(var i = 0;i < parentId.length;i++){
			html += "<option value='"+parentId[i].id+"'>"+parentId[i].key+"</option>";
		}
	}
	//父编号
	$("#pId").html(html);
}

function doSucBackValue(res){
	$("#key").val(res.data[0].value);
}