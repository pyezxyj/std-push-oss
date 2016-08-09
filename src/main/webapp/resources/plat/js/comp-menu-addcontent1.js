var templetCode = null;
var contentType = null;
var code;
var menuCode;
var ue = UE.getEditor('description');
var picture1Img = null;
var picture2Img = null;

$(function() {
	initData();
	code = getQueryString('code');
	menuCode = getQueryString('menuCode');
	if(isBlank(code)){
		$("#operate").val("add");
	}else{
		$("#code").val(code);
		$("#operate").val("edit");
		$("#operContent").text("修改菜单");
		var data = {"code":code};
		var url = $("#basePath").val()+"/plat/content/list";
		doGetAjax(url, data, doGetDetailBack);
	}
	
	//提交
	$('#subBtn').click(function() {
	    if(!$("#jsForm").valid()){
			return false;
		}
	    
	    if (!$('#picture1Img').attr('src') ) {
			alert('请上传图片');
			return;
		}
	    
		var data = {};
		var t = $('form').serializeArray();
		$.each(t, function() {
			data[this.name] = this.value;
		});
		data['menuCode'] = menuCode;
		data["picture1"]=picture1Img;
		data["picture2"]=picture2Img;
		var value = $("#fileUrl").val();
		if(value){
			data['endNote'] = value;
		}else{
			data['endNote'] = $("#endNote1").val();
		}
		
		var url = $("#basePath").val() + "/plat/content/" +(code ? 'edit' : 'add');
		doPostAjax(url, data, doSuccessBack);
	});
	
	//返回
	$('#backBtn').click(function() {
		location.href = $("#basePath").val()+"/plat/comp_menu_addcontent.htm?code="+menuCode;
	});
	
	//入参合法性校验
	$("#jsForm").validate({
		rules: {
			title: {
				required: true,
				maxlength: 64
			},
			description: {
				required: true,
			},
			type: {
				required: true,
			}
		},
		messages: {
			title: {
				required: "请输入标题",
				maxlength: jQuery.format("标题不能大于{0}个字符")
			},
			description: {
				required: "请输入描述",
				maxlength: jQuery.format("描述不能大于{0}个字符")
			},
			type: {
				required: "请选择状态"
			}
		}
	});
	$("#sendFile").on("click", function(){
		ajaxFileUpload($("#basePath").val() + "/upload/file", "endNote");
	});
});

function doGetDetailBack(res){
	if (res.success == true) {
		if(res.data.length > 0){
			$("#menuCode").val(res.data[0].menuCode);
			$("#code").val(res.data[0].code);
			$("#title").val(res.data[0].title);
			$("#type").val(res.data[0].type);
			$("#endNote1").val(res.data[0].endNote);
			$("#fileUrl").val(res.data[0].endNote);
			picture1Img = res.data[0].picture1;
			$('#picture1Img').attr('src', picture1Img);
			picture2Img = res.data[0].picture2;
			$('#picture2Img').attr('src', picture2Img);
//			ue.setContent(res.data[0].description);
			ue.addListener("ready", function () {
				// editor准备好之后才可以使用
				ue.setContent(res.data[0].description);

			});
		}
	}else{
		alert(res.msg);
	}
}
function ajaxFileUpload(postUrl,fileId,uploadControlId) {
    $.ajaxFileUpload
    (
        {
            url: postUrl, //用于文件上传的服务器端请求地址
            type: 'POST',
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: fileId, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {
                if (typeof (data.status) != 'undefined') {
                    if (data.status == "1") {
                    	alert('上传成功');
                    	$("#fileUrl, #endNote1").val(data.url);
                    	if(!isBlank(uploadControlId)){
                    		$("#"+uploadControlId).text(data.url.substring(data.url.lastIndexOf('/')+1));
            		    	$("#"+uploadControlId).attr('href',data.url); 
                    	}
                    } else {
                        alert(data.msg);
                    }
                }
            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                alert(e);
            }
        }
    )
    return false;
}


function doSuccessBack(res) {
	if (res.success == true) {
		alert("操作成功");
		location.href = $("#basePath").val()+"/plat/comp_menu_addcontent.htm?code="+menuCode;
	}else{
		alert(res.msg);
	}
}

function initData(){
	//状态
	var data= {"key":"content_status"};
	doGetAjaxIsAsync($("#dictUrl").val(), data, false, doSucBackStatus);
	}

//数据字典（合同状态）关联的回执方法
function doSucBackStatus(res){
	dictContractStatus = res.data;
	var html = "<option value=''>请选择</option>";
	if(typeof(dictContractStatus) != "undefined"){//判断undifined
		for(var i = 0;i < dictContractStatus.length;i++){
			html += "<option value='"+dictContractStatus[i].value+"'>"+dictContractStatus[i].remark+"</option>";
		}
	}
	$("#type").html(html);
}
//状态列表格式化
function statusFormatter(value, row) {
	for(var i = 0;i < dictContractStatus.length;i++){
		if(dictContractStatus[i].value == value){
			return dictContractStatus[i].remark;
		}
	}
}

function selectImagePicture1(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('picture1').src = evt.target.result;
		picture1Img = evt.target.result;
		$("#picture1Img").show();
		$("#picture1Img").attr("src",picture1Img);
	}
	reader.readAsDataURL(file.files[0]);
}
function selectImagePicture2(file){
	if(!file.files || !file.files[0]){
		return;
	}
	var reader = new FileReader();
	reader.onload = function(evt){
		document.getElementById('picture2').src = evt.target.result;
		picture2Img = evt.target.result;
		$("#picture2Img").show();
		$("#picture2Img").attr("src",picture2Img);
	}
	reader.readAsDataURL(file.files[0]);
}