$(function() {
	// 父菜单下拉框初始化
	var url = $("#basePath").val() + "/menu/list";
	//同步调用
	doGetAjaxIsAsync(url, null,false, doSuccessPCodeBack);
	
	var menuCode = getQueryString('menuCode');
	//新增修改判断
	if(isBlank(menuCode)){
		$("#operate").val("add");
	}else{
		$("#menuCode").attr("readonly","readonly");
		$("#operate").val("edit");
		$("#operContent").text("修改菜单");
		var data = {"menuCode":menuCode};
		var url = $("#basePath").val()+"/menu/list";
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
		var url = $("#basePath").val()+"/menu/" + $("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/security/menu.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			menuCode: {
				required: true,
				number:true,
				maxlength: 32
			},
			menuName: {
				required: true,
				maxlength: 32
			},
			type: {
				required: true,
				maxlength: 4
			},
			menuUrl: {
				required: true,
				maxlength: 64
			},
			orderNo: {
				required: true,
				number:true,
				maxlength: 8
			},
			remark: {
				maxlength: 64
			}
		}
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#menuCode").val(res.data[0].menuCode);
			$("#menuName").val(res.data[0].menuName);
			$("#menuUrl").val(res.data[0].menuUrl);
			$("#parentCode").val(res.data[0].parentCode);
			$("#type").val(res.data[0].type);
			$("#orderNo").val(res.data[0].orderNo);
			$("#remark").val(res.data[0].remark);
		}else{
			alert("根据菜单编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

//父菜单下拉框回置方法
function doSuccessPCodeBack(res){
	if (res.success == true) {
		var data = res.data;
		var html = "<option value=''>请选择</option>";
		if(typeof(data) != "undefined"){//判断undifined
			for(var i = 0;i < data.length;i++){
				if(data[i].menuCode == $("#parentCode").val()){
					html += "<option selected='selected' value='"+data[i].menuCode+"'>"+data[i].menuCode +" " +data[i].menuName +"</option>";
				}else{
					html += "<option value='"+data[i].menuCode+"'>"+data[i].menuCode +" " +data[i].menuName +"</option>";
				}
			}
		}
		$("#parentCode").html(html);
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/security/menu.htm";
	}else{
		alert(res.msg);
	}
}