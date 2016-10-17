$(function() {
	$('#btn-icon').append($('.iconctn'));
	$('#btn-icon').click(function() {
		$('.iconctn').show();
	});
	$('.iconctn li').click(function(e) {
		$('.iconctn').hide();
		e.stopPropagation();
		$('#icon1').attr('class',($(this).attr('class')));
		$('#img1').attr('src', '');
	});
	$('#btn-icon').on('blur', function() {
		$('.iconctn').hide();
	});
	var global = !+getQueryString('b') ? 1 : 0;
	var params = '';
	if (global) {
		params = '?parentCode=0&isGlobal=1';
	} else {
		var cityId;
		if (!global) {
			cityId = getCityId(getUserId()) || '0';
		}
		params = '?parentCode=0&isDqNavigate=1&siteCode=' + cityId;
	}
	$('#type').renderDropdown(Dict.getName('view_type'));
	$('#status').renderDropdown(Dict.getName('active_status'));
	$('#parentCode').renderDropdown({
		url: $("#basePath").val() + '/view/list' + params,
		keyName: 'code',
		valueName: 'name'
	});
	var code = getQueryString('code');
	if (code) {
		doGetAjax($("#basePath").val()+"/view/detail", {
			code: code
		}, function(res) {
			if (res.success) {
				var data = res.data;
				$('#code').val(data.code);
				$('#name').val(data.name);
				$('#parentCode').val(data.parentCode);
				$('#status').val(data.status);
				$('#type').val(data.type);
				$('#orderNo').val(data.orderNo);
				$('#url').val(data.url);
				$('#img1').attr('src', data.pic);
			}
		});
	}
	
	//提交
	$('#subBtn').click(function() {
		if ($('#jsForm').valid()) {
			if (!$('#img1').attr('src')) {
				alert('请上传图片！');
				return;
			}
			var data = {};
			var t = $('#jsForm').serializeArray();
			$.each(t, function() {
				data[this.name] = this.value;
			});
			data['pic'] = $('#img1').attr('src');
			data['parentCode'] = data['parentCode'] || 0;
			data['isGlobal'] = global;
			data['siteCode'] = cityId || '';
			var url = $("#basePath").val()+"/view/" + (code ? 'edit' : 'add');
			ajaxPost(url, data).then(function(res) {
				if (res.success) {
					alert("操作成功");
					goBack();
				}
			});
		}
		
	});
	
	//返回
	$('#backBtn').click(function() {
		goBack();
	});
	
	
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 30
			},
			status: {
				required: true
			},
			type: {
				required: true
			},
			orderNo: {
				required: true,
				maxlength: 10
			},
			url: {
				required: true,
				maxlength: 100
			}
		}
	});
	
});

function selectImage(file,name){
	if(!file.files || !file.files[0]){
		return;
	}
	zipImg(file.files[0], document.getElementById(name));
	$('#icon1').attr('class', '');
}