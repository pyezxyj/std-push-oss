var templetCode = null;
var contentType = null;

$(function() {
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/search/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/cooprate.htm";
	});
	
});	



function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#organization").html(res.data[0].organization);
			$("#person").html(res.data[0].person);
			$("#contact").html(res.data[0].contact);
			$("#content1").html(res.data[0].content1);
			$("#organizationDesc").html(res.data[0].organizationDesc);
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
