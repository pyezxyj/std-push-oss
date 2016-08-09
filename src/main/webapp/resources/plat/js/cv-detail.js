var templetCode = null;
var contentType = null;
var urlPDF = null;
$(function() {
	var code = getQueryString("code");
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/search/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/cv.htm";
	});
	
});	

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#content1").html(res.data[0].content1);
			var str = res.data[0].content2;
			str = str.substr(str.lastIndexOf('/')+1); 
			$("#content2").html(str);
			$("#createDatatime").html(dateFormatter(res.data[0].createDatatime));
			//点击跳转
			$('#content2').click(function() {
				urlPDF = res.data[0].content2;
				window.open(urlPDF);
			});
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