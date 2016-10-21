/**
 * 日期格式转化
 * @param date
 * @param format
 * @returns
 */
function dateFormat(date, format) {
	if(date == '' || typeof(date) == 'undefined'){
		return '-';
	}
	if(format == '' || format == null || format == undefined){
		format = "yyyy-MM-dd HH:mm:ss";
	}
	
    date = new Date(date);
    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)){
         format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in o){
        if (new RegExp('(' + k + ')').test(format)){
        	format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
        }
    }
    return format;
}

function dateTimeFormat(date) {
	if(date == '' || typeof(date) == 'undefined'){
		return '-';
	}
	format = "yyyy-MM-dd HH:mm:ss";
    date = new Date(date);
    var o = {
        'M+' : date.getMonth() + 1, //month
        'd+' : date.getDate(), //day
        'H+' : date.getHours(), //hour
        'm+' : date.getMinutes(), //minute
        's+' : date.getSeconds(), //second
        'q+' : Math.floor((date.getMonth() + 3) / 3), //quarter
        'S' : date.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format)){
         format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in o){
        if (new RegExp('(' + k + ')').test(format)){
        	format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
        }
    }
    return format;
}

/**
 * 金额格式转化
 * @param money
 * @param format
 */
/**
 * 废弃
function moneyFormat(money,format){
	if(isNaN(money)){
		return '';
	}
	if(format == '' || format == null || format == undefined){
		format = 2;
	}
	return parseFloat(money/1000).toFixed(format);
} */

/**
 * 金额格式转化
 * @param money
 * @param format
 */
function moneyFormat(money,format){
	var flag = true;
	if(isNaN(money)){
		return '0.00';
	}
	if(money < 0){
		money = -1 * money;
		flag = false;
	}
	if(format == '' || format == null || format == undefined || typeof format == 'object'){
		format = 2;
	}
	//钱除以1000并保留两位小数
	//money = (money/1000).toFixed(format).toString();
	money = (money/1000).toString();
	money = money.replace(/(\.\d\d)\d+/ig,"$1");
	money = parseFloat(money).toFixed(format);
	//千分位转化
	var re=/\d{1,3}(?=(\d{3})+$)/g;
	money = money.replace(/^(\d+)((\.\d+)?)$/,function(s,s1,s2){return s1.replace(re,"$&,")+s2;});
	if(!flag){
		money = "-" + money;	
	}
	return money;
}

function moneyParse(money, rate) {
	rate = rate || 1000;
	return ((+('' + money).replace(/,/g, '')) * rate).toFixed(0);
}

/**
 * 编辑金额格式转化
 * @param money
 * @param format
 */
function editMoneyFormat(money,format){
	if(isNaN(money)){
		return '';
	}
	if(format == '' || format == null || format == undefined){
		format = 2;
	}
	return parseFloat(money/1000).toFixed(format);
}

/**
 * 百分比格式转化
 * @param percent
 * @param format
 */
function percentFormat(percent, format){
	return percent;
	/*if(isNaN(percent)){
		return '';
	}
	if(format == '' || format == null || format == undefined){
		format = 5;
	}
	return parseFloat(percent).toFixed(format);
	*/
}


/**
 * 百分比格式转化
 * @param percent
 * @param format
 * @returns
 */
function percentFormatByLarge(percent, format){
	if(isNaN(percent)){
		return '';
	}
	if(format == '' || format == null || format == undefined){
		format = 3;
	}
	return parseFloat(percent*10000).toFixed(format);
}

/**
 * 验证数字
 * @param amount
 * @param name
 * @returns {Boolean}
 */
function checkNum(amount,name){
	var reg = new RegExp("^[0-9]+(.[0-9]+)?$");
	if(!reg.test(amount)) { 
		alert(name+"只能输入数字！"); 
		return false; 
	}
	return true;
}

/**
 * 申请编号去除特殊字符('-'与' ')
 * @param rbNo
 */
function rbNoFormat(rbNo){
	return rbNo.replace(/-| /g, "")
}

/**
 * 金额放大，乘于1000，格式化
 * @param money
 * @param format
 */
