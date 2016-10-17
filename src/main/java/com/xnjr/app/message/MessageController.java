package com.xnjr.app.message;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.enums.EUserKind;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;
import com.xnjr.app.util.UploadUtil;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

	// 消息新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object messageAdd(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("805120", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 消息删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object messageDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("805121", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 消息修改
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object messageEdit(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("805122", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 消息发布
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public Object messagePublish(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("805123", JsonUtils.mapToJson(map),
              Object.class);
	}
	
	// 消息分页
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object messagePage(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("805130", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 消息列表
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object messageList(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("805131", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 消息详情
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object viewDetail(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("805132", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
}
