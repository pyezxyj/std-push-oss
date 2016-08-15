var image = null;
var dictCompany = null;
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
		$("#operContent").text("修改职工");
		var data = {"code":code};
		var url = $("#basePath").val()+"/web/worker/list";
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
		data["picture"]=image;
		//3、发送请求
		var url = $("#basePath").val() + "/web/worker/"+$("#operate").val();
		doPostAjax(url,data,doSuccessSubBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/worker.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 64
			},
			position: {
				required: true,
				maxlength: 128
			},
			picture: {
				required: false,
				maxlength: 64
			},
			description: {
				required: true,
				maxlength: 255
			},
			remark: {
				required: true,
				maxlength: 64
			},
			companyCode: {
				required: true
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
		document.getElementById('picture').src = evt.target.result;
		image = evt.target.result;
		$("#previewPic").show();
		$("#previewPic").attr("src",image);
	}
	reader.readAsDataURL(file.files[0]);
}

function doSuccessSubBack(res){
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/web/worker.htm";
	}else{
		alert(res.msg);
	}
}

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			var data = res.data[0];
			
			$("#name").val(data.name);
			$("#position").val(data.position);
			image = data.picture;
			$("#previewPic").attr("src",image);
			ue.addListener("ready", function () {
				// editor准备好之后才可以使用
				ue.setContent(data.description);
			});
			$("#status").val(data.status);
			$("#remark").val(data.remark);
		}else{
			alert("根据职工编号编号获取详情失败");
		}
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