function moneyFormatByEnLarge(money, format){
	if(isNaN(money)){
		return '';
	}
	return parseFloat(money*1000).toFixed(format);
}
/**
 * 利率缩小100倍
 */
function RateFormatByHundredDivided(rate){
	if(isNaN(rate)){
		return '';
	}
	return parseFloat(rate/100.0).toFixed(5);
}
/**
 * 利率放大100倍
 */
function RateFormatByLargeHundred(rate){
	if(isNaN(rate)){
		return '';
	}
	return parseFloat(rate*100.0).toFixed(2);
}
/**
 * 判断参数是否为空
 * @param paramValue
 */
function isBlank(param){
	var flag = false;
	if(param == "" || param == null || param == undefined){
		flag = true;
	}
	return flag;
}

/**
 * 处理错误信息
 * @param msg
 */
function dealErrorMsg(msg){
	//alert(msg);
	if(msg == "登录链接已超时，请重新登录."){
		top.location.href = $("#basePath").val()+"/security/signin.htm"
	}
}

/**
 * 显示遮罩
 */
function maskPop(){
	var maskDiv = '<div class="mask-pop"></div>';
	$('body').append(maskDiv);
	$('.mask-pop').show();
}

/**
 * 隐藏遮罩
 */
function unMaskPop(){
	var maskDiv = '<div class="mask-pop"></div>';
	$('body').append(maskDiv);
	$('.mask-pop').hide();
}

/**
 * 通过正则表达式获取URL传递参数
 * @param name
 * @returns
 */
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return null;
}

/**
 * 通过正则表达式获取按钮菜单URL传递参数
 * @param url
 * @param name
 * @returns
 */
