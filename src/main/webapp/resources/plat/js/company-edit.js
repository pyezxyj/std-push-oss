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
				maxlength: 30
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
				required: true,
				maxlength: 250
			},
			email: {
				required: true,
				maxlength: 30
			},
			corporation: {
				required: true,
				maxlength: 15
			},
			address: {
				required: true,
				maxlength: 250
			},
			description: {
				required: true,
				maxlength: 250
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
				maxlength: 60
			},
			copyright: {
				required: true,
				maxlength: 255
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