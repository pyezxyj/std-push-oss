package com.xnjr.oss.general.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.general.ao.IOperatorAO;
import com.xnjr.oss.res.Page;

@Controller
@RequestMapping(value = "/general/operator")
public class OperatorController {
    @Autowired
    protected IOperatorAO operatoAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addOperator(@RequestParam("userId") String userId,
            @RequestParam("companyId") String companyId,
            @RequestParam("mobile") String mobile,
            @RequestParam("idKind") String idKind,
            @RequestParam("idNo") String idNo,
            @RequestParam("realName") String realName,
            @RequestParam("photo") String photo,
            @RequestParam("tradePwd") String tradePwd,
            @RequestParam("introduction") String introduction,
            @RequestParam(value = "remark", required = false) String remark) {
        return operatoAO.addOperator(userId, companyId, mobile, idKind, idNo,
            realName, photo, tradePwd, introduction, remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteOperator(@RequestParam("userId") String userId) {
        return operatoAO.deleteOperator(userId);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editOperator(@RequestParam("userId") String userId,
            @RequestParam("companyId") String companyId,
            @RequestParam("mobile") String mobile,
            @RequestParam("level") String level,
            @RequestParam("photo") String photo,
            @RequestParam("status") String status,
            @RequestParam("introduction") String introduction,
            @RequestParam(value = "remark", required = false) String remark) {
        return operatoAO.editOperator(userId, companyId, mobile, level, photo,
            introduction, remark, status);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryOperatorList(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "status", required = false) String status) {
        return operatoAO.queryOperatorList(userId, companyId, mobile, realName,
            level, status);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryOperatorPage(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "companyId", required = false) String companyId,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return operatoAO.queryOperatorPage(userId, companyId, mobile, realName,
            level, status, start, limit, orderColumn, orderDir);
    }
}
