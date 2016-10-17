package com.xnjr.app.forum;

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
@RequestMapping(value = "/rule")
public class RuleController extends BaseController {

	// 关键字新增
    @RequestMapping(value = "/keyword/add", method = RequestMethod.POST)
    @ResponseBody
    public Object keywordAdd(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("610090", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 关键字修改
    @RequestMapping(value = "/keyword/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object keywordEdit(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("610092", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 关键字删除
    @RequestMapping(value = "/keyword/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object keywordDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("610091", JsonUtils.mapToJson(map),
              Object.class);
	}
	
	// 关键字分页
    @RequestMapping(value = "/keyword/page", method = RequestMethod.GET)
    @ResponseBody
    public Object keywordPage(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610100", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 关键字详情
    @RequestMapping(value = "/keyword/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object keywordDetail(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610101", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 举报规则修改
    @RequestMapping(value = "/report/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object reportEdit(@RequestBody Map map) {
    	map.put("kind", "2");
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("610110", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 举报规则分页
    @RequestMapping(value = "/report/page", method = RequestMethod.GET)
    @ResponseBody
    public Object reportPage(@RequestParam Map<String,String> allRequestParams) {
    	allRequestParams.put("kind", "2");
  	    return BizConnecter.getBizData("610120", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 举报规则详情
    @RequestMapping(value = "/report/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object reportDetail(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610121", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 积分规则修改
    @RequestMapping(value = "/score/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object scoreEdit(@RequestBody Map map) {
    	map.put("kind", "1");
  		map.put("updater", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("610110", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 积分规则分页
    @RequestMapping(value = "/score/page", method = RequestMethod.GET)
    @ResponseBody
    public Object scorePage(@RequestParam Map<String,String> allRequestParams) {
    	allRequestParams.put("kind", "1");
  	    return BizConnecter.getBizData("610120", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 积分规则详情
    @RequestMapping(value = "/score/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object scoreDetail(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610121", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
}
