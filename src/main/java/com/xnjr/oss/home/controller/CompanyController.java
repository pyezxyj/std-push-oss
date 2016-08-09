package com.xnjr.oss.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.ICompanyAO;

@Controller
@RequestMapping(value = "/plat/company")
public class CompanyController extends BaseController {
    @Autowired
    protected ICompanyAO companyAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addCompany(@RequestParam("name") String name,
            @RequestParam("corporation") String corporation,
            @RequestParam("logo") String logo,
            @RequestParam("address") String address,
            @RequestParam("copyright") String copyright,
            @RequestParam("description") String description,
            @RequestParam("telephone") String telephone,
            @RequestParam("fax") String fax,
            @RequestParam("email") String email,
            @RequestParam("url") String url,
            @RequestParam("slogan") String slogan,
            @RequestParam("barCode") String barCode,
            @RequestParam("longitude") String longitude,
            @RequestParam("latitude") String latitude,
            @RequestParam("userid") String userid) {
        return companyAO.addCompany(name, corporation, logo, address, copyright,
            description, telephone, fax, email, url, slogan, barCode, longitude,
            latitude, userid);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public Object dropCompany(@RequestParam("code") String code) {
        return companyAO.dropCompany(code);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editCompany(@RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("corporation") String corporation,
            @RequestParam("logo") String logo,
            @RequestParam("address") String address,
            @RequestParam("copyright") String copyright,
            @RequestParam("description") String description,
            @RequestParam("telephone") String telephone,
            @RequestParam("fax") String fax,
            @RequestParam("email") String email,
            @RequestParam("url") String url,
            @RequestParam("slogan") String slogan,
            @RequestParam("barCode") String barCode,
            @RequestParam("longitude") String longitude,
            @RequestParam("latitude") String latitude,
            @RequestParam("userid") String userid) {
        return companyAO.editCompany(code, name, corporation, logo, address,
            copyright, description, telephone, fax, email, url, slogan, barCode,
            longitude, latitude, userid);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "userid", required = false) String userid) {
        return companyAO.queryCompanyList(name, code, userid);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryCompanyPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "userid", required = false) String userid,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return companyAO.queryCompanyPage(name, code, userid, start, limit,
            orderColumn, orderDir);
    }

    @RequestMapping(value = "/detailcode", method = RequestMethod.GET)
    @ResponseBody
    public Object detailcompany(@RequestParam("code") String code) {
        return companyAO.detailcompany(code);
    }

    @RequestMapping(value = "/detailuserid", method = RequestMethod.GET)
    @ResponseBody
    public Object detailcompanyuserid(@RequestParam("userid") String userid) {
        return companyAO.detailcompanyuserid(userid);
    }

}
