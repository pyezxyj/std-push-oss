$(function() {
	// 角色下拉框初始化,同步调用
	doGetAjaxIsAsync($("#basePath").val() + "/role/list", null,false, doSuccessRoleBack);
	
	$("#userCode").html(getQueryString("userCode"));
	$("#userName").html(decodeURI(getQueryString("userName")));
	
	//查询当前用户的角色
	var data = {"userCode":$("#userCode").html()};
	doGetAjax($("#basePath").val()+"/user/roleList", data, doGetDetailBack);
	
	//提交
	$('#subBtn').click(function() {
		if(!$("#jsForm").valid()){
			return false;
		}
		var url = $("#basePath").val()+"/user/userRole/changeRole";
		var data = {"userCode":$("#userCode").html(),"roleCode":$("#roleCode").val()};
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/security/user.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			roleCode: {
				required: true,
				maxlength: 32
			}
		}
	});
});

function doSuccessRoleBack(res){
	var data = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(data) != "undefined"){
		for(var i = 0;i < data.length;i++){
			html += "<option value='"+data[i].roleCode+"'>" + data[i].roleName + "</option>";
		}
	}
	$("#roleCode").html(html);
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#roleCode").val(res.data[0].roleCode);
		}else{
			$("#roleCode").val("");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/security/user.htm";
	}else{
		alert(res.msg);
	}
}