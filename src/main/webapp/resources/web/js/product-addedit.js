var image = null;
var ue = UE.getEditor('description');
$(function() {
	var code = getQueryString('code');
	initData();
	$("#companyCode").val(getCompanyId());
    //	新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改信息");
		var data = {"code":code};
		var url = $("#basePath").val()+"/web/product/list";
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
		var url = $("#basePath").val() + "/web/product/"+$("#operate").val();
		doPostAjax(url,data,doSuccessSaveBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/web/product.htm";
	});
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 64
			},
			kind: {
				required: true,
				maxlength: 64
			},
			status: {
				required: true,
				maxlength: 1
			},
			description: {
				required: true,
				maxlength: 255
			},
			companyCode: {
				required: true,
			}
		}
	});
});

function initData(){
	// 新闻类型
    var data ={"key":"product_kind"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackKind);
    
    var data ={"key":"product_status"};
    doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSuccessBackStatus);
    
    // 公司列表
    var url = $("#basePath").val() + "/plat/company/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCompany);
}

function doSuccessBackKind(res){
	dictKind = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictKind) != "undefined"){//判断undifined
		for(var i = 0;i < dictKind.length;i++){
			html += "<option value='"+dictKind[i].value+"'>"+dictKind[i].remark+"</option>";
		}
	}
	$("#kind").html(html);
}

function doSuccessBackStatus(res){
	dictStatus = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictStatus) != "undefined"){//判断undifined
		for(var i = 0;i < dictStatus.length;i++){
			html += "<option value='"+dictStatus[i].value+"'>"+dictStatus[i].remark+"</option>";
		}
	}
	$("#status").html(html);
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

function doSuccessSaveBack(res){
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/web/product.htm";
	}else{
		alert(res.msg);
	}
}
function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			
			$("#name").val(res.data[0].name);
			$("#kind").val(res.data[0].kind);
			$("#status").val(res.data[0].status);
			image = res.data[0].picture;
			$("#previewPic").attr("src",image);
			ue.addListener("ready", function () {
				// editor准备好之后才可以使用
				ue.setContent(res.data[0].description);
			});
		}else{
			alert("根据产品信息编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}