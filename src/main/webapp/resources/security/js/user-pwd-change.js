$(function() {
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
		var url = $("#basePath").val()+"/user/pwd/change";
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/security/right.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			oldPwd: {
				required: true,
				maxlength: 12,
				minlength: 6
			},
			newPwd: {
				required: true,
				maxlength: 12,
				minlength: 6
			}
		},
		messages: {
			oldPwd: {
				required: "请输入旧密码",
				maxlength: jQuery.format("旧密码不能大于{0}个字符"),
				minlength: jQuery.format("旧密码不能小于{0}个字符")
			},
			newPwd: {
				required: "请输入新密码",
				maxlength: jQuery.format("新密码不能大于{0}个字符"),
				minlength: jQuery.format("新密码不能小于{0}个字符")
			}
		}
	});
});

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		//doGetAjaxIsAsync($("#basePath").val()+"/user/logout", null, false, null);
		//window.location.href = $("#basePath").val()+"/security/signin.htm";
		location.reload();
	}else{
		alert(res.msg);
	}
}