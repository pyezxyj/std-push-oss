package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
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
@RequestMapping(value = "/web/news")
public class NewsController extends BaseController {
    @Autowired
    protected INewsAO newsAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN704035Res addNews(@RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("companyCode") String companyCode,
            @RequestParam("author") String author,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam(value = "jumpUrl", required = false) String jumpUrl,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "showUrl", required = false) String showUrl,
            @RequestParam(value = "remark", required = false) String remark) {
        return newsAO.addNews(title, keyword, type, content, picture, jumpUrl,
            showUrl, companyCode, this.getSessionUser().getUserName(), author,
            remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropNews(@RequestParam("code") String code) {
        XN704036Res res = newsAO.dropNews(code);
        return res.isSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editNews(@RequestParam("code") String code,
            @RequestParam("title") String title,
            @RequestParam("type") String type,
            @RequestParam("companyCode") String companyCode,
            @RequestParam("author") String author,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam(value = "jumpUrl", required = false) String jumpUrl,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "showUrl", required = false) String showUrl,
            @RequestParam(value = "remark", required = false) String remark) {
        XN704037Res res = newsAO.editNews(code, title, keyword, type, content,
            picture, jumpUrl, showUrl, companyCode, this.getSessionUser()
                .getUserCode(), author, remark);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryNewsPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return newsAO.queryNewsPage(code, title, type, companyCode, creator,
            dateStart, dateEnd, start, limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryNewsList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return newsAO.queryNewsList(code, title, type, companyCode, creator,
            dateStart, dateEnd);
    }
}
