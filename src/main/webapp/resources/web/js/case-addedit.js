var logoImg = null;
var pictureImg = null;
var ue = UE.getEditor('description');
$(function() {
	
	//数据初始化
	initData();
	$("#companyCode").val(getCompanyId());
	var code = getQueryString('code');
    //	新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改信息");
		var data = {"code":code};
		var url = $("#basePath").val()+"/web/case/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交
	$('#subBtn').click(function() {
		//1、数据长度校验
		if(!$("#jsForm").valid()){
			return false;
		}
		if (!$('#logoImg').attr('src') || !$('#pictureImg').attr('src')) {
			alert('请上传图片或logo');
			return;
		}
		//2、组装数据
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data["logo"]=logoImg;
		data["picture"]=pictureImg;
		//3、发送请求
		var url = $("#basePath").val() + "/web/case/"+$("#operate").val();
		doPostAjax(url,data,doSuccessSubBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/case.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 32
			},
			url: {
				required: true,
				maxlength: 64
			},
			description: {
				required: true
			},
			status: {
				required: true
			}
			
		}
	});
});

function initData(){
	  // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
    //状态
    $('#status').renderDropdown(Dict.getName('case_status'));
}

function doSuccessBackCompany(res){
	dictCompany = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictCompany) != "undefined"){//判断undifined
		for(var i = 0;i < dictCompany.length;i++){
			html += "<option value='"+dictCompany[i].code+"'>"+dictCompany[i].name+"</option>";
		}
	}
	$("#companyCode").html(html);
}

function companyFormatter(value, row) {
	for(var i = 0;i < dictCompany.length;i++){
		if(dictCompany[i].code == value){
			return dictCompany[i].name;
		}
	}
}

$('#status').renderDropdown(Dict.getName('case_status'));

function doSuccessSubBack(res){
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/web/case.htm";
	}else{
		alert(res.msg);
	}
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			console.log(res.data);
			$("#name").val(res.data[0].name);
			$("#url").val(res.data[0].url);
			$("#status").val(res.data[0].status);
			ue.addListener("ready", function () {
				// editor准备好之后才可以使用
				ue.setContent(res.data[0].description);
			});
			logoImg = res.data[0].logo;
			pictureImg = res.data[0].picture;
			$("#logoImg").attr('src', logoImg);
			$("#pictureImg").attr('src', pictureImg);
		}else{
			alert("根据案例信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function selectLogoImage(file){
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

function selectPictureImage(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('picture').src = evt.target.result;
		pictureImg = evt.target.result;
		$("#pictureImg").show();
		$("#pictureImg").attr("src",pictureImg);
	}
	reader.readAsDataURL(file.files[0]);
}