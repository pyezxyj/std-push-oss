/**
 * @Title RoleAOImpl.java 
 * @Package com.ibis.pz.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-5-14 上午10:27:29 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.ao.IRoleAO;
import com.xnjr.oss.security.req.XN705005Req;
import com.xnjr.oss.security.req.XN705006Req;
import com.xnjr.oss.security.req.XN705007Req;
import com.xnjr.oss.security.req.XN705008Req;
import com.xnjr.oss.security.req.XN705009Req;
import com.xnjr.oss.security.res.XN705007Res;
import com.xnjr.oss.security.res.XN705008Res;
import com.xnjr.oss.security.res.XN705009Res;

/**
 * @author: miyb
 * @since: 2015-5-14 上午10:27:29
 * @history:
 */
@Service
public class RoleAOImpl implements IRoleAO {

    @Autowired
    ServletContext servletContext;

    @Override
    public XN705007Res addRole(String roleCode, String roleName,
            String roleLevel, String creator, String remark) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(roleName)) {
            throw new BizException("XN700001", "角色名称不能为空");
        }
        if (StringUtils.isBlank(roleLevel)) {
            throw new BizException("XN700001", "角色等级不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("XN700001", "角色创建人ID不能为空");
        }
        XN705007Req xn705007Req = new XN705007Req();
        xn705007Req.setRoleCode(roleCode);
        xn705007Req.setRoleName(roleName);
        xn705007Req.setRoleLevel(roleLevel);
        xn705007Req.setCreator(creator);
        xn705007Req.setRemark(remark);
        xn705007Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705007",
            JsonUtils.object2Json(xn705007Req), XN705007Res.class);
    }

    @Override
    public XN705008Res dropRole(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        XN705008Req xn705008Req = new XN705008Req();
        xn705008Req.setRoleCode(roleCode);
        return BizConnecter.getBizData("705008",
            JsonUtils.object2Json(xn705008Req), XN705008Res.class);
    }

    @Override
    public XN705009Res editRole(String roleCode, String roleName,
            String roleLevel, String updater, String remark) {
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "角色编号不能为空");
        }
        if (StringUtils.isBlank(roleName)) {
            throw new BizException("XN700001", "角色名称不能为空");
        }
        if (StringUtils.isBlank(roleLevel)) {
            throw new BizException("XN700001", "角色等级不能为空");
        }
        if (StringUtils.isBlank(updater)) {
            throw new BizException("XN700001", "角色修改人ID不能为空");
        }

        XN705009Req xn705009Req = new XN705009Req();
        xn705009Req.setRoleCode(roleCode);
        xn705009Req.setRoleName(roleName);
        xn705009Req.setRoleLevel(roleLevel);
        xn705009Req.setUpdater(updater);
        xn705009Req.setRemark(remark);
        return BizConnecter.getBizData("705009",
            JsonUtils.object2Json(xn705009Req), XN705009Res.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Page queryRolePage(String roleCode, String roleLevel,
            String createDatetimeStart, String createDatetimeEnd, String start,
            String limit, String orderColumn, String orderDir) {

        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN705005Req xn705005Req = new XN705005Req();
        xn705005Req.setRoleCode(roleCode);
        xn705005Req.setRoleLevel(roleLevel);
        xn705005Req.setDateStart(createDatetimeStart);
        xn705005Req.setDateEnd(createDatetimeEnd);
        xn705005Req.setStart(start);
        xn705005Req.setLimit(limit);
        xn705005Req.setOrderColumn(orderColumn);
        xn705005Req.setOrderDir(orderDir);
        xn705005Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705005",
            JsonUtils.object2Json(xn705005Req), Page.class);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List queryRoleList(String roleCode, String roleLevel,
            String createDatetimeStart, String createDatetimeEnd) {
        XN705006Req xn705006Req = new XN705006Req();
        xn705006Req.setRoleCode(roleCode);
        xn705006Req.setRoleLevel(roleLevel);
        xn705006Req.setDateStart(createDatetimeStart);
        xn705006Req.setDateEnd(createDatetimeEnd);
        xn705006Req.setApplyID(servletContext.getAttribute("applyID")
            .toString());
        return BizConnecter.getBizData("705006",
            JsonUtils.object2Json(xn705006Req), List.class);
    }
}
