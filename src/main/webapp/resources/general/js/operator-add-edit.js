var introduction = UE.getEditor('introduction');
var image = '';
//页面初始化
var dictIdKind = null;
var dictLevel = null;
var dictStatus = null;
$(function(){
	//页面数据字典初始化
	initData();
	var userId = getQueryString("userId");
	//新增修改判断
	if(isBlank(userId)){
		$(".editShow").hide();
		$("#operate").val("add");
		$("#img").hide();
	}else{
		$("#editHide ").hide();
		$("#idKind").attr("disabled","disabled");
		$("#idNo").attr("disabled","disabled");
		$("#realName").attr("disabled","disabled");
		$("#operate").val("edit");
		$("#operContent").text("修改操盘手");
		var data = {"userId":userId};
		var url = $("#basePath").val()+"/general/operator/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交
	$('#subBtn').click(function() {
		if(isBlank(image)){
			alert("请上传头像");
			return;
		}
	    if(!$("#jsForm").valid()){
			return false;
		}
	    $("#userId").removeAttr("disabled");
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data["photo"]=image;
		var url = $("#basePath").val()+"/general/operator/" + $("#operate").val();
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/general/operator.htm";
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			userId: {
				required: true,
				isZipCode:true,
				maxlength: 32
			},
			companyId: {
				required: true,
				maxlength: 32
			},
			mobile: {
				required: true,
				mobile: true,
				maxlength: 16
			},
			idKind: {
				required: true,
			},
			idNo: {
				required: true,
				idCard: true,
				maxlength: 32
			},
			realName: {
				required: true,
				maxlength: 16
			},
			tradePwd: {
				required: true,
				maxlength: 32
			},
			introduction: {
				required: true,
				maxlength: 255
			},
			remark: {
				required: false,
				maxlength: 255
			}
		},
		messages: {
			userId: {
				required: "请输入用户编号",
				maxlength: jQuery.format("用户编号不能大于{0}个字符")
			},
			companyId: {
				required: "请输入公司编号",
				maxlength: jQuery.format("公司编号不能大于{0}个字符")
			},
			mobile: {
				required: "请输入手机号",
				mobile: "手机号格式有误",
				maxlength: jQuery.format("手机号不能大于{0}个字符")
			},
			idKind: {
				required: "请选择证件类型"
			},
			idNo: {
				required: "请输入证件号",
				idCard: "证件号格式有误",
				maxlength: jQuery.format("证件号不能大于{0}个字符")
			},
			realName: {
				required: "请输入真实姓名",
				maxlength: jQuery.format("真实姓名不能大于{0}个字符")
			},
			tradePwd: {
				required: "请输入交易密码",
				maxlength: jQuery.format("交易密码不能大于{0}个字符")
			},
			introduction: {
				required: "请输入简介",
				maxlength: jQuery.format("简介不能大于{0}个字符")
			},
			remark: {
				maxlength: jQuery.format("备注不能大于{0}个字符")
			}
		}
	});
});
function initData(){
	//证件类型
	var data= {"key":"id_kind"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackKind);
	
	var data= {"key":"operator_level"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackLevel);
	
	var data= {"key":"operator_status"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackStatus);
	
	doGetAjaxIsAsync($("#basePath").val()+"/user/list", null, false, doSucBackUser);
}

//数据字典（用户）关联的回执方法
function doSucBackUser(res){
	secUser = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(secUser) != "undefined"){//判断undifined
		for(var i = 0;i < secUser.length;i++){
			html += "<option value='"+secUser[i].userCode+"'>"+secUser[i].userCode+" "+secUser[i].userName+"</option>";
		}
	}
	$("#userId").html(html);
}


//数据字典（合同状态）关联的回执方法
function doSucBackKind(res){
	dictIdKind = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictIdKind) != "undefined"){//判断undifined
		for(var i = 0;i < dictIdKind.length;i++){
			html += "<option value='"+dictIdKind[i].value+"'>"+dictIdKind[i].remark+"</option>";
		}
	}
	$("#idKind").html(html);
}
//数据字典（合同状态）关联的回执方法
function doSucBackLevel(res){
	dictLevel = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictLevel) != "undefined"){//判断undifined
		for(var i = 0;i < dictLevel.length;i++){
			html += "<option value='"+dictLevel[i].value+"'>"+dictLevel[i].remark+"</option>";
		}
	}
	$("#level").html(html);
}
//数据字典（合同状态）关联的回执方法
function doSucBackStatus(res){
	dictStatus = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictStatus) != "undefined"){//判断undifined
		for(var i = 0;i < dictStatus.length;i++){
			html += "<option value='"+dictStatus[i].value+"'>"+dictStatus[i].remark+"</option>";
		}
	}
	$("#status").html(html);
}
function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			var result = res.data[0];
			$("#userId").val(result.userId);
			$("#userId").attr("disabled","disabled");
			$("#companyId").val(result.companyId);
			$("#mobile").val(result.mobile);
			$("#level").val(result.level);
			$("#status").val(result.status);
			$("#idKind").val(result.idKind);
			$("#idNo").val(result.idNo);
			$("#realName").val(result.realName);
			image = result.photo;
			$("#tradePwd").val(result.tradePwd);
			$("#remark").val(result.remark);
			$("#img").attr('src',result.photo);
			
			introduction.ready(function(){
			    //需要ready后执行，否则可能报错
				introduction.setContent(result.introduction);
		    });
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
		window.location.href = $("#basePath").val()+"/general/operator.htm";
	}else{
		alert(res.msg);
	}
}
//图片上传转化
function selectImage(file,name){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('photo').src = evt.target.result;
		image = evt.target.result;
		$("#img").show();
		$("#img").attr("src",image);
	}
	reader.readAsDataURL(file.files[0]);
}
