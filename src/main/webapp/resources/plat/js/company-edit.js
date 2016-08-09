var addrImg = null;
var logoImg = null;
$(function() {
	var userid = getQueryString('userid');
	//新增修改判断
	if(isBlank(userid)){
		$("#operate").val("add");
	}else{
		$("#userid").val(userid);
		$("#operate").val("edit");
		$("#operContent").text("修改公司");
		var data = {"userid":userid};
		var url = $("#basePath").val()+"/plat/company/detailuserid";
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
		data["barCode"]=barCodeImg;
		data["logo"]=logoImg;
		var url = $("#basePath").val() + "/plat/company/edit";
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/company_detail2.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			code: {
				required: true,
				maxlength: 30
			},
			name: {
				required: true,
				maxlength: 60
			},
			corporation: {
				required: true,
				maxlength: 60
			},
			telephone: {
				required: true,
				maxlength: 15
			},
			fax: {
				required: true,
				maxlength: 15
			},
			slogan: {
				maxlength: 255
			},
			email: {
				required: true,
				maxlength: 30
			},
			corporation: {
				required: true,
				maxlength: 60
			},
			address: {
				required: true,
				maxlength: 250
			},
			description: {
				required: true,
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
			},
			url: {
				required: true,
				maxlength: 255
			},
			copyright: {
				required: true,
				maxlength: 255
			}
		},
		messages: {
			code: {
				required: "请输入公司编码",
				maxlength: jQuery.format("公司编码不能大于{0}个字符")
			},
			userid: {
				required: "请选择",
			},
			name: {
				required: "请输入公司名称",
				maxlength: jQuery.format("公司名称不能大于{0}个字符")
			},
			slogan: {
				required: "请输入广告语",
				maxlength: jQuery.format("广告语不能大于{0}个字符")
			},
			corporation: {
				required: "请输入公司法人",
				maxlength: jQuery.format("公司法人不能大于{0}个字符")
			},
			telephone: {
				required: "请输入公司座机",
				maxlength: jQuery.format("公司座机不能大于{0}个字符")
			},
			fax: {
				required: "请输入公司传真",
				maxlength: jQuery.format("公司传真不能大于{0}个字符")
			},
			email: {
				required: "请输入公司邮箱",
				maxlength: jQuery.format("公司邮箱不能大于{0}个字符")
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
			},
			longitude: {
				required: "请输入公司经度",
				maxlength: jQuery.format("公司经度不能大于{0}个字符")
			},
			latitude: {
				required: "请输入公司纬度",
				maxlength: jQuery.format("公司纬度不能大于{0}个字符")
			},
			copyright: {
				required: "请输入copyright",
				maxlength: jQuery.format("copyright不能大于{0}个字符")
			}
		}
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
			$("#code").val(res.data.code);
			$("#name").val(res.data.name);
			$("#userid").val(res.data.userid);
			$("#corporation").val(res.data.corporation);
			$("#telephone").val(res.data.telephone);
			$("#fax").val(res.data.fax);
			$("#email").val(res.data.email);
			$("#url").val(res.data.url);
			$("#address").val(res.data.address);
			$("#copyright").val(res.data.copyright);
			$("#slogan").val(res.data.slogan);
			$("#longitude").val(res.data.longitude);
			$("#latitude").val(res.data.latitude);
			$("#description").val(res.data.description);
			logoImg = res.data.logo;
			$("#logoImg").attr("src",logoImg);
			barCodeImg = res.data.barCode;
			$("#barCodeImg").attr("src",barCodeImg);
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/plat/company_detail2.htm";
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