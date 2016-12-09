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
@RequestMapping(value = "/tpl")
public class TemplateController extends BaseController {
	

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object page(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804061", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804062", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam Map<String,String> map) {
    	map.put("id", map.get("code"));
  	    return BizConnecter.getBizData("804063", JsonUtils.mapToJson(map),
              Object.class);
    }
}
