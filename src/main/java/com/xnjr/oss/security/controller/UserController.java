package com.xnjr.oss.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.ao.IMenuRoleAO;
import com.xnjr.oss.security.ao.IRoleAO;
import com.xnjr.oss.security.ao.IRoleUserAO;
import com.xnjr.oss.security.ao.IUserAO;
import com.xnjr.oss.security.res.XN705010Res;
import com.xnjr.oss.security.res.XN705021Res;
import com.xnjr.oss.security.res.XN705032Res;
import com.xnjr.oss.security.res.XN705034Res;
import com.xnjr.oss.security.res.XN705035Res;
import com.xnjr.oss.security.res.XN705036Res;
import com.xnjr.oss.security.res.XN705037Res;
import com.xnjr.oss.session.ISessionProvider;
import com.xnjr.oss.session.SessionUser;

/**
 * 系统用户模块
 * @author: xieyj 
 * @since: 2015年8月16日 下午1:41:04 
 * @history:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    protected IRoleAO roleAO;

    @Autowired
    protected IUserAO userAO;

    @Autowired
    protected IRoleUserAO roleUserAO;

    @Autowired
    protected IMenuRoleAO roleMenuAO;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean doLogin(@RequestParam("loginName") String loginName,
            @RequestParam("loginPwd") String loginPwd) {
        // 校验用户名密码
        XN705035Res res = userAO.login(loginName, loginPwd, getRemoteHost());
        // 创建session
        sessionProvider.setUserDetail(new SessionUser(res.getUserCode(),
            loginName));
        return true;
    }

    /**
     * 用户注销
     * @param loginName
     * @param loginPwd
     * @return 
     * @create: 2015年9月18日 上午11:02:40 haiqingzheng
     * @history:
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public boolean logout() {
        sessionProvider.removeAttribute(ISessionProvider.SESSION_KEY_USER);
        return true;
    }

    @RequestMapping(value = "/pwd/change", method = RequestMethod.POST)
    @ResponseBody
    public boolean changePsd(@RequestParam("oldPwd") String oldPwd,
    		
            @RequestParam("newPwd") String newPwd
            ) {
        XN705036Res res = userAO.changePsd(this.getSessionUser().getUserCode(),
            oldPwd, newPwd);
        return res.getIsSuccess();
    }

    @RequestMapping(value = "/pwd/reset", method = RequestMethod.POST)
    @ResponseBody
    public boolean changePsdByAdmin(@RequestParam("adminPwd") String adminPwd,
            @RequestParam("userCode") String userCode) {
        XN705037Res res = userAO.changePsdByAdmin(this.getSessionUser()
            .getUserCode(), adminPwd, userCode);
        return res.getIsSuccess();
    }

    // 给用户添加角色
    @RequestMapping(value = "/roleList", method = RequestMethod.GET)
    @ResponseBody
    public List queryRoleList(@RequestParam("userCode") String userCode) {
        return roleUserAO.queryRoleList(userCode);
    }

    @RequestMapping(value = "/userRole/changeRole", method = RequestMethod.POST)
    @ResponseBody
    public boolean changeUserRole(@RequestParam("userCode") String userCode,
            @RequestParam("roleCode") String roleCode) {
        return roleUserAO.changeUserRole(userCode, roleCode, this
            .getSessionUser().getUserName());
    }

    // 权限--UI显示
    @RequestMapping(value = "/roleMenu/list", method = RequestMethod.GET)
    @ResponseBody
    public List<XN705010Res> queryRoleMenu(
            @RequestParam(value = "parentCode", required = true) String parentCode,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "isGetChild", required = true) boolean isGetChild) {
        XN705021Res role = roleUserAO.getRole(this.getSessionUser()
            .getUserCode());
        return roleMenuAO.queryMenuList(role.getRoleCode(), parentCode, type,
            isGetChild);
    }

    // 按钮权限获取
    @RequestMapping(value = "/permission/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryPermission(
            @RequestParam(value = "parentCode", required = false) String parentCode,
            @RequestParam(value = "type", required = false) String type) {
        XN705021Res role = roleUserAO.getRole(this.getSessionUser()
            .getUserCode());
        return roleMenuAO.queryPermissionList(role.getRoleCode(), parentCode,
            type);
    }

    // 用户本身增删改查
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN705032Res addUser(@RequestParam("userCode") String userCode,
            @RequestParam("userName") String userName,
            @RequestParam(value = "remark", required = false) String remark) {
        return userAO.addUser(userCode, userName, this.getSessionUser()
            .getUserName(), remark);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropUser(@RequestParam("userCode") String userCode) {
        return userAO.dropUser(userCode).getIsSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public XN705034Res editUser(@RequestParam("userCode") String userCode,
            @RequestParam("userName") String userName,
            @RequestParam("status") String status,
            @RequestParam(value = "remark", required = false) String remark) {
        return userAO.editUser(userCode, userName, status, this
            .getSessionUser().getUserName(), remark);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryUserList(
            @RequestParam(value = "userCode", required = false) String userCode,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return userAO.queryUserList(userCode, userName, status);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryUserPage(
            @RequestParam(value = "userCode", required = false) String userCode,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return userAO.queryUserPage(userCode, userName, status, start, limit,
            orderColumn, orderDir);
    }

}
