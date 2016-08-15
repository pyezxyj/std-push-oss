var templetCode = null;
var contentType = null;

$(function() {
	
	var isCus = getQueryString('isCus');
	if (isCus) {
		$('#contentCtn').hide();
	}
	
	var url = $("#basePath").val() + "/plat/menu/list";
    doGetAjaxIsAsync(url, null, false, doSuccessBackCode);
	
	
	
	var code = getQueryString('code');
	//新增修改判断
	if(isBlank(code)){
		$("#operate").val("add");
		templetCode = "002";
		contentType = "list";
		$('#content-type-1').addClass('active');
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改菜单");
		var data = {"code":code};
		var url = $("#basePath").val()+"/plat/menu/list";
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
		data['templetCode'] = templetCode;
		data['contentType'] = contentType;
		var url = $("#basePath").val() + "/plat/menu/"+$("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	
	
	//返回
	$('#backBtn').click(function() {
		window.history.back();
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			name: {
				required: true,
				maxlength: 15
			},
			orderNo: {
				required: true,
				maxlength: 2
			}
		}
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			templetCode = res.data[0].templetCode;
			contentType = res.data[0].contentType;
			$("#name").val(res.data[0].name);
			$("#parentCode").val(res.data[0].parentCode);
			$("#orderNo").val(res.data[0].orderNo);
			if (res.data[0].templetCode == '001') {
				$('#content-type-2').addClass('active');
			} else {
				$('#content-type-1').addClass('active');
			}
		}else{
			alert("根据菜单编号添加修改失败");
		}
	}else{
		alert(res.msg);
	}
}



function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.history.back();
	}else{
		alert(res.msg);
	}
}


var dictCode = null;

function doSuccessBackCode(res){
	dictCode = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictCode) != "undefined"){//判断undifined
		for(var i = 0;i < dictCode.length;i++){
			html += "<option value='"+dictCode[i].code+"'>"+dictCode[i].name+"</option>";
		}
	}
	$("#parentCode").html(html);
}

//类型转化
function cFormatter(value, row) {
	for(var i = 0;i < dictCode.length;i++){
		if(dictCode[i].value == value){
			return dictCode[i].name;
		}
	}
}




function clickPic(value,me) {
	$(me).parent().find('img').removeClass('active');
	$(me).addClass('active');
	if(value == 1){
		templetCode = "002";
		contentType = "list";
	}else if(value == 2){
		templetCode = "001";
		contentType = "ele";
	}
}