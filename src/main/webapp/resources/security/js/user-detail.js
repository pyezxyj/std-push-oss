$(function() {
	var userCode = getQueryString("userCode");
	//新增修改判断
	if(isBlank(userCode)){
		$("#operate").val("add");
	}else{
		$("#userCode").attr("readonly","readonly");
		$("#operate").val("edit");
		$("#operContent").text("修改用户");
		var data = {"userCode":userCode};
		var url = $("#basePath").val()+"/user/list";
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
		var url = $("#basePath").val()+"/user/"+$("#operate").val();
		doPostAjax(url, data, doSaveSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/security/user.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			userCode: {
				required: true,
				number:true,
				maxlength: 32
			},
			userName: {
				required: true,
				maxlength: 32,
				nochina:true
			},
			remark: {
				required: false,
				maxlength: 64
			},
			
		},
		messages: {
			userCode: {
				required: "请输入用户编号",
				number: "用户编号请输入数字",
				maxlength: jQuery.format("用户编号不能大于{0}个字符")
			},
			userName: {
				required: "请输入用户名",
				maxlength: jQuery.format("用户名不能大于{0}个字符"),
				nochina: "请输入以字母打头的数字字母下划线组合"
			},
			remark: {
				maxlength: jQuery.format("备注不能大于{0}个字符"),
			}
		}
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#userCode").attr("readonly","readonly");
			$("#userCode").val(res.data[0].userCode);
			$("#userName").val(res.data[0].userName);
			$("#status").val(res.data[0].status);
			$("#remark").val(res.data[0].remark);
		}else{
			alert("根据用户编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSaveSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/security/user.htm";
	}else{
		alert(res.msg);
	}
}