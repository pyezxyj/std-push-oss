package com.xnjr.app.merchant.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;

@Controller
@RequestMapping(value = "/merchant/integral")
public class IntegralController extends BaseController {
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Object pageUser(@RequestParam Map<String,String> map)
	{
		return BizConnecter.getBizData("805310", JsonUtils.mapToJson(map),Object.class);
	}
}
