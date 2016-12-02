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
@RequestMapping(value = "/channel")
public class ChannelController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody Map map) {
  	    return BizConnecter.getBizData("804000", JsonUtils.mapToJson(map),
              Object.class);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(@RequestBody Map map) {
		map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("804001", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object page(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804005", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804006", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam Map<String,String> map) {
    	map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("804007", JsonUtils.mapToJson(map),
              Object.class);
    }
}
