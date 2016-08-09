var templetCode = null;
var contentType = null;

$(function() {
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/search/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/buy.htm";
	});
	
});	



function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#person").html(res.data[0].person);
			$("#personDesc").html(res.data[0].personDesc);
			$("#contact").html(res.data[0].contact);
			$("#content1").html(res.data[0].content1);
			$("#content2").html(res.data[0].content2);
			$("#remark").html(res.data[0].remark);
			$("#createDatatime").html(dateFormatter(res.data[0].createDatatime));
		}else{
			alert("根据菜单编号添加修改失败");
		}
	}else{
		alert(res.msg);
	}
}

//格式化时间
function dateFormatter(value, row){
	return dateFormat(value,'yyyy-MM-dd HH:mm:ss');
}
