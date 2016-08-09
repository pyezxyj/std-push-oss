/**
 * @Title IMenuRoleAO.java 
 * @Package com.ibis.pz.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-14 上午9:17:21 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao;

import java.util.List;

import com.xnjr.oss.security.res.XN705010Res;
import com.xnjr.oss.security.res.XN705012Res;
import com.xnjr.oss.security.res.XN705013Res;
import com.xnjr.oss.security.res.XN705014Res;
import com.xnjr.oss.security.res.XN705015Res;

/** 
 * @author: miyb 
 * @since: 2015-5-14 上午9:17:21 
 * @history:
 */
public interface IMenuRoleAO {
    /**
     * 给角色授权具体的菜单资源（先删后增）
     * @param roleCode
     * @param menuList
     * @param creator
     * @return 
     * @create: 2015年10月19日 下午4:12:31 myb858
     * @history:
     */
    public boolean changeMenuRole(String roleCode, String[] menuList,
            String creator);

    /**
     * 获取用户所对应的menu list
     * @param roleCode 角色编号
     * @param parentCode 要返回menu的父亲菜单编号---查询条件之一
     * @param type 要返回menu的类型---查询条件之一
     * @param isGetChild 要返回menu是否需要带有子菜单---查询条件之一
     * @return 
     * @create: 2015年10月20日 下午4:10:24 myb858
     * @history:
     */
    public List<XN705010Res> queryMenuList(String roleCode, String parentCode,
            String type, boolean isGetChild);

    public XN705013Res addMenuRole(String roleCode, String menuCode,
            String creator, String remark);

    public XN705014Res dropMenuRole(String id);

    public XN705015Res editMenuRole(String id, String roleCode,
            String menuCode, String updater, String remark);

    public List<XN705010Res> queryMenuList(String roleCode);

    public List queryRoleList(String menuCode);

    public XN705012Res getMenuRole(String id);

    /**
     * 获取下级权限
     * @param roleCode 角色编号
     * @param parentCode 父菜单编号
     * @param type 类型(1菜单/2按钮)
     * @return 
     * @create: 2015年12月2日 下午7:34:35 xieyj
     * @history:
     */
    public List queryPermissionList(String roleCode, String parentCode,
            String type);

}
