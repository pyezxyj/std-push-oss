package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IBugAO;
import com.xnjr.oss.home.res.XN704026Res;
import com.xnjr.oss.home.res.XN704027Res;
import com.xnjr.oss.res.Page;

/**
 * Bug控制器
 * @author: wu 
 * @since: 2016年3月17日 下午9:17:01 
 * @history:
 */
@Controller
@RequestMapping(value = "/attach/bug")
public class BugController extends BaseController {

    @Autowired
    protected IBugAO bugAO;

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public boolean payBug(@RequestParam("code") String code,
            @RequestParam("money") String money) {
        XN704027Res res = bugAO.payBug(code, money);
        return res.isSuccess();
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public boolean checkBug(@RequestParam("code") String code,
            @RequestParam("checkResult") String checkResult,
            @RequestParam("checkDetail") String checkDetail) {
        XN704026Res res = bugAO.checkBug(code, this.getSessionUser()
            .getUserCode(), checkResult, checkDetail);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryBugPage(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "module", required = false) String module,
            @RequestParam(value = "alipay", required = false) String alipay,
            @RequestParam(value = "period", required = false) String period,
            @RequestParam(value = "checkUser", required = false) String checkUser,
            @RequestParam(value = "checkResult", required = false) String checkResult,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "money", required = false) String money,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return bugAO.queryBugPage(code, module, alipay, period, orderColumn,
            orderDir, checkUser, checkResult, money, status, start, limit,
            dateStart, dateEnd);

    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryBugList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "module", required = false) String module,
            @RequestParam(value = "alipay", required = false) String alipay,
            @RequestParam(value = "period", required = false) String period,
            @RequestParam(value = "checkUser", required = false) String checkUser,
            @RequestParam(value = "checkResult", required = false) String checkResult,
            @RequestParam(value = "dateStart", required = false) String dateStart,
            @RequestParam(value = "dateEnd", required = false) String dateEnd,
            @RequestParam(value = "money", required = false) String money,
            @RequestParam(value = "status", required = false) String status) {
        return bugAO.queryBugList(code, module, alipay, period, checkUser,
            checkResult, money, status, dateStart, dateEnd);
    }
}
