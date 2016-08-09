SYJDictCache = {};
Dict = {};
Dict.findName = function(data, key, k, v) {
	k = k || 'value';
	v = v || 'remark';
	data = data || [];
	var i = 0, len = data.length, res;
	for (; i < len; i++) {
		var item = data[i];
		if (item[k] == key) {
			res = item[v];
			break;
		}
	}
	return res;
};
Dict.findObj = function(data, key, k) {
	k = k || 'value';
	data = data || [];
	var i = 0, len = data.length, res;
	for (; i < len; i++) {
		var item = data[i];
		if (item[k] == key) {
			res = item;
			break;
		}
	}
	return res;
};

Dict.getName = function(type, key) {
	if (!SYJDictCache[type]) {
		doGetAjaxIsAsync($("#dictUrl").val(), {"key": type}, false, function(res) {
			SYJDictCache[type] = res.data;
		});
	}
	return key ? (Dict.findName(SYJDictCache[type], key) || '-') : SYJDictCache[type];
}

Dict.getNameForList = function(type) {
	if (!SYJDictCache[type]) {
		doGetAjaxIsAsync($("#dictUrl").val(), {"key": type}, false, function(res) {
			SYJDictCache[type] = res.data;
		});
	}
	return function(key) {
		return key ? Dict.findName(SYJDictCache[type], key) : '-';
	}
}

$.fn.renderDropdown = function(data, keyName, valueName, defaultOption) {
	data = data || [];
	keyName = keyName || 'value';
	valueName = valueName || 'remark';
	var html = "<option value=''>请选择</option>" + (defaultOption || '');
	for(var i = 0;i < data.length;i++){
		html += "<option value='"+data[i][keyName]+"'>"+data[i][valueName]+"</option>";
	}
	this.html(html);
	this.val('');
};