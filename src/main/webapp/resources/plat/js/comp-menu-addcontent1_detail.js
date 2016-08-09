$(function() {    
	initData();
	var code = getQueryString("code");
	var menuCode = getQueryString("menuCode");
	var companyCode = getQueryString("companyCode");
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/content/list";
	doGetAjax(url, data, doGetDetailBack);
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/comp_menu_addcontent.htm?code="+menuCode;
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#title").html(res.data[0].title);
			$("#picture1").attr({"src":res.data[0].picture1});
			$("#picture2").attr({"src":res.data[0].picture2});
			$("#type").html(typeFormatter(res.data[0].type));
			$("#endNote").html(res.data[0].endNote);
			//点击跳转
			$('#endNote').click(function() {
				url = res.data[0].endNote;
				window.open(url);
			});
			$("#description").html(res.data[0].description);
		}else{
			alert("根据编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

var dictType = null;

function initData(){
	//状态
	var data= {"key":"content_status"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackStatus);
}

function doSucBackStatus(res){
	dictType = res.data;
}

function typeFormatter(value, row) {
	for(var i = 0;i < dictType.length;i++){
		if(dictType[i].value == value){
			return dictType[i].remark;
		}
	}
}