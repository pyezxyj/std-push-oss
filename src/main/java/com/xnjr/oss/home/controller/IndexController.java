package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IIndexAO;
import com.xnjr.oss.home.ao.INewsAO;
import com.xnjr.oss.home.res.XN704035Res;
import com.xnjr.oss.home.res.XN704036Res;
import com.xnjr.oss.home.res.XN704037Res;
import com.xnjr.oss.res.Page;

/**
 * 
 * @author XIANDONG
 *
 */
@Controller
@RequestMapping(value = "/web/index")
public class IndexController extends BaseController {
    @Autowired
    protected IIndexAO indexAO;

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editIndex(
    		@RequestParam(value = "code") String code,
    		@RequestParam(value = "banner1", required = false) String banner1,
            @RequestParam(value = "banner2", required = false) String banner2,
            @RequestParam(value = "banner3", required = false) String banner3,
            @RequestParam(value = "fullSizePic", required = false) String fullSizePic,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "urlText", required = false) String urlText,
            @RequestParam(value = "companyCode") String companyCode,
            @RequestParam(value = "remark", required = false) String remark) {
        Object res = indexAO.editIndex(code, banner1, banner2, banner3, fullSizePic, url,
        		urlText, companyCode, this.getSessionUser()
                .getUserCode(), remark);
        return res;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryIndexPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return indexAO.queryIndexPage(code, companyCode, creator,
            start, limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryIndexList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return indexAO.queryIndexList(code, companyCode, creator,
        		orderColumn, orderDir);
    }
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object IndexDetail(
            @RequestParam(value = "code", required = false) String code) {
        return indexAO.indexDetail(code);
    }
}
