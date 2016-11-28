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
@RequestMapping(value = "/merchant/ticket")
public class TicketController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addTicket(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("useTimes", "1");
		map.put("creator", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("805320", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/discard", method = RequestMethod.POST)
	@ResponseBody
	public Object discardTicket(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("couponCode", map.get("code"));
		map.put("updater", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("805322", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageTicket(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("805330", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object listTicket(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("805331", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Object detailTicket(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("805332", JsonUtils.mapToJson(map),Object.class);
	}
}
