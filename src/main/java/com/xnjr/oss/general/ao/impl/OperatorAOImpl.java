package com.xnjr.oss.general.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.general.ao.IOperatorAO;
import com.xnjr.oss.general.req.XN803000Req;
import com.xnjr.oss.general.req.XN803001Req;
import com.xnjr.oss.general.req.XN803002Req;
import com.xnjr.oss.general.req.XN803003Req;
import com.xnjr.oss.general.req.XN803004Req;
import com.xnjr.oss.general.res.XN803002Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class OperatorAOImpl implements IOperatorAO {
    @Override
    public boolean addOperator(String userId, String companyId, String mobile,
            String idKind, String idNo, String realName, String photo,
            String tradePwd, String introduction, String remark) {
        if (StringUtils.isBlank(userId)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(companyId)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        if (StringUtils.isBlank(mobile)) {
            throw new BizException("XN700001", "手机号不能为空");
        }
        if (StringUtils.isBlank(idKind)) {
            throw new BizException("XN700001", "证件类型不能为空");
        }
        if (StringUtils.isBlank(idNo)) {
            throw new BizException("XN700001", "证件号不能为空");
        }
        if (StringUtils.isBlank(realName)) {
            throw new BizException("XN700001", "真实姓名不能为空");
        }
        if (StringUtils.isBlank(tradePwd)) {
            throw new BizException("XN700001", "交易密码不能为空");
        }
        if (StringUtils.isBlank(introduction)) {
            throw new BizException("XN700001", "简介不能为空");
        }
        XN803002Req xn803002Req = new XN803002Req();
        xn803002Req.setUserId(userId);
        xn803002Req.setCompanyId(companyId);
        xn803002Req.setMobile(mobile);
        xn803002Req.setIdKind(idKind);
        xn803002Req.setIdNo(idNo);
        xn803002Req.setRealName(realName);
        xn803002Req.setPhoto(uploadPicture(photo));
        xn803002Req.setTradePwd(tradePwd);
        xn803002Req.setIntroduction(introduction);
        xn803002Req.setRemark(remark);
        return BizConnecter.getBizData("803002",
            JsonUtils.object2Json(xn803002Req), XN803002Res.class)
            .getIsSuccess();
    }

    @Override
    public boolean deleteOperator(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        XN803003Req xn803003Req = new XN803003Req();
        xn803003Req.setUserId(userId);
        return BizConnecter.getBizData("803003",
            JsonUtils.object2Json(xn803003Req), XN803002Res.class)
            .getIsSuccess();
    }

    @Override
    public boolean editOperator(String userId, String companyId, String mobile,
            String level, String photo, String introduction, String remark,
            String status) {
        if (StringUtils.isBlank(userId)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(companyId)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        if (StringUtils.isBlank(mobile)) {
            throw new BizException("XN700001", "手机号不能为空");
        }
        if (StringUtils.isBlank(photo)) {
            throw new BizException("XN700001", "头像不能为空");
        }
        if (StringUtils.isBlank(introduction)) {
            throw new BizException("XN700001", "简介不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "状态不能为空");
        }
        XN803004Req xn803004Req = new XN803004Req();
        xn803004Req.setUserId(userId);
        xn803004Req.setCompanyId(companyId);
        xn803004Req.setMobile(mobile);
        xn803004Req.setPhoto(uploadPicture(photo));
        xn803004Req.setIntroduction(introduction);
        xn803004Req.setRemark(remark);
        xn803004Req.setStatus(status);
        xn803004Req.setLevel(level);
        return BizConnecter.getBizData("803004",
            JsonUtils.object2Json(xn803004Req), XN803002Res.class)
            .getIsSuccess();

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
    public List queryOperatorList(String userId, String companyId,
            String mobile, String realName, String level, String status) {
        XN803000Req xn803000Req = new XN803000Req();
        xn803000Req.setUserId(userId);
        xn803000Req.setCompanyId(companyId);
        xn803000Req.setMobile(mobile);
        xn803000Req.setRealName(realName);
        xn803000Req.setStatus(status);
        xn803000Req.setLevel(level);
        return BizConnecter.getBizData("803000",
            JsonUtils.object2Json(xn803000Req), List.class);
    }

    @Override
    public Page queryOperatorPage(String userId, String companyId,
            String mobile, String realName, String level, String status,
            String start, String limit, String orderColumn, String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN803001Req xn803001Req = new XN803001Req();
        xn803001Req.setUserId(userId);
        xn803001Req.setCompanyId(companyId);
        xn803001Req.setMobile(mobile);
        xn803001Req.setRealName(realName);
        xn803001Req.setStatus(status);
        xn803001Req.setLevel(level);
        xn803001Req.setOrderColumn(orderColumn);
        xn803001Req.setOrderDir(orderDir);
        xn803001Req.setStart(start);
        xn803001Req.setLimit(limit);
        return BizConnecter.getBizData("803001",
            JsonUtils.object2Json(xn803001Req), Page.class);
    }

}
