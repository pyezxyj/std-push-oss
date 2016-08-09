package com.xnjr.oss.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IMenuplatAO;

@Controller
@RequestMapping(value = "/plat/menu")
public class MenuplatController extends BaseController {
    @Autowired
    protected IMenuplatAO menuplatAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addMenu(@RequestParam("name") String name,
            @RequestParam("orderNo") String orderNo,
            @RequestParam("parentCode") String parentCode,
            @RequestParam("templetCode") String templetCode,
            @RequestParam("contentType") String contentType,
            @RequestParam(value = "remark", required = false) String remark) {
        return menuplatAO.addmenu(name, orderNo, parentCode, templetCode, contentType,
            this.getSessionUser().getUserCode(), remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public Object dropmenu(@RequestParam("code") String code) {
        return menuplatAO.dropmenu(code);
    }
    
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    @ResponseBody
    public Object changemenu(@RequestParam("code") String code) {
        return menuplatAO.changemenu(code);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editMenu(@RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("parentCode") String parentCode,
            @RequestParam("orderNo") String orderNo,
            @RequestParam("templetCode") String templetCode,
            @RequestParam("contentType") String contentType,
            @RequestParam(value = "remark", required = false) String remark) {
        return menuplatAO.editmenu(code, name, parentCode, orderNo, templetCode,
            contentType, this.getSessionUser().getUserCode(), remark);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "templetCode", required = false) String templetCode,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam(value = "contentType", required = false) String contentType,
            @RequestParam(value = "companyCode", required = false) String companyCode) {
        return menuplatAO.querymenuList(code, status, templetCode, parentCode,
            contentType, companyCode);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "templetCode", required = false) String templetCode,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam(value = "contentType", required = false) String contentType,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return menuplatAO.querymenuPage(code, name, status, templetCode,
            parentCode, contentType, companyCode, start, limit, orderColumn,
            orderDir);
    }

}
