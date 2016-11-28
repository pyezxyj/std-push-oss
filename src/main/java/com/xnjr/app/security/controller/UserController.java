package com.xnjr.app.security.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.xnjr.app.controller.BaseController;
import com.xnjr.app.enums.EUserKind;
import com.xnjr.app.enums.EUserStatus;
import com.xnjr.app.exception.BizException;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;
import com.xnjr.app.security.ao.IMenuRoleAO;
import com.xnjr.app.security.ao.IRoleAO;
import com.xnjr.app.security.ao.IUserAO;
import com.xnjr.app.security.res.XN805043Res;
import com.xnjr.app.security.res.XN805056Res;
import com.xnjr.app.session.ISessionProvider;
import com.xnjr.app.session.SessionUser;

/**
 * @author: XIANDONG 
 * @since: 2016年4月17日 下午6:46:27 
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
    protected IMenuRoleAO roleMenuAO;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(@RequestParam("loginName") String loginName,
            @RequestParam("loginPwd") String loginPwd) {
        // 校验用户名密码
        XN805043Res res = userAO.login(loginName, loginPwd);
        // 创建session
        sessionProvider
            .setUserDetail(new SessionUser(res.getUserId(), loginName));
        return true;
    }
    
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public Object doLogin(@RequestBody Map map) {
//        // 校验用户名密码
//        XN805043Res res = userAO.login((String)map.get("loginName"), (String)map.get("loginPwd"));
//        // 创建session
//        sessionProvider
//            .setUserDetail(new SessionUser(res.getUserId(), (String)map.get("loginName")));
//        return true;
//    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public boolean logout() {
        sessionProvider.removeAttribute(ISessionProvider.SESSION_KEY_USER);
        return true;
    }

    @RequestMapping(value = "/pwd/change", method = RequestMethod.POST)
    @ResponseBody
    public Object changePwd(@RequestParam("oldLoginPwd") String oldLoginPwd,
            @RequestParam("newLoginPwd") String newLoginPwd) {
        return userAO.changeLoginPwd(this.getSessionUser().getUserId(),
            oldLoginPwd, newLoginPwd);
    }

    @RequestMapping(value = "/pwd/reset", method = RequestMethod.POST)
    @ResponseBody
    public Map resetPwd(@RequestBody Map map) {
    	return BizConnecter.getBizData("805078", JsonUtils.mapToJson(map),
                Map.class);
    }

    @RequestMapping(value = "/tradePwd/change", method = RequestMethod.POST)
    @ResponseBody
    public Object changeTradePwd(
            @RequestParam("oldTradePwd") String oldTradePwd,
            @RequestParam("newTradePwd") String newTradePwd) {
        return userAO.changeTradePwd(this.getSessionUser().getUserId(),
            oldTradePwd, newTradePwd);
    }

    @RequestMapping(value = "/tradePwd/reset", method = RequestMethod.POST)
    @ResponseBody
    public Object resetTradePwd(@RequestParam("newTradePwd") String newTradePwd,
            @RequestParam("smsCaptcha") String smsCaptcha,
            @RequestParam("idKind") String idKind,
            @RequestParam("idNo") String idNo) {
        return userAO.resetTradePwd(this.getSessionUser().getUserId(),
            newTradePwd, smsCaptcha, idKind, idNo);
    }

    @RequestMapping(value = "/role/change", method = RequestMethod.POST)
    @ResponseBody
    public Object changeUserRole(@RequestParam("userId") String userId,
            @RequestParam("roleCode") String roleCode,
            @RequestParam(value = "remark", required = false) String remark) {
        return userAO.allotRole(userId, roleCode,
            this.getSessionUser().getUserName(), remark);
    }

    // 权限--UI显示
    @RequestMapping(value = "/roleMenu/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryRoleMenu(
            @RequestParam(value = "parentCode", required = true) String parentCode,
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "isGetChild", required = true) boolean isGetChild) {
        XN805056Res user = userAO.getUser(this.getSessionUser().getUserId());
        String roleCode = user.getRoleCode();
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "该用户角色为空");
        }
        return roleMenuAO.queryMenuList(roleCode, parentCode, type, isGetChild);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map addUser(@RequestBody Map map) {
    	map.put("kind", EUserKind.Operator.getCode());
    	map.put("updater", this.getSessionUser().getUserName());
    	Map user = BizConnecter.getBizData("805042", JsonUtils.mapToJson(map),
                Map.class);
        userAO.allotRole((String)user.get("userId"), (String)map.get("roleCode"),
                this.getSessionUser().getUserId(), (String)map.get("remark"));
        return user;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Map cancelUser(@RequestBody Map map) {
    	map.put("toStatus", "2");
    	map.put("updater", this.getSessionUser().getUserName());
    	return BizConnecter.getBizData("805052", JsonUtils.mapToJson(map),
                Map.class);
    }

    @RequestMapping(value = "/active", method = RequestMethod.POST)
    @ResponseBody
    public Object activeUser(@RequestParam("userId") String userId,
            @RequestParam(value = "remark", required = false) String remark) {
        return userAO.activeUser(userId, this.getSessionUser().getUserName(),
            remark);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryUserList(
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "userReferee", required = false) String userReferee,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "idKind", required = false) String idKind,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "roleCode", required = false) String roleCode,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "updater", required = false) String updater) {
        return userAO.queryUserList(loginName, EUserKind.Operator.getCode(),
            level, userReferee, mobile, idKind, idNo, realName, roleCode,
            status, updater);
    }

    @RequestMapping(value = "/under/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryUnderUserList() {
        return userAO.queryUserList(null, EUserKind.XiaJia.getCode(), null,
            this.getSessionUser().getUserId(), null, null, null, null, null,
            null, null);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryUserPage(
            @RequestParam(value = "loginName", required = false) String loginName,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "userReferee", required = false) String userReferee,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "idKind", required = false) String idKind,
            @RequestParam(value = "idNo", required = false) String idNo,
            @RequestParam(value = "realName", required = false) String realName,
            @RequestParam(value = "roleCode", required = false) String roleCode,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "updater", required = false) String updater,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return userAO.queryUserPage(loginName, EUserKind.Operator.getCode(),
            level, userReferee, mobile, idKind, idNo, realName, roleCode,
            status, updater, start, limit);
    }
    
    // 查询终端用户
    @RequestMapping(value = "/terminal/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryTerminalUserPage(
            @RequestParam(value = "userReferee", required = false) String userReferee,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return userAO.queryTerminalUserPage(userReferee, mobile, start, limit);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object getDetailUser(@RequestParam("userId") String userId) {
        return userAO.getUser(userId);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Object getUser() {
        return userAO.getUser(this.getSessionUser().getUserId());
    }

    @RequestMapping(value = "/editMobile", method = RequestMethod.POST)
    @ResponseBody
    public Object editMobile(
            @RequestParam(value = "newMobile") String newMobile,
            @RequestParam(value = "smsCaptcha") String smsCaptcha,
            @RequestParam(value = "tradePwd") String tradePwd) {
        return userAO.editMobile(this.getSessionUser().getUserId(), newMobile,
            smsCaptcha, tradePwd);
    }
    
    // 找回密码
    @RequestMapping(value = "/pwd/find", method = RequestMethod.POST)
    @ResponseBody
    public Object findPwd(
            @RequestParam(value = "loginName") String loginName,
            @RequestParam(value = "smsCaptcha") String smsCaptcha,
            @RequestParam(value = "newLoginPwd") String newLoginPwd) {
        return userAO.findPwd(loginName, smsCaptcha, newLoginPwd);
    }
    
    // 找回密码发短信
    @RequestMapping(value = "/pwd/find/sms", method = RequestMethod.POST)
    @ResponseBody
    public Object findPwdSMS(
            @RequestParam(value = "loginName") String loginName) {
        return userAO.findPwdSMS(loginName);
    }
    
    // 等级新增
    @RequestMapping(value = "/level/add", method = RequestMethod.POST)
    @ResponseBody
    public Object levelAdd(@RequestBody Map map) {
  		return BizConnecter.getBizData("805110", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 等级删除
    @RequestMapping(value = "/level/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object levelDelete(@RequestBody Map map) {
  		return BizConnecter.getBizData("805111", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 等级修改
    @RequestMapping(value = "/level/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object levelEdit(@RequestBody Map map) {
  		return BizConnecter.getBizData("805112", JsonUtils.mapToJson(map),
              Object.class);
	}
    
    // 等级分页
    @RequestMapping(value = "/level/page", method = RequestMethod.GET)
    @ResponseBody
    public Object levelPage(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("805113", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
    // 等级详情
    @RequestMapping(value = "/level/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object levelList(@RequestParam Map<String,String> allRequestParams) {
  	    return BizConnecter.getBizData("805114", JsonUtils.mapToJson(allRequestParams),
              Object.class);
    }
    
}
