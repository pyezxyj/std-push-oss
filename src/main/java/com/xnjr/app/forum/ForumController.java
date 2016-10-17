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
@RequestMapping(value = "/forum")
public class ForumController extends BaseController {

	// 版块新增
    @RequestMapping(value = "/board/add", method = RequestMethod.POST)
    @ResponseBody
    public Object boardAdd(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("610040", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 版块修改
    @RequestMapping(value = "/board/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object boardEdit(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserId());
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("610041", JsonUtils.mapToJson(map),
              Object.class);
	}
	
	// 分页查询版块
    @RequestMapping(value = "/board/page", method = RequestMethod.GET)
    @ResponseBody
    public Object boardPage(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610045", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 列表查询版块
    @RequestMapping(value = "/board/list", method = RequestMethod.GET)
    @ResponseBody
    public Object boardList(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610046", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 版块详情
    @RequestMapping(value = "/board/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object boardDetail(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610047", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 分页查询帖子
    @RequestMapping(value = "/post/page", method = RequestMethod.GET)
    @ResponseBody
    public Object postPage(@RequestParam Map<String,String> allRequestParams) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("610070", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 帖子详情
    @RequestMapping(value = "/post/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object postDetail(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("610072", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 帖子审核
    @RequestMapping(value = "/post/check", method = RequestMethod.POST)
    @ResponseBody
    public Object postCheck(@RequestBody Map map) {
  		map.put("approver", this.getSessionUser().getUserId());
  		return BizConnecter.getBizData("610055", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 帖子删除
    @RequestMapping(value = "/post/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object postdelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("610052", JsonUtils.mapToJson(map),
              Object.class);
	}
}
