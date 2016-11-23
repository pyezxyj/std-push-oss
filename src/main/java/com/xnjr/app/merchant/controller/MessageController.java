package com.xnjr.app.merchant.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;

@Controller
@RequestMapping(value = "/merchant/message")
public class MessageController extends BaseController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addMessage(@RequestBody Map<String, String> map) {
    	map.put("updater", this.getSessionUser().getUserName());
    	map.put("companyCode","0");
  		return BizConnecter.getBizData("805120", JsonUtils.mapToJson(map),
              Object.class);
	}

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object dropDict(@SuppressWarnings("rawtypes") @RequestBody Map map) {
  		return BizConnecter.getBizData("805121", JsonUtils.mapToJson(map),
              Object.class);
	}

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editDict(@SuppressWarnings("rawtypes") @RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("805122", JsonUtils.mapToJson(map),
              Object.class);
	}

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDictPage(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("805130", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDictList(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("805131", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object queryDictDetail(@RequestParam Map<String,String> map) {
    	map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("805132", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @ResponseBody
    public Object releaseMessage(@RequestBody Map<String, String> map) {
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("805120", JsonUtils.mapToJson(map),
              Object.class);
	}
}
