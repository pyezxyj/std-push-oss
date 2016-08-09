var addrImg = null;
var logoImg = null;
$(function() {
	var code = getQueryString('code');
	
	doGetAjaxIsAsync($("#basePath").val()+"/user/list", {}, false, function(res) {
		var data = res.data || [], html = "<option value=''>请选择</option>";
		for (var i = 0, len = data.length; i < len; i++) {
			html += "<option value='"+data[i].userCode+"'>"+data[i].userName+"</option>";
			$("#userid").html(html);
		}
	});
	
	//新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改公司");
		var data = {"code":code};
		var url = $("#basePath").val()+"/plat/company/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交
	$('#subBtn').click(function() {
	    if(!$("#jsForm").valid()){
			return false;
		}
	    
	    if (!$('#logoImg').attr('src') ) {
			alert('请上传图片');
			return;
		}
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data["barCode"]=barCodeImg;
		data["logo"]=logoImg;
		var url = $("#basePath").val() + "/plat/company/"+$("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/company.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			code: {
				required: true,
				maxlength: 30
			},
			userid: {
				required: true,
			},
			copyright: {
				required: true,
				maxlength: 255
			},
			name: {
				required: true,
				maxlength: 30
			},
			corporation: {
				required: true,
				maxlength: 15
			},
			telephone: {
				required: true,
				maxlength: 15
			},
			fax: {
				required: true,
				maxlength: 15
			},
			email: {
				required: true,
				maxlength: 30
			},
			url: {
				required: true,
				maxlength: 64
			},
			slogan: {
				required: true,
				maxlength: 255
			},
			address: {
				required: true,
				maxlength: 255
			},
			description: {
				required: true,
				maxlength: 255
			},
			longitude: {
				required: true,
				number: true,
				maxlength: 30
			},
			latitude: {
				required: true,
				number: true,
				maxlength: 30
			}
		},
		messages: {
			code: {
				required: "请输入公司编码",
				maxlength: jQuery.format("公司名称不能大于{0}个字符")
			},
			userid: {
				required: "请选择",
			},
			copyright: {
				maxlength: jQuery.format("copyright不能大于{0}个字符")
			},
			name: {
				required: "请输入公司名称",
				maxlength: jQuery.format("公司名称不能大于{0}个字符")
			},
			corporation: {
				required: "请输入公司法人",
				maxlength: jQuery.format("公司法人不能大于{0}个字符")
			},
			telephone: {
				required: "请输入公司座机",
				maxlength: jQuery.format("公司座机不能大于{0}个字符")
			},
			slogan: {
				required: "请输入广告语",
				maxlength: jQuery.format("广告语不能大于{0}个字符")
			},
			fax: {
				required: "请输入公司传真",
				maxlength: jQuery.format("公司传真不能大于{0}个字符")
			},
			url: {
				required: "请输入域名",
				maxlength: jQuery.format("域名不能大于{0}个字符")
			},
			address: {
				required: "请输入公司地址",
				maxlength: jQuery.format("公司地址不能大于{0}个字符")
			},
			description: {
				required: "请输入公司简介",
				maxlength: jQuery.format("公司简介不能大于{0}个字符")
			},
			longitude: {
				required: "请输入公司经度",
				maxlength: jQuery.format("公司经度不能大于{0}个字符")
			},
			latitude: {
				required: "请输入公司纬度",
				maxlength: jQuery.format("公司纬度不能大于{0}个字符")
			}
		}
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#name").val(res.data[0].name);
			$("#corporation").val(res.data[0].corporation);
			$("#userid").val(res.data[0].userid);
			$("#slogan").val(res.data[0].slogan);
			$("#telephone").val(res.data[0].telephone);
			$("#longitude").val(res.data[0].longitude);
			$("#latitude").val(res.data[0].latitude);
			$("#copyright").val(res.data[0].copyright);
			$("#fax").val(res.data[0].fax);
			$("#email").val(res.data[0].email);
			$("#url").val(res.data[0].url);
			logoImg = res.data[0].logo;
			$("#logoImg").attr("src",logoImg);
			$("#address").val(res.data[0].address);
			barCodeImg = res.data[0].barCode;
			$("#barCodeImg").attr("src",barCodeImg);
			$("#description").val(res.data[0].description);
		}else{
			alert("根据公司编号添加修改失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/plat/company.htm";
	}else{
		alert(res.msg);
	}
}
function selectImageBarCode(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('barCode').src = evt.target.result;
		barCodeImg = evt.target.result;
		$("#barCodeImg").show();
		$("#barCodeImg").attr("src",barCodeImg);
	}
	reader.readAsDataURL(file.files[0]);
}
function selectImageLogo(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('logo').src = evt.target.result;
		logoImg = evt.target.result;
		$("#logoImg").show();
		$("#logoImg").attr("src",logoImg);
	}
	reader.readAsDataURL(file.files[0]);
}