$(function(){
	var data={"parentCode":"10000001","type":"1","isGetChild":false};
	doGetAjax($("#basePath").val()+"/user/roleMenu/list", data, doSuccessMenuBack);
});

function doSuccessMenuBack(res){
	var firstMenuCode = null;
	if (res.success == true) {
		$.each(res.data, function(i, item) {
			if(i == 0){
				firstMenuCode = item.menuCode;
			}
            $(".nav").append("<li><a id=\"menu"+i+"\" href=\"javascript:void(0)\" onclick=\"initLefMenu('"+item.menuCode+"');return false;\" target=\"leftFrame\"><img src=\""+$("#basePath").val()+"/resources/security/images/icon01.png\" title=\""+item.menuName + "\" /><h2>"+item.menuName+"</h2></a></li>");
        });
		initLefMenu(firstMenuCode);
		
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