SYJDictCache = {};
Dict = {};
Dict.findName = function(data, key, k, v) {
	k = k || 'dkey';
	v = v || 'dvalue';
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
	k = k || 'dkey';
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
		doGetAjaxIsAsync($("#dictUrl").val(), {"parentKey": type}, false, function(res) {
			SYJDictCache[type] = res.data;
		});
	}
	return key ? (Dict.findName(SYJDictCache[type], key) || '-') : SYJDictCache[type];
}

Dict.getNameForList = function(type) {
	if (!SYJDictCache[type]) {
		doGetAjaxIsAsync($("#dictUrl").val(), {"parentKey": type}, false, function(res) {
			SYJDictCache[type] = res.data;
		});
	}
	return function(key) {
		return key ? Dict.findName(SYJDictCache[type], key) : '-';
	}
}
Dict.getNameForList1 = function(type, systemCode) {
	if (!SYJDictCache[type]) {
		doGetAjaxIsAsync($("#dictUrl").val(), {"parentKey": type}, false, function(res) {
			if(res.success){
				for(var i = 0, result = []; i < res.data.length; i++){
					if(res.data[i].dkey == systemCode){
						result.push(res.data[i]);
						break;
					}
				}
				SYJDictCache[type] = result;
			}else{
				SYJDictCache[type] = [];
			}
		});
	}
	return function(key) {
		return key ? Dict.findName(SYJDictCache[type], key) : '-';
	}
}