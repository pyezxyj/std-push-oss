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
import com.xnjr.app.util.UploadUtil;

@Controller
@RequestMapping(value = "/merchant/order")
public class OrderController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/refund", method = RequestMethod.POST)
	@ResponseBody
	public Object refunOrder(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("808056", JsonUtils.mapToJson(map),Object.class);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageOrder(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808070", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object listProduct(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808071", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Object detailOrder(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808072", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/logistics", method = RequestMethod.POST)
	@ResponseBody
	public Object logisticsOrder(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("orderCode", "logisticsCode");
		map.put("updater", this.getSessionUser().getUserName());
		map.put("pdf", UploadUtil.uploadPicture((String) map.get("pdf")));
		return BizConnecter.getBizData("808054", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/site", method = RequestMethod.POST)
	@ResponseBody
	public Object siteOrder(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("808055", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sure", method = RequestMethod.POST)
	@ResponseBody
	public Object sureOrder(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("808057", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/abnormal", method = RequestMethod.POST)
	@ResponseBody
	public Object abnormalOrder(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("808056", JsonUtils.mapToJson(map),Object.class);
	}

}
