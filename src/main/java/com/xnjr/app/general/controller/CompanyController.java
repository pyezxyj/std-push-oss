package com.xnjr.app.general.controller;

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
@RequestMapping(value = "/general/company")
public class CompanyController extends BaseController {
	
	// 新增公司
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object cityAdd(@RequestBody Map map) {
    	map.put("isDefault", "0");
    	map.put("type", "1");
    	map.put("location", "1");
    	map.put("logo", UploadUtil.uploadPicture((String) map.get("logo")));
    	map.put("qrCode", UploadUtil.uploadPicture((String) map.get("qrCode")));
    	map.put("updater", this.getSessionUser().getUserName());
    	return BizConnecter.getBizData("806000", JsonUtils.mapToJson(map),
                Object.class);
    }
    
    // 删除公司
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object cityDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("806001", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 禁用公司
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object cityCancel(@RequestBody Map map) {
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("806002", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 修改公司
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object cityEdit(@RequestBody Map map) {
    	map.put("type", "1");
    	map.put("logo", UploadUtil.uploadPicture((String) map.get("logo")));
    	map.put("qrCode", UploadUtil.uploadPicture((String) map.get("qrCode")));
    	map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("806004", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 设置默认
    @RequestMapping(value = "/default", method = RequestMethod.POST)
    @ResponseBody
    public Object cityDefault(@RequestBody Map map) {
  		return BizConnecter.getBizData("806003", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 分页查询站点
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object cityPage(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("806014", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 列表查询站点
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object cityList(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("806013", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 详情查询
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object cityDetail(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("806010", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
}
