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
@RequestMapping(value = "/merchant/input")
public class InputController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addProduct(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		map.put("pic1", UploadUtil.uploadPicture((String) map.get("pic1")));
		map.put("pic2", UploadUtil.uploadPicture((String) map.get("pic2")));
		map.put("pic3", UploadUtil.uploadPicture((String) map.get("pic3")));
		map.put("pic4", UploadUtil.uploadPicture((String) map.get("pic4")));
		map.put("advPic", UploadUtil.uploadPicture((String) map.get("advPic")));
		return BizConnecter.getBizData("808010", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteProduct(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		return BizConnecter.getBizData("808011", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object editProduct(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		map.put("pic1", UploadUtil.uploadPicture((String) map.get("pic1")));
		map.put("pic2", UploadUtil.uploadPicture((String) map.get("pic2")));
		map.put("pic3", UploadUtil.uploadPicture((String) map.get("pic3")));
		map.put("pic4", UploadUtil.uploadPicture((String) map.get("pic4")));
		map.put("advPic", UploadUtil.uploadPicture((String) map.get("advPic")));
		return BizConnecter.getBizData("808012", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageProduct(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808020", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Object listProduct(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808021", JsonUtils.mapToJson(map),Object.class);
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public Object detailProduct(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("808022", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/put", method = RequestMethod.POST)
	@ResponseBody
	public Object putProduct(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		return BizConnecter.getBizData("808013", JsonUtils.mapToJson(map),Object.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pull", method = RequestMethod.POST)
	@ResponseBody
	public Object pullProduct(@SuppressWarnings("rawtypes") @RequestBody Map map)
	{
		map.put("updater", this.getSessionUser().getUserName());
		map.put("remark", "10");
		return BizConnecter.getBizData("808014", JsonUtils.mapToJson(map),Object.class);
	}

}
