//页面初始化
$(function(){
	var id = getQueryString('id');
	//新增修改判断
	if(isBlank(id)){
		$("#operate").val("add");
	}else{
		$("#operate").val("edit");
		$("#operContent").text("修改系统参数");

		var data = {"id":id,"type":"2"};
		var url = $("#basePath").val()+"/general/dict/list";
		doGetAjax(url, data, doGetDetailBack);
		document.getElementById("key").setAttribute("readOnly",'true');  
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
		data["type"]="2";
		data["pId"]="-1";
		var url = $("#basePath").val()+"/general/dict/" + $("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/general/sys_param.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			key: {
				required: true,
				maxlength: 32
			},
			value: {
				required: true,
				maxlength: 32
			},
			remark: {
				required: true,
				maxlength: 64
			}
		},
		messages: {
			key: {
				required: "请输入参数键",
				maxlength: jQuery.format("参数键不能大于{0}个字符")
			},
			value: {
				required: "请输入参数值",
				maxlength:jQuery.format("参数值不能大于{0}个字符")
			},
			remark: {
				required: "请输入参数说明",
				maxlength:jQuery.format("参数说明不能大于{0}个字符")
			}
		}
	});
});

function doGetDetailBack(res){
	
	if (res.success == true) {
		if(res.data.length > 0){
			var result = res.data[0];
			$("#id").val(result.id);
			$("#pId").val(result.pId);
			$("#key").val(result.key);
			$("#value").val(result.value);
			$("#remark").val(result.remark);
		}else{
			alert("根据编号获取详情失败");
		}
	}else{
		alert(res.msg);
	}
}

function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		window.location.href = $("#basePath").val()+"/general/sys_param.htm";
	}else{
		alert(res.msg);
	}
}