function getMenuUrl(url,name) {
	if(isBlank(url)){
		return null;
	}
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = url.substr(url.lastIndexOf("/")+1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

/**
 * jquery URL传递 暂时没有
 * @param key
 * @returns
 */
function getUrlParam(key) {
	var json = {}, data;
	$.each(location.search.substr(1).split("&"), function(i, n) {
		data = n.split("=");
		json[data[0]] = data[1];
	});
	return key != undefined ? json[key] : json;
}

/**
 * 按钮权限控制
 */
function showPermissionControl(){
	//获取menuCode
	var url = $("#basePath").val() + "/menu/list";
	var webUrl = window.location.pathname;
	var menuUrl = webUrl.substring($("#basePath").val().length);
	var data = {"url":menuUrl};
	//var data = {"url":menuUrl,"kind":getCurrentKind()};
	//doGetAjaxIsAsync(url, data, false, doGetMenuCode);
	
	//直接从url获取menuCode，二级页面返回，权限控制不了
    var pUrl = $("#basePath").val() + "/role/menuList";
    if (window.parent.frames[1]) {
    	var pData = {"parentCode":$('.left-menu .active', window.parent.frames[1].document).attr('id'),"type":"2"};
    	doGetAjaxIsAsync(pUrl, pData, false, doSuccessBackPermission);
    }
}

//获取菜单编号回执方法
function doGetMenuCode(res){
	if(res.success == true && !isBlank(res.data)){
		$("#permissionCode").val(res.data[0].code);
	}else{
		//alert("获取菜单编号失败,权限不受控制!");
	}
}

//控制按钮呈现方式回执方法
function doSuccessBackPermission(res){
	var data = res.data || [];
	$('.tools .toolbar').empty();
	for(var i = 0;i < data.length;i++){
		var menuUrl = data[i].url;
		menuUrl = menuUrl.substr(menuUrl.lastIndexOf("/")+1);
		//$("#"+menuUrl+"Btn").show();
		$('.tools .toolbar').append('<li style="display:block;" id="'+menuUrl+'Btn"><span><img src="'+$('#resourceUrl').val()+'/resources/common/images/t01.png"/></span>'+data[i].name+'</li>');
	}
}
function myShowModalDialog(url, width, height, fn) {
    if (navigator.userAgent.indexOf("Chrome") > 0) {
        window.returnCallBackValue354865588 = fn;
        var paramsChrome = 'height=' + height + ', width=' + width + ', top=' + (((window.screen.height - height) / 2) - 50) +
            ',left=' + ((window.screen.width - width) / 2) + ',toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no';
        window.open(url, "newwindow", paramsChrome);
    }
    else {
        var params = 'dialogWidth:' + width + 'px;dialogHeight:' + height + 'px;status:no;dialogLeft:'
                    + ((window.screen.width - width) / 2) + 'px;dialogTop:' + (((window.screen.height - height) / 2) - 50) + 'px;';
        var tempReturnValue = window.showModalDialog(url, "", params);
        fn.call(window, tempReturnValue);
    }
}
function myReturnValue(value) {
    if (navigator.userAgent.indexOf("Chrome") > 0) {
        window.opener.returnCallBackValue354865588.call(window.opener, value);
    }
    else {
        window.returnValue = value;
    }
}

function openWindow(url,name,iWidth,iHeight)
{
	 var url;                                 //转向网页的地址;
	 var name;                           //网页名称，可为空;
	 var iWidth;                          //弹出窗口的宽度;
	 var iHeight;                        //弹出窗口的高度;
	 var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
	 var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
	 window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}

// 扩展方法
$.fn.serializeObject = function() {
	 var o = {};
	 var a = this.serializeArray();
	 $.each(a, function() {
		 if (o[this.name] !== undefined) {
			 if (!o[this.name].push) {
				 o[this.name] = [o[this.name]];
			 }
			 o[this.name].push(this.value || '');
		 } else {
			 var value = this.value || '';
			 if ($('#' + this.name).parent('li').attr('type') == 'amount') {
				 value = moneyParse(value);
			 }
			 o[this.name] = value;
		 }
	 });
	 return o;
};

$.fn.renderDropdown = function(data, keyName, valueName, defaultOption) {
	var value, url;
	if ($.isPlainObject(data)) {
		value = data.value;
		url = data.url;
		param = data.param || {};
		keyName = data.keyName;
		valueName = data.valueName;
		defaultOption = data.defaultOption;
	}
	if(url) {
		ajaxGet(url, param, false, true).then(function(res) {
			data.data = res.data;
		});
	}
	data = (data.data && data.data.list) || data.data || data || [];
	keyName = keyName || 'dkey';
	valueName = valueName || 'dvalue';
	var html = "<option value=''>请选择</option>" + (defaultOption || '');
	for(var i = 0;i < data.length;i++){
		html += "<option value='"+data[i][keyName]+"'>"+data[i][valueName]+"</option>";
	}
	this.html(html);
	if (value) {
		this.val(value);
	}
	return data;
};

$.fn.renderDropdown2 = function(data, defaultOption) {
	var html = "<option value=''>请选择</option>" + (defaultOption || '');
	for (var k in data) {
		html += "<option value='"+k+"'>"+data[k]+"</option>";
	}
	this.html(html);
};

function renderLink(link, name) {
	return '<a href="'+link+'" target="_blank">'+name+'</a>';
}

function renderA(el, link) {
	if (!link) {
		return;
	}
	var values = link.split('/');
	el.attr('href', link);
	el.html(values[values.length - 1]);
}

// array

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}

Array.prototype.each = function(fn){  
    fn = fn || Function.K;  
     var a = [];  
     var args = Array.prototype.slice.call(arguments, 1);  
     for(var i = 0; i < this.length; i++){  
         var res = fn.apply(this,[this[i],i].concat(args));  
         if(res != null) a.push(res);  
     }  
     return a;  
};  

Array.prototype.uniquelize = function(){  
    var ra = new Array();  
    for(var i = 0; i < this.length; i ++){  
        if(!ra.contains(this[i])){  
           ra.push(this[i]);  
        }  
    }  
    return ra;  
};  

Array.complement = function(a, b){  
    return Array.minus(Array.union(a, b),Array.intersect(a, b));  
};  

Array.intersect = function(a, b){  
    return a.uniquelize().each(function(o){return b.contains(o) ? o : null});  
};  

Array.minus = function(a, b){  
    return a.uniquelize().each(function(o){return b.contains(o) ? null : o});  
}; 

Array.union = function(a, b){  
    return a.concat(b).uniquelize();  
};  

//面包屑
setTimeout(function() {
	var topTitle = $('.nav .selected h2', window.parent.frames[0].document).text();
	var leftFirstTitle = $('.left-menu .active', window.parent.frames[1].document).parent().parent().find('.title').text();
	var leftSecondTitle = $('.left-menu .active', window.parent.frames[1].document).text();
	var html = '<li>'+topTitle+'</li><li>'+leftFirstTitle+'</li><li>'+leftSecondTitle+'</li>';
	var BtnTitle = localStorage.getItem('syj-btn');
	localStorage.setItem('syj-btn', '');
	if (BtnTitle) {
		html += '<li>'+BtnTitle+'</li>';
	}
	$('.place ul').html(html);
}, 100);

$(document).on('click', '.toolbar li[id*=Btn]', function(e) {
	var text = $(this).text();
	localStorage.setItem('syj-btn', text);
});

//资源链接

function linkSrc(value) {
	if (!value) {
		return '-';
	}
	var values = value.split('/');
	return '<a target="_blank" href="'+value+'">'+values[values.length - 1]+'</a>';
}

function getUserId() {
	return $('#topUserId', window.parent.frames[0].document).val();
}

//下拉框
setTimeout(function() {
	$('select').chosen && $('select').not('.norender').chosen({search_contains: true});
}, 100);
var oriVal = $.fn.val;
$.fn.val = function(value) {
	var res = oriVal.apply($(this), arguments);
	if ($(this).is('select')) {
		$(this).trigger('chosen:updated');
	}
	return res;
}

$(document).on('click', 'input[type=reset]', function() {
	var me = this;
	setTimeout(function() {
		$(me).closest('.search-form').find('select').trigger('chosen:updated');
	}, 100);
});

var oriHtml = $.fn.html;
$.fn.html = function(value) {
	var res = oriHtml.apply($(this), arguments);
	if ($(this).is('select')) {
		$(this).trigger('chosen:updated');
	}
	return res;
}

// 压缩图片
function zipImg(file, pos) {
	if (file.type != 'image/jpeg') {
		var reader = new FileReader();
		reader.onload = function(evt){
			var image = evt.target.result;
			$(pos).attr("src",image);
		}
		reader.readAsDataURL(file);
	} else {
		var mpImg = new MegaPixImage(file);
		mpImg.render(pos, {quality: 0.5});
	}
}


//后退
function goBack() {
 if ('referrer' in document) {
     window.location = document.referrer;
     /* OR */
     //location.replace(document.referrer);
 } else {
     window.history.back();
 }
}

function getAccountId(userId, currency) {
	var res1;
	ajaxGet($('#basePath').val() + '/account/id', {
		userId: userId,
		currency: currency,
	}, false, true).then(function(res) {
		res1 = res.data.accountNumber
	});
	return res1;
}

function getCompany(userId) {
	var res1;
	ajaxGet($('#basePath').val() + '/general/company/list', {
		userId: userId
	}, false, true).then(function(res) {
		res1 = res.data.length > 0 ? res.data[0]: '';
	});
	return res1;
}

function getCompanyId(userId) {
	var res1;
	ajaxGet($('#basePath').val() + '/general/company/list', {
		userId: userId
	}, false, true).then(function(res) {
		res1 = res.data.length > 0 ? res.data[0].code: '';
	});
	return res1;
}

function objectArrayFilter(arr, keys) {
	keys = keys.split(',');
	var newArr = [];
	arr.forEach(function(item) {
		if (keys.indexOf(item.dkey) > -1) {
			newArr.push(item);
		}
	});
	return newArr;
}

function buildList(router, columns, options) {
	options = options || {};
	var html = '<ul>';
	var dropDownList = [];
	var urlParams = options.urlParams;
	var urlParamsStr = '';
	if (urlParams) {
		for (var i in urlParams) {
			urlParamsStr += '&' + i + '=' + urlParams[i];
		}
	}
	for (var i = 0, len = columns.length; i < len; i++) {
		var item = columns[i];
		if (item.amount) {
			item.formatter = moneyFormat;
		}
		if (item.search) {
			if (item.key || item.type == 'select') {
				html += '<li><label>'+item.title+'</label><select id="'+item.field+'" name="'+item.field+'"></select></li>';
			} else if (item.type == 'date') {
				
			} else {
				html += '<li><label>'+item.title+'</label><input id="'+item.field+'" name="'+item.field+'" type="text"/></li>';
			}
		}
		
		if (item.key || item.type == 'select') {
			dropDownList.push(item);
		}
		
		
	}
	html += '<li><input id="searchBtn" type="button" class="btn" value="查询" /><input type="reset" class="btn" value="重置" /></li></ul>';
	$('.search-form').append(html);
	
	for (var i = 0, len = dropDownList.length; i < len; i++) {
		var item = dropDownList[i];
		if (item.data) {
			var data = item.data;
			$('#' + item.field).renderDropdown2(data);
			(function(d){
				item.formatter = function(v) {
					return d[v] || d[+v];
				};
			})(data);
			
		}
		else if (item.key) {
			$('#' + item.field).renderDropdown(Dict.getName(item.key));
		} else if (item.url) {
			var data = $('#' + item.field).renderDropdown($.extend({
				url: item.url,
				keyName: item.keyName,
				valueName: item.valueName
			}, (item.defaultOption ? {defaultOption: '<option value="0">'+item.defaultOption+'</option>'} : {})));
			var dataDict = {};
			if (item.defaultOption) {
				dataDict['0'] = item.defaultOption;
			}
			for (var j = 0, len1 = data.length; j < len1; j++) {
				dataDict[data[j][item.keyName]] = data[j][item.valueName];
			}
			
			item.formatter = function(v) {
				return dataDict[v];
			};
		}
	}
	
	$('#searchBtn').click(function() {
		$('#tableList').bootstrapTable('refresh',{url: $('#tableList').bootstrapTable('getOptions').url});
	});
	
	$('#addBtn').click(function() {
		window.location.href = $("#basePath").val()+ (options.pageRouter || router) + "_addedit.htm?-=-" + urlParamsStr;
	});
	
	$('#editBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+ (options.pageRouter || router) + "_addedit.htm?code="+(selRecords[0].code || selRecords[0].id) + urlParamsStr;
	});
	
	$('#deleteBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		
		if(!confirm("确认是否删除该记录？")){
    		return false;
    	}
    	var url = $("#basePath").val()+ router + '/delete';
    	var data = {code:selRecords[0].code};
		ajaxPost(url, data).then(function(res) {
			if (res.success) {
				alert('操作成功');
				$('#tableList').bootstrapTable('refresh',{url: $('#tableList').bootstrapTable('getOptions').url});
			} else {
				alert(res.msg);
			}
		});
	});
	
	$('#detailBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		location.href = $("#basePath").val() + (options.pageRouter || router) + "_addedit.htm?v=1&code=" + (selRecords[0].code || selRecords[0].id) + urlParamsStr;
	});
	
	$('#checkBtn').click(function() {
		var selRecords = $('#tableList').bootstrapTable('getSelections');
		if(selRecords.length <= 0){
			alert("请选择记录");
			return;
		}
		window.location.href = $("#basePath").val()+ (options.pageRouter || router) + "_check.htm?code="+ (selRecords[0].code || selRecords[0].id) + urlParamsStr;
	});

	$('#tableList').bootstrapTable({
		method : "get",
		url : $("#basePath").val() + router + '/page',
		striped : true,
		clickToSelect : true,
		singleSelect : true,
		queryParams : function(params) {
			return $.extend({
				start : params.offset / params.limit + 1,
				limit : params.limit
			}, $('.search-form').serializeObject(), options.searchParams);
		},
		queryParamsType : 'limit',
		responseHandler : function(res) {
			return {
				rows : res.data.list,
				total : res.data.totalCount
			};
		},
		pagination : true,
		sidePagination : 'server',
		totalRows : 0,
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		columns : columns
	});
}

