package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IJobsAO;
import com.xnjr.oss.home.res.XN704020Res;
import com.xnjr.oss.home.res.XN704021Res;
import com.xnjr.oss.home.res.XN704022Res;
import com.xnjr.oss.res.Page;

/**
 *新闻控制器
 * @author: jlxuu
 * @since: 2015年10月27日 上午9:36:42 
 * @history:
 */

@Controller
@RequestMapping(value = "/web/jobs")
public class JobsController extends BaseController {
    @Autowired
    protected IJobsAO jobsAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN704020Res addJobs(
            @RequestParam("name") String name,
            @RequestParam("duty") String duty,
            @RequestParam("claim") String claim,
            @RequestParam("area") String area,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam("description") String description,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "department", required = false) String department,
            @RequestParam("companyCode") String companyCode) {
        return jobsAO.addJobs(name, department, area, duty, claim, content,
            description, status, companyCode);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropJobs(@RequestParam("code") String code) {
        XN704021Res res = jobsAO.dropJobs(code);
        return res.isSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editJobs(
            @RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("duty") String duty,
            @RequestParam("claim") String claim,
            @RequestParam("area") String area,
            @RequestParam(value = "content", required = false) String content,
            @RequestParam("description") String description,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "department", required = false) String department,
            @RequestParam("companyCode") String companyCode) {
        XN704022Res res = jobsAO.editJobs(code, name, department, area, duty,
            claim, content, description, status, companyCode);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryJobsPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return jobsAO.queryJobsPage(code, name, companyCode, status, dateStart, dateEnd,
            start, limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryJobsList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd) {
        return jobsAO.queryJobsList(code, name, status, dateStart, dateEnd);
    }
}
