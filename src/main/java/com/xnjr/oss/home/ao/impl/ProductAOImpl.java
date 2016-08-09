package com.xnjr.oss.home.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.IProductAO;
import com.xnjr.oss.home.req.XN704005Req;
import com.xnjr.oss.home.req.XN704006Req;
import com.xnjr.oss.home.req.XN704007Req;
import com.xnjr.oss.home.req.XN704008Req;
import com.xnjr.oss.home.res.XN704005Res;
import com.xnjr.oss.home.res.XN704006Res;
import com.xnjr.oss.home.res.XN704007Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class ProductAOImpl implements IProductAO {

    @Override
    public List queryProductList(String code, String name, String kind,
            String status, String companyCode) {
        XN704007Req req = new XN704007Req();
        req.setCode(code);
        req.setName(name);
        req.setKind(kind);
        req.setStatus(status);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704009", JsonUtils.object2Json(req),
            List.class);
    }

    @Override
    public Page queryProductPage(String code, String name, String kind,
            String status, String companyCode, String start, String limit,
            String orderColumn, String orderDir) {
        XN704008Req xn704008Req = new XN704008Req();
        xn704008Req.setCode(code);
        xn704008Req.setName(name);
        xn704008Req.setKind(kind);
        xn704008Req.setCompanyCode(companyCode);
        xn704008Req.setStart(start);
        xn704008Req.setStatus(status);
        xn704008Req.setLimit(limit);
        xn704008Req.setOrderColumn(orderColumn);
        xn704008Req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704008",
            JsonUtils.object2Json(xn704008Req), Page.class);
    }

    @Override
    public XN704005Res addProduct(String name, String picture,
            String description, String kind, String status, String companyCode) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN700001", "产品名称不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN700001", "产品描述不能为空");
        }
        if (StringUtils.isBlank(kind)) {
            throw new BizException("XN700001", "产品类型不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "产品状态不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        picture = UploadUtil.uploadPicture(picture);
        XN704005Req req = new XN704005Req();
        req.setName(name);
        req.setPicture(picture);
        req.setDescription(description);
        req.setKind(kind);
        req.setStatus(status);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704005", JsonUtils.object2Json(req),
            XN704005Res.class);
    }

    @Override
    public XN704006Res dropProduct(String code) {
        XN704006Req req = new XN704006Req();
        req.setCode(code);
        return BizConnecter.getBizData("704006", JsonUtils.object2Json(req),
            XN704006Res.class);
    }

    @Override
    public XN704007Res editProduct(String code, String name, String picture,
            String description, String kind, String status, String companyCode) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN700001", "产品名称不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN700001", "产品描述不能为空");
        }
        if (StringUtils.isBlank(kind)) {
            throw new BizException("XN700001", "产品类别不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "产品状态不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        XN704007Req req = new XN704007Req();
        req.setCode(code);
        req.setName(name);
        req.setPicture(uploadPicture(picture));
        req.setDescription(description);
        req.setKind(kind);
        req.setStatus(status);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704007", JsonUtils.object2Json(req),
            XN704007Res.class);
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