function selectImage(file,name){
	setTimeout(function() {
		$(file).valid();
	}, 10);
	if(!file.files || !file.files[0]){
		name.src = '';
		return;
	}
	zipImg(file.files[0], document.getElementById(name) || name);
}

function buildDetail(router, fields, code, options) {
	options = options || {};
	var title = $('.left-menu .active a', window.parent.frames[1].document).html();
	$('#page-title').html(title);
	var html = '<input type="hidden" id="code" name="code" class="control-def" />';
	var dropDownList = [], rules = {}, textareaList = [];
	for (var i = 0, len = fields.length; i < len; i++) {
		var item = fields[i];
		rules[item.field] = {};
		if (item.type == 'img') {
			rules[item.field + 'Img'] = {};
			rules[item.field + 'Img'].required = item.required;
		}
		if (item.required) {
			
			rules[item.field].required = item.required;
		}
		
		if (item.maxlength) {
			rules[item.field].maxlength = item.maxlength;
		}
		
		if (item.number) {
			rules[item.field].number = item.number;
		}
		
		if (item.email) {
			rules[item.field].email = item.email;
		}
		
		if (item.min) {
			rules[item.field].min = item.min;
		}
		
		if (item.max) {
			rules[item.field].max = item.max;
		}
		
		if (item['Z+']) {
			rules[item.field]['Z+'] = item['Z+'];
		}
		
		if (item['amount']) {
			rules[item.field]['amount'] = item['amount'];
		}
		
		if (code && item.readonly) {
			html += '<li type="'+(item.amount ? 'amount' : '')+'"><label>'+item.title+':</label><span id="'+item.field+'" name="'+item.field+'"></span></li>';
		} else {
			html += '<li type="'+(item.amount ? 'amount' : '')+'" style="'+(item.hidden ? 'display: none' : '')+'"><label>'+(item.title ? ('<b>'+ ((item.required && '*') || '') +'</b>'+item.title+':') : '')+'</label>';
			if (item.type == 'hidden') {
				html = '<input type="hidden" id="'+item.field+'" name="'+item.field+'"/>' + html;
			} else if (item.type == 'radio') {
				for (var k = 0, len1 = item.items.length; k < len1; k++) {
					var rd = item.items[k];
					html += '<input type="radio" id="radio'+k+'" name="'+item.field+'" value="'+rd.key+'"><label title="'+(rd.value || '')+'" for="radio'+k+'" class="radio-text"><i class="zmdi '+(rd.icon || '')+' zmdi-hc-5x"></i></label>';
				}
				html += '</li>';
			} else if (item.type == 'select') {
				dropDownList.push(item);
				html += '<select id="'+item.field+'" name="'+item.field+'" class="control-def"></select></li>';
			} else if (item.type == 'img') {
				html += '<div class="btn-file"><span>选择图片</span>' +
			    	'<input type="file" tabindex="1" id="'+item.field+'Img" name="'+item.field+'Img" onchange="selectImage(this,'+item.field+');" />' +
			    	'</div><img src="" id="'+item.field+'" /></li>';
			} else if (item.type == 'textarea') {
				textareaList.push({field: item.field});
				html += '<textarea id="'+item.field+'" name="'+item.field+'" style="width:800px;height:250px;float:left"></textarea></li>';
			} else if (item.type == 'citySelect') {
				html += '<div id="city-group"><select id="province" name="province" class="control-def prov"></select>' +
				    '<select id="city" name="city" class="control-def city"></select>' +
				    '<select id="area" name="area" class="control-def dist"></select></div></li>';
				if (item.required) {
					rules.province = {required: true};
					rules.city = {required: true};
					rules.area = {required: true};
				}
				
			} else {
				html += '<input id="'+item.field+'" name="'+item.field+'" class="control-def" '+(item.placeholder ? ('placeholder="'+item.placeholder+'"') : '')+'/></li>';
			}
		}
		
		
		
	}
	var btnHandlers = [];
	if (options.buttons) {
		var btnHtml = '<li>';
		
		for (var i = 0, len = options.buttons.length; i < len; i++) {
			var item = options.buttons[i];
			var id = 'btn-' + i;
			btnHandlers.push({id: id, handler: item.handler});
			btnHtml += '<input id="'+id+'" type="button" class="btn margin-left-20" value="'+item.title+'"/>';
		}
		btnHtml += '</li>';
		html += btnHtml;
	} else {
		html += '<li><input id="subBtn" type="button" class="btn margin-left-100" value="保存"/><input id="backBtn" type="button" class="btn margin-left-20" value="返回"/></li>';
	}
	
	$('.form-info').append(html);
	
	for (var i = 0, len = btnHandlers.length; i < len; i++) {
		$('#' + btnHandlers[i].id).on('click', btnHandlers[i].handler);
	}
	
	$('#backBtn').click(function() {
		goBack();
	});
	$('#subBtn').click(function() {
		if ($('#jsForm').valid()) {
			var data = $('#jsForm').serializeObject();
			$('#jsForm').find('input[type=file]').parent().next().each(function(i, el) {
				data[el.id] = $(el).attr('src');
			});
			if ($('#jsForm').find('#province')[0]) {
				var province = $('#province').val();
				var city = $('#city').val();
				var area = $('#area').val();
				if (!city) {
					data['city'] = province;
					data['area'] = province;
				} else if (!area) {
					data['city'] = province;
					data['area'] = city;
				} 
			}
			for (var i = 0, len = fields.length; i < len; i++) {
				var item = fields[i];
				if (item.equal && (!$('#' + item.field).is(':hidden') || !$('#' + item.field + 'Img').is(':hidden'))) {
					data[item.equal] = $('#' + item.field).val() || $('#' + item.field).attr('src');
				}
			}
			var url = $("#basePath").val()+ router + "/" + (code ? 'edit' : 'add');
			ajaxPost(url, data).then(function(res) {
				if (res.success) {
					alert("操作成功");
					goBack();
				}
			});
		}
	});
	$("#jsForm").validate({'rules': rules});
	
	for (var i = 0, len = dropDownList.length; i < len; i++) {
		var item = dropDownList[i];
		if (item.data) {
			$('#' + item.field).renderDropdown2(item.data);
		}
		else if (item.key) {
			$('#' + item.field).renderDropdown(Dict.getName(item.key));
		} else if (item.url) {
			$('#' + item.field).renderDropdown($.extend({
				url: item.url,
				keyName: item.keyName,
				valueName: item.valueName
			}, (item.defaultOption ? {defaultOption: '<option value="0">'+item.defaultOption+'</option>'} : {})));
		}
		if (item.onChange) {
			(function(i) {
				$('#' + i.field).on('change', function(e) {
					i.onChange(this.value);
				});
			})(item);
			
		}
	}
	
	for (var i = 0, len = textareaList.length; i < len; i++) {
		var item = textareaList[i];
		UE.getEditor(item.field);
	}
	
	$("#city-group").citySelect && $("#city-group").citySelect({
		required:false
	}); 
	
	for (var i = 0, len = fields.length; i < len; i++) {
		var item = fields[i];
		if ('defaultValue' in item) {
			$('#' + item.field).val(item.defaultValue);
		}
		
	}
	
	
	if (!code) {
		for (var i = 0, len = fields.length; i < len; i++) {
			var item = fields[i];
			if ('value' in item && !item.value.call) {
				$('#' + item.field).val(item.value);
			}
			
		}
	}
	
	
	if (code) {
		doGetAjax($("#basePath").val()+ router + "/detail", {
			code: code
		}, function(res) {
			if (res.success) {
				var data = res.data;
				$('#code').val(data.code || data.id);
				for (var i = 0, len = fields.length; i < len; i++) {
					var item = fields[i];
					var value = item.value;
					if (item.readonly) {
						if (item.type == 'select' && item.data) {
							var realValue = data[item.field];
							if (item.value) {
								if (item.value.call) {
									realValue = item.value(data);
								} else {
									realValue = item.value;
								}
							}
							$('#' + item.field).html(item.data[realValue]);
						}
						else if (item.type == 'select' && !item.url) {
							var realValue = data[item.field];
							if (item.value) {
								if (item.value.call) {
									realValue = item.value(data);
								} else {
									realValue = item.value;
								}
							}
							$('#' + item.field).html(Dict.getName(item.key, realValue));
							
							if (item.onChange) {
								item.onChange(realValue);
							}
						} else if (item.type == 'radio') {
							var selectOne = '';
							for (var k = 0, len1 = item.items.length; k < len1; k++) {
								if (item.items[k].key == data[item.field]) {
									selectOne = item.items[k];
									break;
								}
							}
							$('#' + item.field).html('<div class="zmdi '+selectOne.icon+' zmdi-hc-5x" title="'+selectOne.value+'"></div>');
						} else if (item.type == 'select' && item.url) {
							var params = {};
							params[item.keyName] = data[item.field];
							if (!data[item.field]) {
								$('#' + item.field).html('-');
							} else if (data[item.field] == 0) {
								$('#' + item.field).html(item.defaultOption);
							} else {
								(function(i) {
									ajaxGet(i.url, params).then(function(res) {
										var data = (res.data && res.data.list && res.data.list[0]) || res.data[0] || res.data;
										$('#' + i.field).html(data[i.valueName] || i.defaultOption);
									});
								})(item);
							}
							
							
						} else if (item.type == 'img') {
							var realValue = data[item['[value]']] || data[item.field] || '';
							$('#' + item.field).html(realValue.indexOf('http://') > -1 ? '<img src="'+realValue+'" style="max-width: 300px;"></img>' : '-');
						} else {
							if (item.field in data) {
								$('#' + item.field).html((item.amount ? moneyFormat(data[item.field]) : data[item.field]));
							} else {
								$('#' + item.field).html('-');
							}
							
						}
						if (item.formatter) {
							$('#' + item.field).html(item.formatter(data[item.field], data));
						}
						if (item['[value]']) {
							if (item.type == 'img') {
								var realValue = data[item['[value]']] || data[item.field] || '';
								if (realValue.indexOf('http://') > -1) {
									$('#' + item.field).attr('src', realValue);
								}
							} else {
								$('#' + item.field).html(item.amount ? moneyFormat(data[item['[value]']]) : data[item['[value]']]);
							}
							
						}
					} else {
						if (item.type == 'img') {
							var realValue = data[item['[value]']] || data[item.field] || '';
							if (realValue.indexOf('http://') > -1) {
								$('#' + item.field).attr('src', realValue);
							}
						} else if (item.type == 'radio') {
							$('input[name='+item.field+'][value='+data[item.field]+']').prop('checked', true);
						} else if (item.type == 'textarea') {
							(function(f) {
								UE.getEditor(f).ready(function() {
									UE.getEditor(f).setContent(data[f]);
								});
							})(item.field);
						} else if (item.type == 'citySelect') {
							if (data.province == data.city && data.city == data.area) {
								
							} else if (data.province == data.city && data.city != data.area) {
								data.city = data.area;
							}
							$('#province').val(data.province);
							$('#province').trigger('change');
							$('#city').val(data.city);
							$('#city').trigger('change');
							$('#area').val(data.area);
						} else {
							$('#' + item.field).val(item.amount ? moneyFormat(data[item.field]) : data[item.field]);
						}
					}
					
					if ('value' in item) {
						if (item.value.call) {
							$('#' + item.field).val(item.value(data));
						} else {
							$('#' + item.field).val(item.amount ? moneyFormat(item.value): item.value);
						}
						
					}
					
					if (item['[value]']) {
						if (item.type == 'img') {
							var realValue = data[item['[value]']] || data[item.field] || '';
							if (realValue.indexOf('http://') > -1) {
								$('#' + item.field).attr('src', realValue);
							}
						} else {
							$('#' + item.field).val(item.amount ? moneyFormat(data[item['[value]']]) : data[item['[value]']]);
						}
						
					}
					
					if (item.type == 'select') {
						$('#' + item.field).trigger('change');
					}
					
				}
			}
		});
	}
}