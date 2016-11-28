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
@RequestMapping(value = "/merchant/memQuery")
public class MemQueryController extends BaseController {
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageUser(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("805054", JsonUtils.mapToJson(map),Object.class);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/freeze-up", method = RequestMethod.POST)
	@ResponseBody
	public Object freezeUpUser(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		map.put("toStatus", "2");
		return BizConnecter.getBizData("805052", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/freeze-down", method = RequestMethod.POST)
	@ResponseBody
	public Object freezeDownUser(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		map.put("toStatus", "0");
		return BizConnecter.getBizData("805052", JsonUtils.mapToJson(map),Object.class);
	}
}
