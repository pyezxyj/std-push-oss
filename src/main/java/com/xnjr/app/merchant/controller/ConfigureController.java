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
@RequestMapping(value = "/merchant/configure")
public class ConfigureController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editConfigure(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("id", map.get("code"));
		return BizConnecter.getBizData("808910", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageConfigure(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808915", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Object detailConfigure(@RequestParam Map<String,String> map)
	{
		map.put("id",map.get("code"));
		return BizConnecter.getBizData("808916", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/ckey", method = RequestMethod.GET)
	@ResponseBody
	public Object ckeyConfigure(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808917", JsonUtils.mapToJson(map),Object.class);
	}

}
