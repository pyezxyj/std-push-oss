package com.xnjr.oss.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IContentAO;

@Controller
@RequestMapping(value = "/plat/content")
public class ContentController extends BaseController {
    @Autowired
    protected IContentAO contentAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addcontent(@RequestParam("title") String title,
    		@RequestParam("type") String type,
            @RequestParam("picture1") String picture1,
            @RequestParam("picture2") String picture2,
            @RequestParam("description") String description,
            @RequestParam("menuCode") String menuCode,
            @RequestParam("endNote") String endNote,
            @RequestParam(value = "remark", required = false
            ) String remark) {
        return contentAO.addcontent(title, type, picture1, picture2, description,
            menuCode, remark, endNote);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public Object dropcontent(@RequestParam("code") String code) {
        return contentAO.dropcontent(code);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editcontent(@RequestParam("code") String code,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam("title") String title,
            @RequestParam("picture1") String picture1,
            @RequestParam("picture2") String picture2,
            @RequestParam("description") String description,
            @RequestParam("menuCode") String menuCode,
            @RequestParam("endNote") String endNote,
            @RequestParam(value = "remark", required = false) String remark) {
        return contentAO.editcontent(code, type, title, picture1, picture2,
            description, menuCode, remark, endNote);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object querycontentList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "menuCode", required = false) String menuCode) {
        return contentAO.querycontentList(code, type, title, menuCode);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object querycontentPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "menuCode", required = false) String menuCode,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return contentAO.querycontentPage(code, type, title, menuCode, start,
            limit, orderColumn, orderDir);

    }

}
