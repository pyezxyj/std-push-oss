$(function() {
	//获取数据字典
	var data = {"key":"role_level"};
	doGetAjaxIsAsync($("#dictUrl").val(), data,false, doSucBackLevel);
	
	//获取菜单URL入参
	var roleCode = getQueryString("roleCode");
	//新增修改判断
	if(isBlank(roleCode)){
		$("#operate").val("add");
	}else{
		$("#roleCode").attr("readonly","readonly");
		$("#operate").val("edit");
		$("#operContent").text("修改角色");
		var data = {"roleCode":roleCode};
		var url = $("#basePath").val()+"/role/list";
		doGetAjax(url, data, doSucBackGetDetail);
	}
	
	//提交
	$('#subBtn').click(function() {
	    if(!$("#jsForm").valid()){
	    	return;
	    }
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		var operator = $("#operate").val() != "edit"?"add":"edit";
		var url = $("#basePath").val()+"/role/" + operator;
		doPostAjax(url, data, doSucBackSave);
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			roleCode: {
				required: true,
				number:true,
				maxlength: 32
			},
			roleName: {
				required: true,
				maxlength: 32
			},
			roleLevel: "required",
			remark: {
				maxlength: 100
			}
		},
		messages: {
			roleCode: {
				required: "请输入角色编号",
				number: "角色编号请输入数字",
				maxlength: jQuery.format("角色编号不能大于{0}个字符")
			},
			roleName: {
				required: "请输入角色名称",
				maxlength: jQuery.format("角色名称不能大于{0}个字符")
			},
			roleLevel: "请选择角色等级",
			remark: {
				maxlength: jQuery.format("备注不能大于{0}个字符")
			}
		}
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/security/role.htm";
	});
});

//下拉框初始化数据
function doSucBackLevel(res){
	var data = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(data) != "undefined"){//判断undifined
		for(var i = 0;i < data.length;i++){
			if(data[i].key == $("#roleLevel").val()){
				html += "<option selected='selected' value='"+data[i].value+"'>"+data[i].remark+"</option>";
			}else{
				html += "<option value='"+data[i].value+"'>"+data[i].remark+"</option>";
			}
		}
	}
	$("#roleLevel").html(html);
}

//获取详情回调方法
function doSucBackGetDetail(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#roleCode").val(res.data[0].roleCode);
			$("#roleName").val(res.data[0].roleName);
			$("#roleLevel").val(res.data[0].roleLevel);
			$("#remark").val(res.data[0].remark);
		}else{
			alert("根据角色编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

//保存回调方法
function doSucBackSave(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/security/role.htm";
	}else{
		alert(res.msg);
	}
}