package com.xnjr.oss.home.ao.impl;

import org.springframework.stereotype.Service;

import com.xnjr.oss.home.ao.IMenuplatAO;
import com.xnjr.oss.home.req.XN704045Req;
import com.xnjr.oss.home.req.XN704046Req;
import com.xnjr.oss.home.req.XN704047Req;
import com.xnjr.oss.home.req.XN704048Req;
import com.xnjr.oss.home.req.XN704049Req;
import com.xnjr.oss.home.req.XN704309Req;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;

@Service
public class MenuplatAOImpl implements IMenuplatAO {

    @Override
    public Object addmenu(String name, String orderNo, String parentCode, String templetCode,
            String contentType, String userId, String remark) {
        XN704045Req req = new XN704045Req();
        req.setName(name);
        req.setOrderNo(orderNo);
        req.setParentCode(parentCode);
        req.setTempletCode(templetCode);
        req.setContentType(contentType);
        req.setUserid(userId);
        req.setRemark(remark);
        return BizConnecter.getBizData("704045", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object dropmenu(String code) {
        XN704046Req req = new XN704046Req();
        req.setCode(code);
        return BizConnecter.getBizData("704046", JsonUtils.object2Json(req),
            Object.class);
    }
    
    @Override
    public Object changemenu(String code) {
        XN704309Req req = new XN704309Req();
        req.setCode(code);
        return BizConnecter.getBizData("704309", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object editmenu(String code, String name, String parentCode, String orderNo,
            String templetCode, String contentType, String userId,
            String remark) {
        XN704047Req req = new XN704047Req();
        req.setCode(code);
        req.setName(name);
        req.setParentCode(parentCode);
        req.setOrderNo(orderNo);
        req.setTempletCode(templetCode);
        req.setContentType(contentType);
        req.setRemark(remark);
        return BizConnecter.getBizData("704047", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object querymenuPage(String code, String name, String status,
            String templetCode, String parentCode, String contentType,
            String companyCode, String start, String limit, String orderColumn,
            String orderDir) {
        XN704048Req req = new XN704048Req();
        req.setCode(code);
        req.setName(name);
        req.setStatus(status);
        req.setTempletCode(templetCode);
        req.setParentCode(parentCode);
        req.setContentType(contentType);
        req.setCompanyCode(companyCode);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704048", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object querymenuList(String code, String status, String templetCode,
            String parentCode, String contentType, String companyCode) {
        XN704049Req req = new XN704049Req();
        req.setCode(code);
        req.setStatus(status);
        req.setTempletCode(templetCode);
        req.setParentCode(parentCode);
        req.setContentType(contentType);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704049", JsonUtils.object2Json(req),
            Object.class);
    }

}
