/**
 * @Title MemberController_haiqing.java 
 * @Package com.ibis.pz.controller 
 * @Description 
 * @author 茜茜  
 * @date 2015年3月12日 下午11:15:22 
 * @version V1.0   
 */
package com.xnjr.app.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.exception.BizException;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;
import com.xnjr.app.res.CheckedMenu;
import com.xnjr.app.security.ao.IMenuAO;
import com.xnjr.app.security.ao.IMenuRoleAO;
import com.xnjr.app.security.ao.IRoleAO;
import com.xnjr.app.security.ao.IUserAO;
import com.xnjr.app.security.res.XN805001Res;
import com.xnjr.app.security.res.XN805026Res;
import com.xnjr.app.security.res.XN805056Res;

/**
 * @author: XIANDONG
 * @since: 2016年4月18日 上午10:04:55
 * @history:
 */

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    protected IRoleAO roleAO;

    @Autowired
    protected IUserAO userAO;

    @Autowired
    protected IMenuAO menuAO;

    @Autowired
    protected IMenuRoleAO roleMenuAO;

    @RequestMapping(value = "/menuList", method = RequestMethod.GET)
    @ResponseBody
    public Object queryMenuList(@RequestParam("parentCode") String parentCode,
            @RequestParam("type") String type) {
        XN805056Res user = userAO.getUser(this.getSessionUser().getUserId());
        String roleCode = user.getRoleCode();
        if (StringUtils.isBlank(roleCode)) {
            throw new BizException("XN700001", "该用户角色为空");
        }
        return roleMenuAO.queryMenuList(roleCode, parentCode, type,
            user.getKind());
    }

    @RequestMapping(value = "/checkedList", method = RequestMethod.GET)
    @ResponseBody
    public List<CheckedMenu> queryCheckedMenuList(
            @RequestParam("roleCode") String roleCode,
            @RequestParam(value = "kind", required = false) String kind) {
        List<CheckedMenu> resultList = new ArrayList<CheckedMenu>();
        List<XN805001Res> allMenulist = menuAO.queryMenuList(kind, null, null,
            null, null, null);
        List<XN805026Res> roleMenuList = roleMenuAO.queryMenuList(roleCode,
            null, null, "");
        Map<String, String> roleMenuMap = new HashMap<String, String>();
        for (XN805026Res res : roleMenuList) {
            roleMenuMap.put(res.getCode(), res.getName());
        }
        for (XN805001Res result : allMenulist) {
            CheckedMenu lTreeRes = new CheckedMenu();
            lTreeRes.setId(result.getCode());
            lTreeRes.setPid(result.getParentCode());
            lTreeRes.setText(result.getName());
            if (roleMenuMap.containsKey(result.getCode())) {
                lTreeRes.setIschecked(true);
            }
            resultList.add(lTreeRes);
        }
        return resultList;
    }

    @RequestMapping(value = "/menuRole/change", method = RequestMethod.POST)
    @ResponseBody
    public Object changeMenuRole(
            @RequestParam("roleCode") String roleCode,
            @RequestParam(value = "menuList[]", required = false) String[] menuList) {
        if (menuList == null) {
            menuList = new String[1];
        }
        return roleMenuAO.changeMenuRole(roleCode, menuList, this
            .getSessionUser().getUserName());
    }

    // 角色本身增删改查
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object addRole(@RequestBody Map map) {
    	map.put("kind", "1");
        map.put("updater", this.getSessionUser().getUserName());
    	return BizConnecter.getBizData("805023", JsonUtils.mapToJson(map),
                Object.class);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object dropRole(@RequestBody Map map) {
    	return BizConnecter.getBizData("805024", JsonUtils.mapToJson(map),
                Object.class);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object editRole(@RequestBody Map map) {
    	map.put("kind", "1");
        map.put("updater", this.getSessionUser().getUserName());
    	return BizConnecter.getBizData("805025", JsonUtils.mapToJson(map),
                Object.class);
    }
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    @ResponseBody
//    public Object editRole(@RequestBody Map map) {
//    	map.put("updater", this.getSessionUser().getUserName());
//    	return BizConnecter.getBizData("805025", JsonUtils.mapToJson(map),
//                Object.class);
//    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object queryRoleList(
            @RequestParam(value = "kind", required = false) String kind,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "updater", required = false) String updater) {
        return roleAO.queryRoleList(kind, name, level, updater);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryRolePage(
            @RequestParam(value = "kind", required = false) String kind,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "level", required = false) String level,
            @RequestParam(value = "updater", required = false) String updater,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit) {
        return roleAO.queryRolePage(kind, name, level, updater, start, limit);
    }
    
//    @RequestMapping(value = "/page", method = RequestMethod.GET)
//    @ResponseBody
//    public Object queryRolePage(@RequestParam Map<String,String> allRequestParams) {
//    	return BizConnecter.getBizData("805020", JsonUtils.mapToJson(allRequestParams),
//                Object.class);
//    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public Object getRole(@RequestParam(value = "code") String code) {
        return roleAO.getRole(code);
    }
}
