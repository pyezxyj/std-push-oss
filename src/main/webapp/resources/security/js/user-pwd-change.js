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