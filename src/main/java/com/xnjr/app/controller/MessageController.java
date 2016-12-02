package com.xnjr.app.controller;

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
@RequestMapping(value = "/message")
public class MessageController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Object send(@RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  	    return BizConnecter.getBizData("804030", JsonUtils.mapToJson(map),
              Object.class);
    }
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object page(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804040", JsonUtils.mapToJson(map),
              Object.class);
    }
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam Map<String,String> map) {
    	map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("804042", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/wx/page", method = RequestMethod.GET)
    @ResponseBody
    public Object wxpage(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804040", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/wx/list", method = RequestMethod.GET)
    @ResponseBody
    public Object wxlist(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804041", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/wx/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object wxdetail(@RequestParam Map<String,String> map) {
    	map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("804042", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 微信推送
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/wx/send", method = RequestMethod.POST)
    @ResponseBody
    public Object wxsend(@RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  	    return BizConnecter.getBizData("804033", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 微信模板
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/wx/tpl", method = RequestMethod.GET)
    @ResponseBody
    public Object wxtpl(@RequestParam Map map) {
  	    return BizConnecter.getBizData("804060", JsonUtils.mapToJson(map),
              Object.class);
    }
}
