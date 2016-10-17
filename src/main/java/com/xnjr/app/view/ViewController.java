package com.xnjr.app.view;

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
@RequestMapping(value = "/view")
public class ViewController extends BaseController {

	// 视图新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object viewAdd(@RequestBody Map map) {
  		map.put("userId", this.getSessionUser().getUserId());
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("610020", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 视图修改
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object viewEdit(@RequestBody Map map) {
  		map.put("userId", this.getSessionUser().getUserId());
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("610022", JsonUtils.mapToJson(map),
              Object.class);
	}
	
	// 分页查询视图信息
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object viewPage(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610030", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 列表查询视图信息
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object viewList(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610031", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 详情查询视图信息
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object viewDetail(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610032", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
}
