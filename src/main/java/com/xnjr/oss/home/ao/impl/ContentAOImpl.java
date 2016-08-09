package com.xnjr.oss.home.ao.impl;

import org.springframework.stereotype.Service;

import com.xnjr.oss.home.ao.IContentAO;
import com.xnjr.oss.home.req.XN704301Req;
import com.xnjr.oss.home.req.XN704302Req;
import com.xnjr.oss.home.req.XN704303Req;
import com.xnjr.oss.home.req.XN704304Req;
import com.xnjr.oss.home.req.XN704305Req;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.util.UploadUtil;

@Service
public class ContentAOImpl implements IContentAO {

    @Override
    public Object addcontent(String title, String type, String picture1, String picture2,
            String description, String menuCode, String remark, String endNote) {
        XN704301Req req = new XN704301Req();
        req.setTitle(title);
        req.setType(type);
        req.setPicture1(UploadUtil.uploadPicture(picture1));
        req.setPicture2(UploadUtil.uploadPicture(picture2));
        req.setDescription(description);
        req.setMenuCode(menuCode);
        req.setRemark(remark);
        req.setEndNote(endNote);
        return BizConnecter.getBizData("704301", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object dropcontent(String code) {
        XN704302Req req = new XN704302Req();
        req.setCode(code);
        return BizConnecter.getBizData("704302", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object editcontent(String code, String type, String title,
            String picture1, String picture2, String description,
            String menuCode, String remark, String endNote) {
        XN704303Req req = new XN704303Req();
        req.setCode(code);
        req.setType(type);
        req.setTitle(title);
        req.setPicture1(UploadUtil.uploadPicture(picture1));
        req.setPicture2(UploadUtil.uploadPicture(picture2));
        req.setDescription(description);
        req.setMenuCode(menuCode);
        req.setRemark(remark);
        req.setEndNote(endNote);
        return BizConnecter.getBizData("704303", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object querycontentPage(String code, String type, String title,
            String menuCode, String start, String limit, String orderColumn,
            String orderDir) {
        XN704304Req req = new XN704304Req();
        req.setCode(code);
        req.setType(type);
        req.setTitle(title);
        req.setMenuCode(menuCode);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704304", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object querycontentList(String code, String type, String title,
            String menuCode) {
        XN704305Req req = new XN704305Req();
        req.setCode(code);
        req.setType(type);
        req.setTitle(title);
        req.setMenuCode(menuCode);
        return BizConnecter.getBizData("704305", JsonUtils.object2Json(req),
            Object.class);
    }

}
