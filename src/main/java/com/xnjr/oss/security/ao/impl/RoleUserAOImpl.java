package com.xnjr.oss.security.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.security.ao.IRoleUserAO;
import com.xnjr.oss.security.req.XN705020Req;
import com.xnjr.oss.security.req.XN705021Req;
import com.xnjr.oss.security.req.XN705022Req;
import com.xnjr.oss.security.req.XN705023Req;
import com.xnjr.oss.security.req.XN705024Req;
import com.xnjr.oss.security.req.XN705025Req;
import com.xnjr.oss.security.req.XN705026Req;
import com.xnjr.oss.security.res.XN705021Res;
import com.xnjr.oss.security.res.XN705022Res;
import com.xnjr.oss.security.res.XN705023Res;
import com.xnjr.oss.security.res.XN705024Res;
import com.xnjr.oss.security.res.XN705025Res;
import com.xnjr.oss.security.res.XN705026Res;

@Service
public class RoleUserAOImpl implements IRoleUserAO {
    @Override
    public XN705023Res addUserRole(String userCode, String roleCode,
            String creator, String remark) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("XN700001", "创建人不能为空");
        }
        XN705023Req xn705023Req = new XN705023Req();
        xn705023Req.setUserCode(userCode);
        xn705023Req.setRoleCode(roleCode);
        xn705023Req.setCreator(creator);
        xn705023Req.setRemark(remark);
        return BizConnecter.getBizData("705023",
            JsonUtils.object2Json(xn705023Req), XN705023Res.class);
    }

    @Override
    public XN705024Res dropUserRole(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "主键ID不能为空");
        }
        XN705024Req xn705024Req = new XN705024Req();
        xn705024Req.setId(id);
        return BizConnecter.getBizData("705024",
            JsonUtils.object2Json(xn705024Req), XN705024Res.class);
    }

    @Override
    public XN705025Res editUserRole(String id, String userCode,
            String roleCode, String updater, String remark) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "主键ID不能为空");
        }
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(updater)) {
            throw new BizException("XN700001", "更新人不能为空");
        }

        XN705025Req xn705025Req = new XN705025Req();
        xn705025Req.setId(id);
        xn705025Req.setUserCode(userCode);
        xn705025Req.setRoleCode(roleCode);
        xn705025Req.setUpdater(updater);
        xn705025Req.setRemark(remark);
        return BizConnecter.getBizData("705025",
            JsonUtils.object2Json(xn705025Req), XN705025Res.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryUserList(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        XN705020Req xn705020Req = new XN705020Req();
        xn705020Req.setRoleCode(roleCode);
        return BizConnecter.getBizData("705020",
            JsonUtils.object2Json(xn705020Req), List.class);

    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryRoleList(String userCode) {
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        XN705021Req xn705021Req = new XN705021Req();
        xn705021Req.setUserCode(userCode);
        return BizConnecter.getBizData("705021",
            JsonUtils.object2Json(xn705021Req), List.class);
    }

    @Override
    public XN705022Res getUserRole(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "主键ID不能为空");
        }
        XN705022Req xn705022Req = new XN705022Req();
        xn705022Req.setId(id);
        return BizConnecter.getBizData("705022",
            JsonUtils.object2Json(xn705022Req), XN705022Res.class);
    }

    @Override
    public boolean changeUserRole(String userCode, String roleCode,
            String creator) {
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        XN705026Req xn705026Req = new XN705026Req();
        xn705026Req.setUserCode(userCode);
        BizConnecter.getBizData("705026", JsonUtils.object2Json(xn705026Req),
            XN705026Res.class);

        this.addUserRole(userCode, roleCode, creator, "");

        return true;
    }

    @Override
    public XN705021Res getRole(String userCode) {
        XN705021Res role = null;
        if (StringUtils.isBlank(userCode)) {
            throw new BizException("XN700001", "用户编号不能为空");
        }
        XN705021Req xn705021Req = new XN705021Req();
        xn705021Req.setUserCode(userCode);

        String jsonStr = BizConnecter.getBizData("705021",
            JsonUtils.object2Json(xn705021Req));
        Gson gson = new Gson();
        List<XN705021Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN705021Res>>() {
            }.getType());
        if (CollectionUtils.isNotEmpty(list)) {
            role = list.get(0);
        }
        return role;
    }
}
