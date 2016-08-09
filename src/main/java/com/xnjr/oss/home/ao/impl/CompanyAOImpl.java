package com.xnjr.oss.home.ao.impl;

import org.springframework.stereotype.Service;

import com.xnjr.oss.home.ao.ICompanyAO;
import com.xnjr.oss.home.req.XN704001Req;
import com.xnjr.oss.home.req.XN704002Req;
import com.xnjr.oss.home.req.XN704003Req;
import com.xnjr.oss.home.req.XN704004Req;
import com.xnjr.oss.home.req.XN704050Req;
import com.xnjr.oss.home.req.XN704306Req;
import com.xnjr.oss.home.req.XN704308Req;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.util.UploadUtil;

@Service
public class CompanyAOImpl implements ICompanyAO {

    @Override
    public Object addCompany(String name, String corporation, String logo,
            String address, String copyright, String description,
            String telephone, String fax, String email, String url,
            String slogan, String barCode, String longitude, String latitude,
            String userid) {
        XN704001Req req = new XN704001Req();
        req.setName(name);
        req.setCorporation(corporation);
        req.setLogo(UploadUtil.uploadPicture(logo));
        req.setAddress(address);
        req.setCopyright(copyright);
        req.setDescription(description);
        req.setTelephone(telephone);
        req.setFax(fax);
        req.setEmail(email);
        req.setUrl(url);
        req.setSlogan(slogan);
        req.setBarCode(UploadUtil.uploadPicture(barCode));
        req.setLongitude(longitude);
        req.setLatitude(latitude);
        req.setUserid(userid);
        return BizConnecter.getBizData("704001", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object dropCompany(String code) {
        XN704002Req req = new XN704002Req();
        req.setCode(code);
        return BizConnecter.getBizData("704002", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object editCompany(String code, String name, String corporation,
            String logo, String address, String copyright, String description,
            String telephone, String fax, String email, String url,
            String slogan, String barCode, String longitude, String latitude,
            String userid) {
        XN704003Req req = new XN704003Req();
        req.setCode(code);
        req.setName(name);
        req.setCorporation(corporation);
        req.setLogo(UploadUtil.uploadPicture(logo));
        req.setAddress(address);
        req.setCopyright(copyright);
        req.setDescription(description);
        req.setTelephone(telephone);
        req.setFax(fax);
        req.setEmail(email);
        req.setUrl(url);
        req.setSlogan(slogan);
        req.setBarCode(UploadUtil.uploadPicture(barCode));
        req.setLongitude(longitude);
        req.setLatitude(latitude);
        req.setUserid(userid);
        return BizConnecter.getBizData("704003", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object detailcompany(String code) {
        XN704004Req req = new XN704004Req();
        req.setCode(code);
        return BizConnecter.getBizData("704004", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object detailcompanyuserid(String userid) {
        XN704306Req req = new XN704306Req();
        req.setUserid(userid);
        return BizConnecter.getBizData("704306", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object queryCompanyPage(String name, String code, String userid,
            String start, String limit, String orderColumn, String orderDir) {
        XN704308Req req = new XN704308Req();
        req.setName(name);
        req.setCode(code);
        req.setUserid(userid);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704308", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object queryCompanyList(String name, String code, String userid) {
        XN704050Req req = new XN704050Req();
        req.setName(name);
        req.setCode(code);
        req.setUserid(userid);
        return BizConnecter.getBizData("704050", JsonUtils.object2Json(req),
            Object.class);
    }

}
