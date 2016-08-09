package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.ICaseAO;
import com.xnjr.oss.home.res.XN704015Res;
import com.xnjr.oss.home.res.XN704016Res;
import com.xnjr.oss.home.res.XN704017Res;
import com.xnjr.oss.res.Page;

/**
 * 案例管理
 * @author: jlxuu
 * @since: 2015年10月27日 上午9:36:42 
 * @history:
 */

@Controller
@RequestMapping(value = "/web/case")
public class CaseController extends BaseController {
    @Autowired
    protected ICaseAO caseAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN704015Res addcase(@RequestParam("name") String name,
            @RequestParam("logo") String logo, @RequestParam("url") String url,
            @RequestParam("description") String description,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam("companyCode") String companyCode,
            @RequestParam("status") String status) {
    	return caseAO.addCase(name, logo, picture, url, description,
            companyCode, status);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropProduct(@RequestParam("code") String code) {
        XN704016Res res = caseAO.dropCase(code);
        return res.isSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editCase(@RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("logo") String logo, @RequestParam("url") String url,
            @RequestParam("status") String status,
            @RequestParam("description") String description,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam("companyCode") String companyCode) {
        XN704017Res res = caseAO.editCase(code, name, logo, status, picture, url,
            description, companyCode);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryCasePage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit) {
        return caseAO.queryCasePage(code, name, companyCode, status, dateStart,
            dateEnd, start, limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryCaseList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd) {
        return caseAO
            .queryCaseList(code, name, companyCode, status, dateStart, dateEnd);
    }
}
