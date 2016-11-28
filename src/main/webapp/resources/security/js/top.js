$(function(){
	doGetAjaxIsAsync($("#basePath").val() + '/user', {}, false, function(res) {
		if (res.timeout) {
			location.href = $("#basePath").val() + '/security/signin.htm';
		}
		window.ossKind = res.data ? res.data.kind : '';
	});
	
	doGetAjaxIsAsync($("#basePath").val() + '/menu/list', {"parentCode":"SM201600000000000000"}, false, function(res) {
		window.parentCode = res.data[0] ? res.data[0].code : '';
	});
	
	var data={"parentCode":window.parentCode,"type":"1","isGetChild":"false"};
	doGetAjax($("#basePath").val()+"/user/roleMenu/list", data, doSuccessMenuBack);
	
	$("#logout").click(function(){
		doGetAjax($("#basePath").val()+"/user/logout", {}, function(res) {
			if (res.success) {
				location.href = $("#basePath").val() + '/security/signin.htm';
			}
		});
	})
	function check() {
		function getCompanyId(userId) {
			var res1;
			ajaxGet($('#basePath').val() + '/general/company/list', {
				userId: userId,
				location: '1'
			}, false, true).then(function(res) {
				res1 = res.data.length > 0 ? res.data[0].code: '0';
			});
			return res1;
		}
		var userId = $('#topUserId').val();
		var companyId = getCompanyId(userId);
		if (companyId == '0' && userId != 'U201600000000000000') {
			alert('商户信息未初始化，请联系管理员');
			$('body .nav').remove();
			$('body', parent.frames["leftFrame"].document).html('');
			$('body', parent.frames["rightFrame"].document).html('');
		}
	}
	check();
});

function doSuccessMenuBack(res){
	var firstMenuCode = null;
	if (res.success == true) {
		$.each(res.data, function(i, item) {
			if(i == 0){
				firstMenuCode = item.code;
			}
            $(".nav").append("<li><a id=\"menu"+i+"\" href=\"javascript:void(0)\" onclick=\"initLefMenu('"+item.code+"');return false;\" target=\"leftFrame\"><img src=\""+$("#basePath").val()+"/resources/security/images/icon01.png\" title=\""+item.name + "\" /><h2>"+item.name+"</h2></a></li>");
        });
		initLefMenu(firstMenuCode);
		$(".nav").find('a:first').addClass('selected');
		
		//顶部导航切换
		$(".nav li a").click(function(){
			$(".nav li a.selected").removeClass("selected")
			$(this).addClass("selected");
		});
	}else{
		dealErrorMsg(res.msg);
	}
}
		
		

//重新加载左侧页面
function initLefMenu(parentCode){
	parent.frames["leftFrame"].document.getElementById('parentCode').value = parentCode;
	parent.frames["leftFrame"].window.initMenu();
}

function mainReload(){
	window.parent.location.reload(true);
}