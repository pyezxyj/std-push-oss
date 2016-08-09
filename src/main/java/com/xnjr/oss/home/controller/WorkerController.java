package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IWorkerAO;
import com.xnjr.oss.home.res.XN704030Res;
import com.xnjr.oss.home.res.XN704031Res;
import com.xnjr.oss.home.res.XN704032Res;
import com.xnjr.oss.res.Page;

@Controller
@RequestMapping(value = "/web/worker")
public class WorkerController extends BaseController {

    @Autowired
    protected IWorkerAO workerAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN704030Res addWorker(@RequestParam("name") String name,
            @RequestParam("position") String position,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam("description") String description,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam("companyCode") String companyCode) {
        return workerAO.addWorker(name, position, picture, description,  remark,
            companyCode, this.getSessionUser().getUserName());
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropWorker(@RequestParam("code") String code) {
        XN704031Res res = workerAO.dropWorker(code);
        return res.isSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editWorker(@RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam("position") String position,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam("description") String description,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam("companyCode") String companyCode) {

        XN704032Res res = workerAO.editWorker(code, name, position, picture,
            description, remark, companyCode);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryNewsList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "companyCode", required = false) String companyCode) {
        return workerAO.queryWorkerList(code, name, status, dateStart, dateEnd,
            companyCode);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryWorkerPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return workerAO.queryWorkerPage(code, name, status, dateStart, dateEnd,
            companyCode, start, limit, orderColumn, orderDir);
    }

}
