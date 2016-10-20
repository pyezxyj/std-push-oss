package com.xnjr.app.general.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.general.ao.IDictAO;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;

@Controller
@RequestMapping(value = "/general/dict")
public class DictController extends BaseController {
    @Autowired
    protected IDictAO dictAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addDict(@RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("809000", JsonUtils.mapToJson(map),
              Object.class);
	}

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public Object dropDict(@RequestBody Map map) {
  		return BizConnecter.getBizData("809001", JsonUtils.mapToJson(map),
              Object.class);
	}

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editDict(@RequestBody Map map) {
    	map.put("id", map.get("code"));
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("809002", JsonUtils.mapToJson(map),
              Object.class);
	}

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDictPage(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("809005", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDictList(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("809006", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDictDetail(@RequestParam Map<String,String> map) {
    	map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("809007", JsonUtils.mapToJson(map),
              Object.class);
    }
}
