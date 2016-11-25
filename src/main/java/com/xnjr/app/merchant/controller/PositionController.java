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
@RequestMapping(value = "/merchant/position")
public class PositionController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addGenre(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
		map.put("type", "2");
		return BizConnecter.getBizData("808000", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteGenre(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		return BizConnecter.getBizData("808001", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editGenre(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{	
		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
		map.put("type", "2");
		return BizConnecter.getBizData("808002", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageGenre(@RequestParam Map<String,String> map)
	{
		map.put("type", "2");
		return BizConnecter.getBizData("808005", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object listGenre(@RequestParam Map<String,String> map)
	{
		map.put("type", "2");
		return BizConnecter.getBizData("808006", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Object detailGenre(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808007", JsonUtils.mapToJson(map),Object.class);
	}
}
