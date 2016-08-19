$(function(){
    // frameset框架嵌套，跳转到最外层
	if (top.location != self.location){
		top.location=self.location;
	}
	
    $('.login-box').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){
    	$('.login-box').css({'position':'absolute','left':($(window).width()-692)/2});
	});
	
	// 登录
	$('#loginBtn').click(function() {
		var data = {};
		var t = $('#loginForm').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		var url = $("#basePath").val()+"/user/login";
		doPostAjax(url, data, doSuccessLoginBack);
	});
	
	// enter
	$(document).on('keyup', function(e) {
		if (e.keyCode == 13) {
			$('#loginBtn').click();
		}
	});
});

function doSuccessLoginBack(res){
	if (res.success == true) {
		location.href = $("#basePath").val() + "/security/main.htm";
	}else{
		alert(res.msg);
	}
}