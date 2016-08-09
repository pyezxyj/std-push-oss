package com.xnjr.oss.general.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.general.ao.IContractAO;
import com.xnjr.oss.general.res.XN707002Res;
import com.xnjr.oss.res.Page;

@Controller
@RequestMapping(value = "general/contractTemplate")
public class ContractController extends BaseController {
    @Autowired
    IContractAO contractTemplateAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN707002Res addContractTemplate(@RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            @RequestParam(value = "remark", required = false) String remark) {
        return contractTemplateAO.addContractTemplate(title, content, type,
            status, this.getSessionUser().getUserName(), remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteContractTemplate(@RequestParam("id") String id) {
        return contractTemplateAO.deleteContractTemplate(id);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editContractTemplate(@RequestParam("id") String id,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            @RequestParam(value = "remark", required = false) String remark) {
        return contractTemplateAO.editContractTemplate(id, title, content,
            type, status, this.getSessionUser().getUserName(), remark);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryContractTemplateList(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "updater", required = false) String updater) {
        return contractTemplateAO.queryContractTemplateList(id, title, type,
            status, creator, updater);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryContractTemplatePage(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "creator", required = false) String creator,
            @RequestParam(value = "updater", required = false) String updater,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return contractTemplateAO.queryContractTemplatePage(id, title, type,
            status, creator, updater, dateStart, dateEnd, start, limit,
            orderColumn, orderDir);
    }
}
