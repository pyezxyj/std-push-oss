package com.xnjr.oss.home.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.IBugAO;
import com.xnjr.oss.home.req.XN704026Req;
import com.xnjr.oss.home.req.XN704027Req;
import com.xnjr.oss.home.req.XN704028Req;
import com.xnjr.oss.home.req.XN704029Req;
import com.xnjr.oss.home.res.XN704026Res;
import com.xnjr.oss.home.res.XN704027Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;

@Service
public class BugAOImpl implements IBugAO {

    @Override
    public XN704027Res payBug(String code, String money) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN700001", "编号不能为空");
        }
        if (StringUtils.isBlank(money)) {
            throw new BizException("XN700001", "奖励金额不能为空");
        }
        XN704027Req xn704027Req = new XN704027Req();
        xn704027Req.setCode(code);
        xn704027Req.setMoney(money);
        return BizConnecter.getBizData("704027",
            JsonUtils.object2Json(xn704027Req), XN704027Res.class);
    }

    @Override
    public XN704026Res checkBug(String code, String checkUser,
            String checkResult, String checkDetail) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN700001", "Bug编号不能为空");
        }
        if (StringUtils.isBlank(checkUser)) {
            throw new BizException("XN700001", "复核人不能为空");
        }
        if (StringUtils.isBlank(checkResult)) {
            throw new BizException("XN700001", "复核意见不能为空");
        }

        if (StringUtils.isBlank(checkDetail)) {
            throw new BizException("XN700001", "复核意见说明不能为空");
        }
        XN704026Req xn704026Req = new XN704026Req();
        xn704026Req.setCheckDetail(checkDetail);
        xn704026Req.setCheckResult(checkResult);
        xn704026Req.setCheckUser(checkUser);
        xn704026Req.setCode(code);
        return BizConnecter.getBizData("704026",
            JsonUtils.object2Json(xn704026Req), XN704026Res.class);

    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryBugList(String code, String module, String alipay,
            String period, String checkUser, String checkResult, String money,
            String status, String dateStart, String dateEnd) {
        XN704029Req xn704029Req = new XN704029Req();
        xn704029Req.setAlipay(alipay);
        xn704029Req.setCheckResult(checkResult);
        xn704029Req.setCheckUser(checkUser);
        xn704029Req.setCode(code);
        xn704029Req.setDateEnd(dateEnd);
        xn704029Req.setDateStart(dateStart);
        xn704029Req.setModule(module);
        xn704029Req.setMoney(money);
        xn704029Req.setPeriod(period);
        xn704029Req.setStatus(status);

        return BizConnecter.getBizData("704029",
            JsonUtils.object2Json(xn704029Req), List.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Page queryBugPage(String code, String module, String alipay,
            String period, String orderColumn, String orderDir,
            String checkUser, String checkResult, String money, String status,
            String start, String limit, String dateStart, String dateEnd) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN704028Req xn704028Req = new XN704028Req();
        xn704028Req.setAlipay(alipay);
        xn704028Req.setCheckResult(checkResult);
        xn704028Req.setCheckUser(checkUser);
        xn704028Req.setCode(code);
        xn704028Req.setStart(start);
        xn704028Req.setDateEnd(dateEnd);
        xn704028Req.setDateStart(dateStart);
        xn704028Req.setLimit(limit);
        xn704028Req.setModule(module);
        xn704028Req.setMoney(money);
        xn704028Req.setOrderColumn(orderColumn);
        xn704028Req.setOrderDir(orderDir);
        xn704028Req.setPeriod(period);
        xn704028Req.setStatus(status);
        return BizConnecter.getBizData("704028",
            JsonUtils.object2Json(xn704028Req), Page.class);
    }

}
