package com.xnjr.oss.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.ISearchAO;

@Controller
@RequestMapping(value = "/plat/search")
public class SearchController extends BaseController {
    @Autowired
    protected ISearchAO searchAO;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyList(
    		@RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "updater", required = false) String updater,
            @RequestParam(value = "companyCode", required = false) String companyCode) {
        return searchAO.querySearchList(code, type, status,
        		creator, updater, companyCode);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyPage(
    		@RequestParam(value = "person", required = false) String person,
    		@RequestParam(value = "contact", required = false) String contact,
    		@RequestParam(value = "content1", required = false) String content1,
    		@RequestParam(value = "content2", required = false) String content2,
    		@RequestParam(value = "organization", required = false) String organization,
    		@RequestParam(value = "organizationDesc", required = false) String organizationDesc,
    		@RequestParam(value = "remark", required = false) String remark,
    		@RequestParam(value = "personDesc", required = false) String personDesc,
    		@RequestParam(value = "dateStart", required = false) String dateStart,
    		@RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "updater", required = false) String updater,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return searchAO.querySearchPage(person, contact, content1, content2, organization,
     		   organizationDesc, remark ,personDesc, dateStart, dateEnd, code, type, status,
        		creator, updater, companyCode, start, limit, orderColumn, orderDir);
    }

    
}
