package com.xnjr.oss.home.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.ICaseAO;
import com.xnjr.oss.home.req.XN704015Req;
import com.xnjr.oss.home.req.XN704016Req;
import com.xnjr.oss.home.req.XN704017Req;
import com.xnjr.oss.home.req.XN704018Req;
import com.xnjr.oss.home.req.XN704019Req;
import com.xnjr.oss.home.res.XN704015Res;
import com.xnjr.oss.home.res.XN704016Res;
import com.xnjr.oss.home.res.XN704017Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class CaseAOimpl implements ICaseAO {

    @Override
    public XN704015Res addCase(String name, String logo, String picture,
            String url, String description, String companyCode, String status) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN700001", "案例名称不能为空");
        }
        if (StringUtils.isBlank(url)) {
            throw new BizException("XN700001", "案例的url不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN700001", "案例描述不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "公司状态不能为空");
        }
        logo = UploadUtil.uploadPicture(logo);
        picture = UploadUtil.uploadPicture(picture);
        XN704015Req req = new XN704015Req();
        req.setName(name);
        req.setLogo(logo);
        req.setPicture(picture);
        req.setUrl(url);
        req.setDescription(description);
        req.setCompanyCode(companyCode);
        req.setStatus(status);
        return BizConnecter.getBizData("704015", JsonUtils.object2Json(req),
            XN704015Res.class);
    }

    @Override
    public XN704016Res dropCase(String code) {
        XN704016Req req = new XN704016Req();
        req.setCode(code);
        return BizConnecter.getBizData("704016", JsonUtils.object2Json(req),
            XN704016Res.class);
    }

    @Override
    public XN704017Res editCase(String code, String name, String logo,
            String status, String picture, String url, String description, String companyCode) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN700001", "案例编号不能为空");
        }
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN700001", "案例名称不能为空");
        }
        if (StringUtils.isBlank(url)) {
            throw new BizException("XN700001", "案例的url不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN700001", "案例描述不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "公司状态不能为空");
        }
        XN704017Req req = new XN704017Req();
        req.setCode(code);
        req.setName(name);
        req.setLogo(uploadPicture(logo));
        req.setStatus(status);
        req.setPicture(uploadPicture(picture));
        req.setUrl(url);
        req.setDescription(description);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704017", JsonUtils.object2Json(req),
            XN704017Res.class);
    }

    private String uploadPicture(String picture) {
        Pattern pattern = Pattern.compile("data:image/(.+?);base64");
        Matcher matcher = pattern.matcher(picture);
        if (!matcher.find()) {
            return picture;
        }
        return UploadUtil.uploadPicture(picture);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryCaseList(String code, String name, String companyCode,
            String status, String dateStart, String dateEnd) {
        XN704019Req req = new XN704019Req();
        req.setCode(code);
        req.setName(name);
        req.setCompanyCode(companyCode);
        req.setStatus(status);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd); 
        return BizConnecter.getBizData("704019", JsonUtils.object2Json(req),
            List.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Page queryCasePage(String code, String name, String companyCode,
            String status, String dateStart, String dateEnd, String start, String limit,
            String orderColumn, String orderDir) {
        XN704018Req req = new XN704018Req();
        req.setCode(code);
        req.setName(name);
        req.setCompanyCode(companyCode);
        req.setStatus(status);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704018", JsonUtils.object2Json(req),
            Page.class);
    }
}
