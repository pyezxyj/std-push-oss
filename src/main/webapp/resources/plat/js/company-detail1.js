$(function() {
	var data = {"userid":getUserId()};
	var url = $("#basePath").val()+"/plat/company/detailuserid";
	var userId = '';
	doGetAjax(url, data, doGetDetailBack);
	
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/company.htm";
	});
	
	//修改公司
	$('#editBtn').click(function() {
		window.location.href = $("#basePath").val()+"/plat/company_edit.htm?userid=" + getUserId();
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		$("#code").html(res.data.code);
		$("#name").html(res.data.name);
		userId = res.data.userid;
		$("#userid").html(res.data.userid);
		$("#corporation").html(res.data.corporation);
		$("#telephone").html(res.data.telephone);
		$("#fax").html(res.data.fax);
		$("#email").html(res.data.email);
		$("#url").html(res.data.url);
		$("#logo").attr({"src":res.data.logo});
		$("#address").html(res.data.address);
		$("#barCode").attr({"src":res.data.barCode});
		$("#copyright").html(res.data.copyright);
		$("#slogan").html(res.data.slogan);
		$("#longitude").html(res.data.longitude);
		$("#latitude").html(res.data.latitude);
		$("#description").html(res.data.description);
	}else{
		alert(res.msg);
	}
}