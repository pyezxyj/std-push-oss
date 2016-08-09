/**
 * @Title MenuRoleAOImpl.java 
 * @Package com.ibis.pz.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-14 上午10:23:11 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.security.ao.IMenuRoleAO;
import com.xnjr.oss.security.req.XN705010Req;
import com.xnjr.oss.security.req.XN705011Req;
import com.xnjr.oss.security.req.XN705012Req;
import com.xnjr.oss.security.req.XN705013Req;
import com.xnjr.oss.security.req.XN705014Req;
import com.xnjr.oss.security.req.XN705015Req;
import com.xnjr.oss.security.req.XN705016Req;
import com.xnjr.oss.security.req.XN705017Req;
import com.xnjr.oss.security.res.XN705010Res;
import com.xnjr.oss.security.res.XN705012Res;
import com.xnjr.oss.security.res.XN705013Res;
import com.xnjr.oss.security.res.XN705014Res;
import com.xnjr.oss.security.res.XN705015Res;
import com.xnjr.oss.security.res.XN705016Res;

/** 
 * @author: miyb 
 * @since: 2015-5-14 上午10:23:11 
 * @history:
 */
@Service
public class MenuRoleAOImpl implements IMenuRoleAO {

    @Override
    public XN705013Res addMenuRole(String roleCode, String menuCode,
            String creator, String remark) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(menuCode)) {
            throw new BizException("XN700001", "菜单编号不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("XN700001", "创建人不能为空");
        }

        XN705013Req xn705013Res = new XN705013Req();
        xn705013Res.setRoleCode(roleCode);
        xn705013Res.setMenuCode(menuCode);
        xn705013Res.setCreator(creator);
        xn705013Res.setRemark(remark);
        return BizConnecter.getBizData("705013",
            JsonUtils.object2Json(xn705013Res), XN705013Res.class);
    }

    @Override
    public XN705014Res dropMenuRole(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "主键ID不能为空");
        }
        XN705014Req xn705014Req = new XN705014Req();
        xn705014Req.setId(id);
        return BizConnecter.getBizData("705014",
            JsonUtils.object2Json(xn705014Req), XN705014Res.class);
    }

    @Override
    public XN705015Res editMenuRole(String id, String roleCode,
            String menuCode, String updater, String remark) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "主键ID不能为空");
        }
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(menuCode)) {
            throw new BizException("XN700001", "菜单编号不能为空");
        }
        if (StringUtils.isBlank(updater)) {
            throw new BizException("XN700001", "更新人不能为空");
        }

        XN705015Req xn705015Req = new XN705015Req();
        xn705015Req.setId(id);
        xn705015Req.setRoleCode(roleCode);
        xn705015Req.setMenuCode(menuCode);
        xn705015Req.setUpdater(updater);
        xn705015Req.setRemark(remark);
        return BizConnecter.getBizData("705015",
            JsonUtils.object2Json(xn705015Req), XN705015Res.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<XN705010Res> queryMenuList(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        XN705010Req xn705010Req = new XN705010Req();
        xn705010Req.setRoleCode(roleCode);

        String jsonStr = BizConnecter.getBizData("705010",
            JsonUtils.object2Json(xn705010Req));
        Gson gson = new Gson();
        List<XN705010Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN705010Res>>() {
            }.getType());
        return list;

    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryRoleList(String menuCode) {
        if (StringUtils.isBlank(menuCode)) {
            throw new BizException("XN700001", "菜单编号不能为空");
        }
        XN705011Req xn705011Req = new XN705011Req();
        xn705011Req.setMenuCode(menuCode);
        return BizConnecter.getBizData("705011",
            JsonUtils.object2Json(xn705011Req), List.class);

    }

    @Override
    public XN705012Res getMenuRole(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "主键ID不能为空");
        }
        XN705012Req xn705012Req = new XN705012Req();
        xn705012Req.setId(id);
        return BizConnecter.getBizData("705012",
            JsonUtils.object2Json(xn705012Req), XN705012Res.class);
    }

    @Override
    public boolean changeMenuRole(String roleCode, String[] menuList,
            String creator) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        XN705016Req xn705016Req = new XN705016Req();
        xn705016Req.setRoleCode(roleCode);
        BizConnecter.getBizData("705016", JsonUtils.object2Json(xn705016Req),
            XN705016Res.class);
        for (String menu : menuList) {
            this.addMenuRole(roleCode, menu, creator, "");
        }
        return true;
    }

    @Override
    public List<XN705010Res> queryMenuList(String roleCode, String parentCode,
            String type, boolean isGetChild) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(parentCode)) {
            throw new BizException("XN700001", "父编号不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "类型不能为空");
        }
        XN705010Req xn705010Req = new XN705010Req();
        xn705010Req.setRoleCode(roleCode);
        String jsonStr = BizConnecter.getBizData("705010",
            JsonUtils.object2Json(xn705010Req));
        Gson gson = new Gson();
        List<XN705010Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN705010Res>>() {
            }.getType());
        List<XN705010Res> cList = new ArrayList<XN705010Res>();
        List<XN705010Res> resList = new ArrayList<XN705010Res>();
        for (XN705010Res res : list) {
            if (res.getParentCode().equals(parentCode)
                    && res.getType().equals(type)) {
                cList.add(res);
                resList.add(res);
            }
        }
        if (isGetChild) {
            for (XN705010Res res : cList) {
                for (XN705010Res result : list) {
                    if (res.getMenuCode().equals(result.getParentCode())) {
                        resList.add(result);
                    }
                }
            }
        }
        return resList;
    }

    /** 
     * @see com.xnjr.oss.security.ao.IMenuRoleAO#queryPermissionList(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List queryPermissionList(String roleCode, String parentCode,
            String type) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(parentCode)) {
            throw new BizException("XN700001", "父菜单编号不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "权限类型不能为空");
        }
        XN705017Req xn705017Req = new XN705017Req();
        xn705017Req.setRoleCode(roleCode);
        xn705017Req.setParentCode(parentCode);
        xn705017Req.setType(type);
        return BizConnecter.getBizData("705017",
            JsonUtils.object2Json(xn705017Req), List.class);
    }
}
