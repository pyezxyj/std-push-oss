var image = null;
var ue = UE.getEditor('description');
$(function() {
	var code = getQueryString('code');
	initData();
	$("#companyCode").val(getCompanyId());
	//新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改合作伙伴");
		var data = {"code":code};
		var url = $("#basePath").val()+"/web/partner/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交(保存)
	$('#subBtn').click(function() {
		
	    if(!$("#jsForm").valid()){
			return false;
		}
	    
	    if (!$('#previewPic').attr('src')) {
			alert('请上传图片');
			return;
		}
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data["logo"]=image;
		var url = $("#basePath").val()+"/web/partner/" + $("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/partner.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 64
			},
			description: {
				required: true
			},
			url: {
				required: true,
				maxlength: 64
			}
		}
	});
});
function selectImage(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('logo').src = evt.target.result;
		image = evt.target.result;
		$("#previewPic").show();
		$("#previewPic").attr("src",image);
	}
	reader.readAsDataURL(file.files[0]);
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			var data = res.data[0];
			$("#code").val(data.code);
			$("#name").val(data.name);
			$("#url").val(data.url);
			image = data.logo;
			$("#previewPic").attr("src",image);
//			$("#description").val(data.description);
			ue.addListener("ready", function () {
				// editor准备好之后才可以使用
				ue.setContent(data.description);
			});
		}else{
			alert("根据合作伙伴编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/web/partner.htm?status=0";
	}else{
		alert(res.msg);
	}
}
function initData(){
	 // 公司列表
  var url = $("#basePath").val() + "/plat/company/list";
  doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
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