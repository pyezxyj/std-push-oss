
$(function() {
	var code = getQueryString('code');
	if (code) {
		doGetAjax($("#basePath").val()+"/view/detail", {
			code: code
		}, function(res) {
			if (res.success) {
				var data = res.data;
				$('#code').html(data.code);
				$('#name').html(data.name);
				
				ajaxGet($("#basePath").val() + '/view/list', {
					code: data.parentCode
				}).then(function(res) {
					$('#parentCode').html(res.data.length > 0 ? res.data[0].name : '');
				});
				$('#status').html(Dict.getName('active_status', data.status));
				$('#type').html(Dict.getName('view_type', data.type));
				$('#orderNo').html(data.orderNo);
				$('#url').html(data.url);
				$('#img1').attr('src', data.pic);
			}
		});
	}
	
	
	//返回
	$('#backBtn').click(function() {
		goBack();
	});
	
	
});