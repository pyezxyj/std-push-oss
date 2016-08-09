var templetCode = null;
var contentType = null;
$(function() {
	var parentCode ='';
	doGetAjaxIsAsync($("#basePath").val()+"/plat/menu/page", {parentCode:getParentCode()}, true, function(res) {
		if (res.data.length > 0) {
			parentCode = res.data[0].name;
		}
	});
	var code = getQueryString('code');
	var data = {"code":code};
	var url = $("#basePath").val()+"/plat/menu/list";
	doGetAjax(url, data, doGetDetailBack);
	
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
		if (!$('#picture1Img').attr('src') || !$('#picture2Img').attr('src')) {
			alert('请上传文件');
			return;
		}
		data["picture1"]=$('#picture1Img').attr('src');
		data["picture2"]=$('#picture2Img').attr('src');
		
		
		var url = $("#basePath").val() + "/plat/content/add";
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/comp_menu.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			title: {
				required: true,
				maxlength: 64
			},
			orderNo: {
				required: true,
				maxlength: 2
			},
			description: {
				required: true,
				maxlength: 250
			}
		},
		messages: {
			title: {
				required: "请输入标题",
				maxlength: jQuery.format("标题不能大于{0}个字符")
			},
			orderNo: {
				required: "请输入顺序",
				maxlength: jQuery.format("顺序不能大于{0}个字符")
			}
		}
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#menuCode").val(res.data[0].code);
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/plat/comp_menu.htm";
	}else{
		alert(res.msg);
	}
}

function selectImagePicture1(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('picture1').src = evt.target.result;
		picture1Img = evt.target.result;
		$("#picture1Img").show();
		$("#picture1Img").attr("src",picture1Img);
	}
	reader.readAsDataURL(file.files[0]);
}
function selectImagePicture2(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('picture2').src = evt.target.result;
		picture2Img = evt.target.result;
		$("#picture2Img").show();
		$("#picture2Img").attr("src",picture2Img);
	}
	reader.readAsDataURL(file.files[0]);
}