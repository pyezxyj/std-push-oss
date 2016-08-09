package com.xnjr.oss.home.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.IWorkerAO;
import com.xnjr.oss.home.req.XN704030Req;
import com.xnjr.oss.home.req.XN704031Req;
import com.xnjr.oss.home.req.XN704032Req;
import com.xnjr.oss.home.req.XN704033Req;
import com.xnjr.oss.home.req.XN704034Req;
import com.xnjr.oss.home.res.XN704030Res;
import com.xnjr.oss.home.res.XN704031Res;
import com.xnjr.oss.home.res.XN704032Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class WorkerAOImpl implements IWorkerAO {

    @Override
    public XN704030Res addWorker(String name, String position, String picture,
            String description, String remark, String companyCode,
            String creator) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN704030", "姓名不能为空");
        }
        if (StringUtils.isBlank(position)) {
            throw new BizException("XN704030", "职位不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN704030", "描述不能为空");
        }
        if (StringUtils.isBlank(remark)) {
            throw new BizException("XN704030", "备注不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN704030", "公司编号不能为空");
        }
        picture = UploadUtil.uploadPicture(picture);
        XN704030Req xn704030Req = new XN704030Req();
        xn704030Req.setName(name);
        xn704030Req.setCompanyCode(companyCode);
        xn704030Req.setPosition(position);
        xn704030Req.setPicture(picture);
        xn704030Req.setDescription(description);
        xn704030Req.setRemark(remark);
        xn704030Req.setCompanyCode(companyCode);
        
        return BizConnecter.getBizData("704030",
            JsonUtils.object2Json(xn704030Req), XN704030Res.class);
    }

    @Override
    public XN704031Res dropWorker(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN704031", "职工编号不能为空");
        }
        XN704031Req xn704031Req = new XN704031Req();
        xn704031Req.setCode(code);
        ;
        return BizConnecter.getBizData("704031",
            JsonUtils.object2Json(xn704031Req), XN704031Res.class);
    }

    @Override
    public XN704032Res editWorker(String code, String name, String position,
            String picture, String description, String remark,
            String companyCode) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN704032", "职工编码不能为空");
        }
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN704032", "职工姓名不能为空");
        }
        if (StringUtils.isBlank(position)) {
            throw new BizException("XN704032", "类型不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN704032", "发布时间不能为空");
        }
        if (StringUtils.isBlank(remark)) {
            throw new BizException("XN704032", "创建者不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN704032", "公司编号不能为空");
        }
        XN704032Req xn704032Req = new XN704032Req();
        xn704032Req.setCode(code);
        xn704032Req.setName(name);
        xn704032Req.setPosition(position);
        xn704032Req.setPicture(uploadPicture(picture));
        xn704032Req.setDescription(description);
        xn704032Req.setRemark(remark);
        xn704032Req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704032",
            JsonUtils.object2Json(xn704032Req), XN704032Res.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryWorkerList(String code, String name, String status,
            String dateStart, String dateEnd, String companyCode) {
        XN704034Req xn704034Req = new XN704034Req();
        xn704034Req.setCode(code);
        xn704034Req.setName(name);
        xn704034Req.setStatus(status);
        xn704034Req.setDateStart(dateStart);
        xn704034Req.setDateEnd(dateEnd);
        xn704034Req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704034",
            JsonUtils.object2Json(xn704034Req), List.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Page queryWorkerPage(String code, String name, String status,
            String dateStart, String dateEnd, String companyCode, String start,
            String limit, String orderColumn, String orderDir) {
        XN704033Req req = new XN704033Req();
        req.setCode(code);
        req.setName(name);
        req.setStatus(status);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd);
        req.setCompanyCode(companyCode);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704033", JsonUtils.object2Json(req),
            Page.class);
    }

    private String uploadPicture(String picture) {
        Pattern pattern = Pattern.compile("data:image/(.+?);base64");
        Matcher matcher = pattern.matcher(picture);
        if (!matcher.find()) {
            return picture;
        }
        return UploadUtil.uploadPicture(picture);
    }
}
