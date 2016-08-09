package com.xnjr.oss.security.ao.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.oss.enums.EUserStatus;
import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.ao.IUserAO;
import com.xnjr.oss.security.req.XN705030Req;
import com.xnjr.oss.security.req.XN705031Req;
import com.xnjr.oss.security.req.XN705032Req;
import com.xnjr.oss.security.req.XN705033Req;
import com.xnjr.oss.security.req.XN705034Req;
import com.xnjr.oss.security.req.XN705035Req;
import com.xnjr.oss.security.req.XN705036Req;
import com.xnjr.oss.security.req.XN705037Req;
import com.xnjr.oss.security.res.XN705032Res;
import com.xnjr.oss.security.res.XN705033Res;
import com.xnjr.oss.security.res.XN705034Res;
import com.xnjr.oss.security.res.XN705035Res;
import com.xnjr.oss.security.res.XN705036Res;
import com.xnjr.oss.security.res.XN705037Res;
import com.xnjr.oss.util.MD5Util;

/**
 * 系统用户模块
 * @author: xieyj 
 * @since: 2015年8月16日 下午2:32:15 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {

    @Autowired
    ServletContext servletContext;

    @Override
    public XN705035Res login(String userName, String pwd, String loginIp) {
        if (StringUtils.isBlank(userName)) {
            throw new BizException("XN700001", "登录名不能为空");
        }
        if (StringUtils.isBlank(pwd)) {
            throw new BizException("XN700001", "密码不能为空");
        }
        if (StringUtils.isBlank(loginIp)) {
            throw new BizException("XN700001", "IP不能为空");
        }
        XN705035Req xn705035Req = new XN705035Req();
        xn705035Req.setLoginName(userName);
        xn705035Req.setLoginPwd(MD5Util.md5(pwd));
        xn705035Req.setLoginIp(loginIp);
        xn705035Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705035",
            JsonUtils.object2Json(xn705035Req), XN705035Res.class);
    }

    @Override
    public XN705036Res changePsd(String userCode, String oldPwd, String newPwd) {
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(oldPwd)) {
            throw new BizException("XN700001", "旧密码不能为空");
        }
        if (StringUtils.isBlank(newPwd)) {
            throw new BizException("XN700001", "新密码不能为空");
        }
        XN705036Req xn705036Req = new XN705036Req();
        xn705036Req.setUserCode(userCode);
        xn705036Req.setOldPwd(MD5Util.md5(oldPwd));
        xn705036Req.setNewPwd(MD5Util.md5(newPwd));

        return BizConnecter.getBizData("705036",
            JsonUtils.object2Json(xn705036Req), XN705036Res.class);
    }

    @Override
    public XN705037Res changePsdByAdmin(String adminCode, String adminPwd,
            String userCode) {
        if (StringUtils.isBlank(adminCode)) {
            throw new BizException("XN700001", "管理员编号不能为空");
        }
        if (StringUtils.isBlank(adminPwd)) {
            throw new BizException("XN700001", "管理员密码不能为空");
        }
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        XN705037Req xn705037Req = new XN705037Req();
        xn705037Req.setAdminCode(adminCode);
        xn705037Req.setAdminPwd(MD5Util.md5(adminPwd));
        xn705037Req.setUserCode(userCode);
        xn705037Req.setUserPwd(MD5Util.md5("888888"));
        return BizConnecter.getBizData("705037",
            JsonUtils.object2Json(xn705037Req), XN705037Res.class);
    }

    @Override
    public XN705032Res addUser(String userCode, String userName,
            String creator, String remark) {
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("ZC703176", "登录名不能为空");
        }
        if (StringUtils.isBlank(userName)) {
            throw new BizException("ZC703176", "密码不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("ZC703176", "创建人不能为空");
        }
        XN705032Req xn705032Req = new XN705032Req();
        xn705032Req.setUserCode(userCode);
        xn705032Req.setUserName(userName);
        xn705032Req.setPassword(MD5Util.md5("888888"));
        xn705032Req.setStatus(EUserStatus.OK.getCode());
        xn705032Req.setCreator(creator);
        xn705032Req.setRemark(remark);
        xn705032Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705032",
            JsonUtils.object2Json(xn705032Req), XN705032Res.class);
    }

    @Override
    public XN705033Res dropUser(String userCode) {
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        XN705033Req xn705033Req = new XN705033Req();
        xn705033Req.setUserCode(userCode);
        return BizConnecter.getBizData("705033",
            JsonUtils.object2Json(xn705033Req), XN705033Res.class);
    }

    @Override
    public XN705034Res editUser(String userCode, String userName,
            String status, String updater, String remark) {
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(userName)) {
            throw new BizException("XN700001", "用户名不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "状态不能为空");
        }
        if (StringUtils.isBlank(updater)) {
            throw new BizException("XN700001", "角色修改人ID不能为空");
        }
        XN705034Req xn705034Req = new XN705034Req();
        xn705034Req.setUserCode(userCode);
        xn705034Req.setUserName(userName);
        xn705034Req.setStatus(status);
        xn705034Req.setUpdater(updater);
        xn705034Req.setRemark(remark);
        return BizConnecter.getBizData("705034",
            JsonUtils.object2Json(xn705034Req), XN705034Res.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Page queryUserPage(String userCode, String userName, String status,
            String start, String limit, String orderColumn, String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN705030Req xn705030Req = new XN705030Req();
        xn705030Req.setUserCode(userCode);
        xn705030Req.setUserName(userName);
        xn705030Req.setStatus(status);
        xn705030Req.setStart(start);
        xn705030Req.setLimit(limit);
        xn705030Req.setOrderColumn(orderColumn);
        xn705030Req.setOrderDir(orderDir);
        xn705030Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705030",
            JsonUtils.object2Json(xn705030Req), Page.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryUserList(String userCode, String userName, String status) {
        XN705031Req xn705031Req = new XN705031Req();
        xn705031Req.setUserCode(userCode);
        xn705031Req.setUserName(userName);
        xn705031Req.setStatus(status);
        xn705031Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705031",
            JsonUtils.object2Json(xn705031Req), List.class);
    }

}
