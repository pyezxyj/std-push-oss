package com.xnjr.oss.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.ao.IMenuAO;
import com.xnjr.oss.security.res.XN705002Res;
import com.xnjr.oss.security.res.XN705003Res;
import com.xnjr.oss.security.res.XN705004Res;

/**
 * 系统用户模块
 * @author: linyy 
 * @since: 2015年8月26日 下午22:51:04 
 * @history:
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    @Autowired
    protected IMenuAO menuAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN705002Res addmenu(
            @RequestParam("menuCode") String menuCode,
            @RequestParam("menuName") String menuName,
            @RequestParam("menuUrl") String menuUrl,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam("type") String type,
            @RequestParam("orderNo") String orderNo,
            @RequestParam(value = "remark", required = false) String remark) {
        return menuAO.addMenu(menuCode, menuName, menuUrl, parentCode, type,
            orderNo, this.getSessionUser().getUserName(), remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropMenu(@RequestParam("menuCode") String menuCode) {
        XN705003Res res = menuAO.dropMenu(menuCode);
        return res.getIsSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editMenu(
            @RequestParam("menuCode") String menuCode,
            @RequestParam("menuName") String menuName,
            @RequestParam("menuUrl") String menuUrl,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam("type") String type,
            @RequestParam("orderNo") String orderNo,
            @RequestParam(value = "remark", required = false) String remark) {

        XN705004Res res = menuAO.editMenu(menuCode, menuName, menuUrl,
            parentCode, type, orderNo, this.getSessionUser().getUserName(),
            remark);
        return res.getIsSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryMenuPage(
            @RequestParam(value = "menuCode", required = false) String menuCode,
            @RequestParam(value = "menuUrl", required = false) String menuUrl,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return menuAO.queryMenuPage(menuCode, menuUrl, parentCode, type, start,
            limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryMenuList(
            @RequestParam(value = "menuCode", required = false) String menuCode,
            @RequestParam(value = "menuUrl", required = false) String menuUrl,
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam(value = "type", required = false) String type) {
        return menuAO.queryMenuList(menuCode, menuUrl, parentCode, type);
    }

}
