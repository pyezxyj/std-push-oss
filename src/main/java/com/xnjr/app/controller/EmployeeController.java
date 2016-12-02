package com.xnjr.app.controller;

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
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("804050", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("804051", JsonUtils.mapToJson(map),
              Object.class);
	}

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(@RequestBody Map map) {
  		return BizConnecter.getBizData("804052", JsonUtils.mapToJson(map),
              Object.class);
	}

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object page(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804055", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804056", JsonUtils.mapToJson(map),
              Object.class);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("804057", JsonUtils.mapToJson(map),
              Object.class);
    }
}
