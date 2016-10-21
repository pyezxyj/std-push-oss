package com.xnjr.app.standard;

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
@RequestMapping(value = "/std")
public class StandardController extends BaseController {
    
    // 意向状态修改
    @RequestMapping(value = "/intent/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object intentEdit(@RequestBody Map map) {
  		map.put("updater", this.getSessionUser().getUserName());
  		return BizConnecter.getBizData("806061", JsonUtils.mapToJson(map),
              Object.class);
	}
	
	// 分页查询意向
    @RequestMapping(value = "/intent/page", method = RequestMethod.GET)
    @ResponseBody
    public Object intentPage(@RequestParam Map<String,String> map) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("806070", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 列表查询意向
    @RequestMapping(value = "/intent/list", method = RequestMethod.GET)
    @ResponseBody
    public Object intentList(@RequestParam Map<String,String> map) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("806071", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 详情查询意向
    @RequestMapping(value = "/intent/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object viewDetail(@RequestParam Map<String,String> map) {
    	//allRequestParams.put("userId", this.getSessionUser().getUserId());
  	    return BizConnecter.getBizData("806072", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // banner新增
    @RequestMapping(value = "/banner/add", method = RequestMethod.POST)
    @ResponseBody
    public Object bannerAdd(@RequestBody Map map) {
    	map.put("type", map.get("2"));
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("806040", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // banner删除
    @RequestMapping(value = "/banner/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object bannerDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("806041", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // banner修改
    @RequestMapping(value = "/banner/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object bannerEdit(@RequestBody Map map) {
    	map.put("type", map.get("2"));
    	map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("806042", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // banner分页
    @RequestMapping(value = "/banner/page", method = RequestMethod.GET)
    @ResponseBody
    public Object bannerPage(@RequestParam Map<String,String> map) {
    	map.put("type", "2");
  	    return BizConnecter.getBizData("806051", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // banner列表
    @RequestMapping(value = "/banner/list", method = RequestMethod.GET)
    @ResponseBody
    public Object bannerList(@RequestParam Map<String,String> map) {
    	map.put("type", "2");
  	    return BizConnecter.getBizData("806053", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // banner详情
    @RequestMapping(value = "/banner/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object bannerDetail(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("806054", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 菜单新增
    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    @ResponseBody
    public Object menuAdd(@RequestBody Map map) {
    	map.put("type", map.get("1"));
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("806040", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 菜单删除
    @RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object menuDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("806041", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 菜单修改
    @RequestMapping(value = "/menu/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object menuEdit(@RequestBody Map map) {
    	map.put("type", map.get("1"));
  		map.put("pic", UploadUtil.uploadPicture((String) map.get("pic")));
  		return BizConnecter.getBizData("806042", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 菜单上下架
    @RequestMapping(value = "/menu/updown", method = RequestMethod.POST)
    @ResponseBody
    public Object menuUpdown(@RequestBody Map map) {
  		return BizConnecter.getBizData("806044", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 菜单分页
    @RequestMapping(value = "/menu/page", method = RequestMethod.GET)
    @ResponseBody
    public Object menuPage(@RequestParam Map<String,String> map) {
    	map.put("type", "1");
  	    return BizConnecter.getBizData("806051", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 菜单列表
    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    @ResponseBody
    public Object menuList(@RequestParam Map<String,String> map) {
    	map.put("type", "1");
  	    return BizConnecter.getBizData("806053", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 菜单详情
    @RequestMapping(value = "/menu/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object menuDetail(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("806054", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 素材新增
    @RequestMapping(value = "/material/add", method = RequestMethod.POST)
    @ResponseBody
    public Object materialAdd(@RequestBody Map map) {
    	map.put("pic1", UploadUtil.uploadPicture((String) map.get("pic1")));
    	map.put("pic2", UploadUtil.uploadPicture((String) map.get("pic2")));
    	map.put("endNote", UploadUtil.uploadPicture((String) map.get("endNote")));
  		return BizConnecter.getBizData("806100", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 素材删除
    @RequestMapping(value = "/material/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object materialDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("806101", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 素材修改
    @RequestMapping(value = "/material/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object materialEdit(@RequestBody Map map) {
    	map.put("pic1", UploadUtil.uploadPicture((String) map.get("pic1")));
    	map.put("pic2", UploadUtil.uploadPicture((String) map.get("pic2")));
    	map.put("endNote", UploadUtil.uploadPicture((String) map.get("endNote")));
  		return BizConnecter.getBizData("806102", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 素材分页
    @RequestMapping(value = "/material/page", method = RequestMethod.GET)
    @ResponseBody
    public Object materialPage(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("806110", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 素材列表
    @RequestMapping(value = "/material/list", method = RequestMethod.GET)
    @ResponseBody
    public Object materialList(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("806111", JsonUtils.mapToJson(map),
              Object.class);
    }
    
    // 素材详情
    @RequestMapping(value = "/material/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object materialDetail(@RequestParam Map<String,String> map) {
  	    return BizConnecter.getBizData("806112", JsonUtils.mapToJson(map),
              Object.class);
    }
}
