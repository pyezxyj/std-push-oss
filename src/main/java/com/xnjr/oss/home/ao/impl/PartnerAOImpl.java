package com.xnjr.oss.home.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.IPartnerAO;
import com.xnjr.oss.home.req.XN704010Req;
import com.xnjr.oss.home.req.XN704011Req;
import com.xnjr.oss.home.req.XN704012Req;
import com.xnjr.oss.home.req.XN704013Req;
import com.xnjr.oss.home.req.XN704014Req;
import com.xnjr.oss.home.res.XN704010Res;
import com.xnjr.oss.home.res.XN704011Res;
import com.xnjr.oss.home.res.XN704012Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class PartnerAOImpl implements IPartnerAO {

    @Override
    public XN704010Res addPartner(String name, String logo, String description,
            String url, String companyCode) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN704010", "名称不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN704010", "描述不能为空");
        }
        if (StringUtils.isBlank(url)) {
            throw new BizException("XN704010", "URL不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN704010", "公司编号不能为空");
        }
        XN704010Req xn704010Req = new XN704010Req();
        xn704010Req.setName(name);
        xn704010Req.setLogo(UploadUtil.uploadPicture(logo));
        xn704010Req.setDescription(description);
        xn704010Req.setUrl(url);
        xn704010Req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704010",
            JsonUtils.object2Json(xn704010Req), XN704010Res.class);

    }

    @Override
    public XN704011Res dropPartner(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN704011", "伙伴编号不能为空");
        }
        XN704011Req xn704011Req = new XN704011Req();
        xn704011Req.setCode(code);
        return BizConnecter.getBizData("704011",
            JsonUtils.object2Json(xn704011Req), XN704011Res.class);
    }

    @Override
    public XN704012Res editPartner(String code, String name, String logo,
            String description, String url, String companyCode) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN704012", "合作伙伴编码不能为空");
        }
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN704012", "合作伙伴名称不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN704012", "合作伙伴描述不能为空");
        }
        if (StringUtils.isBlank(url)) {
            throw new BizException("XN704012", "URL不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN704010", "公司编号不能为空");
        }
        XN704012Req xn704012Req = new XN704012Req();
        xn704012Req.setCode(code);
        xn704012Req.setName(name);
        xn704012Req.setLogo(uploadPicture(logo));
        xn704012Req.setDescription(description);
        xn704012Req.setUrl(url);
        xn704012Req.setCompanyCode(companyCode);

        return BizConnecter.getBizData("704012",
            JsonUtils.object2Json(xn704012Req), XN704012Res.class);
    }

    private String uploadPicture(String picture) {
        Pattern pattern = Pattern.compile("data:image/(.+?);base64");
        Matcher matcher = pattern.matcher(picture);
        if (!matcher.find()) {
            return picture;
        }
        return UploadUtil.uploadPicture(picture);
    }

    @Override
    public List queryPartnerList(String code, String name, String companyCode) {
        XN704014Req xn704014Req = new XN704014Req();
        xn704014Req.setCode(code);
        xn704014Req.setName(name);
        xn704014Req.setCompanyCode(companyCode);

        return BizConnecter.getBizData("704014",
            JsonUtils.object2Json(xn704014Req), List.class);
    }

    @Override
    public Page queryPartnerPage(String code, String name, String companyCode,
            String start, String limit, String orderColumn, String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN704013", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN704013", "limit不能为空");
        }
        XN704013Req xn704013Req = new XN704013Req();
        xn704013Req.setCode(code);
        xn704013Req.setName(name);
        xn704013Req.setCompanyCode(companyCode);
        xn704013Req.setStart(start);
        xn704013Req.setLimit(limit);
        xn704013Req.setOrderColumn(orderColumn);
        xn704013Req.setOrderDir(orderDir);

        return BizConnecter.getBizData("704013",
            JsonUtils.object2Json(xn704013Req), Page.class);
    }
}
