/**
 * @Title MenuAOImpl.java 
 * @Package com.ibis.pz.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-14 上午10:22:25 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.ao.IMenuAO;
import com.xnjr.oss.security.req.XN705000Req;
import com.xnjr.oss.security.req.XN705001Req;
import com.xnjr.oss.security.req.XN705002Req;
import com.xnjr.oss.security.req.XN705003Req;
import com.xnjr.oss.security.req.XN705004Req;
import com.xnjr.oss.security.res.XN705001Res;
import com.xnjr.oss.security.res.XN705002Res;
import com.xnjr.oss.security.res.XN705003Res;
import com.xnjr.oss.security.res.XN705004Res;

/** 
 * @author: miyb 
 * @since: 2015-5-14 上午10:22:25 
 * @history:
 */
@Service
public class MenuAOImpl implements IMenuAO {

    @Autowired
    ServletContext servletContext;

    @Override
    public XN705002Res addMenu(String menuCode, String menuName,
            String menuUrl, String parentCode, String type, String orderNo,
            String creator, String remark) {
        if (StringUtils.isBlank(menuCode)) {
            throw new BizException("XN700001", "菜单编号不能为空");
        }
        if (StringUtils.isBlank(menuName)) {
            throw new BizException("XN700001", "菜单名称不能为空");
        }
        if (StringUtils.isBlank(menuUrl)) {
            throw new BizException("XN700001", "菜单URL不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "菜单类型不能为空");
        }
        if (StringUtils.isBlank(orderNo)) {
            throw new BizException("XN700001", "菜单顺序号不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("XN700001", "创建人不能为空");
        }
        XN705002Req xn705002Req = new XN705002Req();
        xn705002Req.setMenuCode(menuCode);
        xn705002Req.setMenuName(menuName);
        xn705002Req.setMenuUrl(menuUrl);
        xn705002Req.setParentCode(parentCode);
        xn705002Req.setType(type);
        xn705002Req.setOrderNo(orderNo);
        xn705002Req.setCreator(creator);
        xn705002Req.setRemark(remark);
        xn705002Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705002",
            JsonUtils.object2Json(xn705002Req), XN705002Res.class);
    }

    @Override
    public XN705003Res dropMenu(String menuCode) {
        if (StringUtils.isBlank(menuCode)) {
            throw new BizException("XN700001", "菜单编号不能为空");
        }
        XN705003Req xn705003Req = new XN705003Req();
        xn705003Req.setMenuCode(menuCode);
        return BizConnecter.getBizData("705003",
            JsonUtils.object2Json(xn705003Req), XN705003Res.class);
    }

    @Override
    public XN705004Res editMenu(String menuCode, String menuName,
            String menuUrl, String parentCode, String type, String orderNo,
            String updater, String remark) {
        if (StringUtils.isBlank(menuCode)) {
            throw new BizException("XN700001", "菜单编号不能为空");
        }
        if (StringUtils.isBlank(menuName)) {
            throw new BizException("XN700001", "菜单名称不能为空");
        }
        if (StringUtils.isBlank(menuUrl)) {
            throw new BizException("XN700001", "菜单URL不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "菜单类型不能为空");
        }
        if (StringUtils.isBlank(orderNo)) {
            throw new BizException("XN700001", "菜单顺序号不能为空");
        }
        if (StringUtils.isBlank(updater)) {
            throw new BizException("XN700001", "修改人不能为空");
        }

        XN705004Req xn705004Req = new XN705004Req();
        xn705004Req.setMenuCode(menuCode);
        xn705004Req.setMenuName(menuName);
        xn705004Req.setMenuUrl(menuUrl);
        xn705004Req.setParentCode(parentCode);
        xn705004Req.setType(type);
        xn705004Req.setOrderNo(orderNo);
        xn705004Req.setUpdater(updater);
        xn705004Req.setRemark(remark);
        return BizConnecter.getBizData("705004",
            JsonUtils.object2Json(xn705004Req), XN705004Res.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Page queryMenuPage(String menuCode, String menuUrl,
            String parentCode, String type, String start, String limit,
            String orderColumn, String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN705000Req xn705000Req = new XN705000Req();
        xn705000Req.setMenuCode(menuCode);
        xn705000Req.setMenuUrl(menuUrl);
        xn705000Req.setParentCode(parentCode);
        xn705000Req.setType(type);
        xn705000Req.setStart(start);
        xn705000Req.setLimit(limit);
        xn705000Req.setOrderColumn(orderColumn);
        xn705000Req.setOrderDir(orderDir);
        xn705000Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705000",
            JsonUtils.object2Json(xn705000Req), Page.class);
    }

    @Override
    public List<XN705001Res> queryMenuList(String menuCode, String menuUrl,
            String parentCode, String type) {
        XN705001Req xn705001Req = new XN705001Req();
        xn705001Req.setMenuCode(menuCode);
        xn705001Req.setMenuUrl(menuUrl);
        xn705001Req.setParentCode(parentCode);
        xn705001Req.setType(type);
        xn705001Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        String jsonStr = BizConnecter.getBizData("705001",
            JsonUtils.object2Json(xn705001Req));
        Gson gson = new Gson();
        List<XN705001Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN705001Res>>() {
            }.getType());
        return list;
    }

}
