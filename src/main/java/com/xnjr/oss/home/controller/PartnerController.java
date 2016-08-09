package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IPartnerAO;
import com.xnjr.oss.home.res.XN704010Res;
import com.xnjr.oss.home.res.XN704011Res;
import com.xnjr.oss.home.res.XN704012Res;
import com.xnjr.oss.res.Page;

@Controller
@RequestMapping(value = "/web/partner")
public class PartnerController extends BaseController {
    @Autowired
    protected IPartnerAO partnerAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN704010Res addPartner(@RequestParam("name") String name,
            @RequestParam("logo") String logo,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("companyCode") String companyCode) {
        return partnerAO.addPartner(name, logo, description, url, companyCode);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropPartner(@RequestParam("code") String code) {
        XN704011Res res = partnerAO.dropPartner(code);
        return res.isSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editNews(@RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam(value = "logo", required = false) String logo,
            @RequestParam("description") String description,
            @RequestParam("url") String url,
            @RequestParam("companyCode") String companyCode) {
        XN704012Res res = partnerAO.editPartner(code, name, logo, description,
            url, companyCode);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryPartnerPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return partnerAO.queryPartnerPage(code, name, companyCode, start,
            limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryPartnerList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "companyCode", required = false) String companyCode) {
        return partnerAO.queryPartnerList(code, name, companyCode);
    }
}
