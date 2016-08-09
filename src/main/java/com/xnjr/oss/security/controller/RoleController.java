/**
 * @Title MemberController_haiqing.java 
 * @Package com.ibis.pz.controller 
 * @Description 
 * @author 茜茜  
 * @date 2015年3月12日 下午11:15:22 
 * @version V1.0   
 */
package com.xnjr.oss.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.ao.IMenuAO;
import com.xnjr.oss.security.ao.IMenuRoleAO;
import com.xnjr.oss.security.ao.IRoleAO;
import com.xnjr.oss.security.res.CheckedMenu;
import com.xnjr.oss.security.res.XN705001Res;
import com.xnjr.oss.security.res.XN705007Res;
import com.xnjr.oss.security.res.XN705008Res;
import com.xnjr.oss.security.res.XN705009Res;
import com.xnjr.oss.security.res.XN705010Res;

/** 
 * @author: 茜茜 
 * @since: 2015年3月12日 下午11:15:22 
 * @history:
 */

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
    @Autowired
    protected IRoleAO roleAO;

    @Autowired
    protected IMenuAO menuAO;

    @Autowired
    protected IMenuRoleAO roleMenuAO;

    @RequestMapping(value = "/menuList", method = RequestMethod.GET)
    @ResponseBody
    public List queryMenuList(@RequestParam("roleCode") String roleCode) {
        return roleMenuAO.queryMenuList(roleCode);
    }

    @RequestMapping(value = "/checkedlist", method = RequestMethod.GET)
    @ResponseBody
    public List<CheckedMenu> queryCheckedMenuList(
            @RequestParam("roleCode") String roleCode) {
        List<XN705001Res> allMenulist = menuAO.queryMenuList("", "", "", "");
        List<XN705010Res> roleMenuList = roleMenuAO.queryMenuList(roleCode);
        Map<String, String> roleMenuMap = new HashMap<String, String>();
        for (XN705010Res res : roleMenuList) {
            roleMenuMap.put(res.getMenuCode(), res.getMenuName());
        }
        List<CheckedMenu> resultList = new ArrayList<CheckedMenu>();
        for (XN705001Res result : allMenulist) {
            CheckedMenu lTreeRes = new CheckedMenu();
            lTreeRes.setId(result.getMenuCode());
            lTreeRes.setPid(result.getParentCode());
            lTreeRes.setText(result.getMenuName());
            if (roleMenuMap.containsKey(result.getMenuCode())) {
                lTreeRes.setIschecked(true);
            }
            resultList.add(lTreeRes);
        }
        return resultList;
    }

    @RequestMapping(value = "/menuRole/change", method = RequestMethod.POST)
    @ResponseBody
    public boolean changeMenuRole(@RequestParam("roleCode") String roleCode,
            @RequestParam("menuList[]") String[] menuList) {
        return roleMenuAO.changeMenuRole(roleCode, menuList, this
            .getSessionUser().getUserName());
    }

    // 角色本身增删改查
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN705007Res addRole(@RequestParam("roleCode") String roleCode,
            @RequestParam("roleName") String roleName,
            @RequestParam("roleLevel") String roleLevel,
            @RequestParam(value = "remark", required = false) String remark) {
        return roleAO.addRole(roleCode, roleName, roleLevel, this
            .getSessionUser().getUserName(), remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropRole(@RequestParam("roleCode") String roleCode) {
        XN705008Res res = roleAO.dropRole(roleCode);
        return res.getIsSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editRole(@RequestParam("roleCode") String roleCode,
            @RequestParam("roleName") String roleName,
            @RequestParam("roleLevel") String roleLevel,
            @RequestParam(value = "remark", required = false) String remark) {
        XN705009Res res = roleAO.editRole(roleCode, roleName, roleLevel, this
            .getSessionUser().getUserName(), remark);
        return res.getIsSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryRoleList(
            @RequestParam(value = "roleCode", required = false) String roleCode,
            @RequestParam(value = "roleLevel", required = false) String roleLevel,
            @RequestParam(value = "createDatetimeStart", required = false) String createDatetimeStart,
            @RequestParam(value = "createDatetimeEnd", required = false) String createDatetimeEnd) {
        return roleAO.queryRoleList(roleCode, roleLevel, createDatetimeStart,
            createDatetimeEnd);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryRolePage(
            @RequestParam(value = "roleCode", required = false) String roleCode,
            @RequestParam(value = "roleLevel", required = false) String roleLevel,
            @RequestParam(value = "createDatetimeStart", required = false) String createDatetimeStart,
            @RequestParam(value = "createDatetimeEnd", required = false) String createDatetimeEnd,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return roleAO.queryRolePage(roleCode, roleLevel, createDatetimeStart,
            createDatetimeEnd, start, limit, orderColumn, orderDir);
    }

}
