var image = null;
var ue = UE.getEditor('description');
$(function() {
	initData();
	$("#companyCode").val(getCompanyId());
	var code = getQueryString('code');
    //	新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		var data = {"code":code};
		var url = $("#basePath").val()+"/web/index/detail";
		doGetAjax(url, data, doGetDetailBack);
	}
	//提交(保存)
	$('#subBtn').click(function() {
		//1、数据长度校验
		if(!$("#jsForm").valid()){
			return false;
		}
		//2、组装数据
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data["companyCode"] = getCompanyId();
		data["banner1"]=$('#previewPic1').attr('src');
		data["banner2"]=$('#previewPic2').attr('src');
		data["banner3"]=$('#previewPic3').attr('src');
		data["fullSizePic"]=$('#previewPic4').attr('src');
		data['urlText'] = ue.getContent();
		//3、发送请求
		var url = $("#basePath").val() + "/web/index/"+$("#operate").val();
		doPostAjax(url,data,doSuccessSubBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		window.history.back();
	});
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			url: {
				maxlength: 64
			}
		}
	});
});

function initData(){
}

function selectImage(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		image = evt.target.result;
		$(file).next().attr("src",image);

	}
	reader.readAsDataURL(file.files[0]);
}

function doSuccessSubBack(res){
	if (res.success == true) {
		alert("操作成功");
		window.history.back();
	}else{
		alert(res.msg);
	}
}
function doGetDetailBack(res){
	if (res.success) {
		var data = res.data;
		$("#url").val(data.url);
		image = data.picture;
		data.banner1 && $("#previewPic1").attr("src",data.banner1);
		data.banner2 && $("#previewPic2").attr("src",data.banner2);
		data.banner3 && $("#previewPic3").attr("src",data.banner3);
		data.fullSizePic && $("#previewPic4").attr("src",data.fullSizePic);
		ue.addListener("ready", function () {
			// editor准备好之后才可以使用
			ue.setContent(data.urlText);
		});
		$("#remark").val(data.remark);
	}else{
		alert(res.msg);
	}
}