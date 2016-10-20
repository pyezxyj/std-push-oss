$(function() {
	$("#userId").html(getQueryString("userId"));
	$("#loginName").html(decodeURI(getQueryString("loginName")));
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
		data["userId"] = $("#userId").html();
		var url = $("#basePath").val()+"/user/pwd/reset";
		ajaxPost(url, data).then(function(res) {
			if (res.success == true) {
				alert("操作成功");
				window.location.href = $("#basePath").val()+"/security/user.htm";
			}else{
				alert(res.msg);
			}
		});
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/security/user.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			adminPwd: {
				required: true,
				maxlength: 12,
				minlength: 6
			}
		}
	});
});