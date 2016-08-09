/**
 * @Title IRoleAO.java 
 * @Package com.ibis.pz.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-14 上午9:16:25 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao;

import java.util.List;

import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.res.XN705007Res;
import com.xnjr.oss.security.res.XN705008Res;
import com.xnjr.oss.security.res.XN705009Res;

/** 
 * @author: miyb 
 * @since: 2015-5-14 上午9:16:25 
 * @history:
 */
public interface IRoleAO {
    /**
     * 新增角色
     * @param roleCode
     * @param roleName
     * @param roleLevel
     * @param creator
     * @param remark
     * @return 
     * @create: 2015年10月13日 上午11:13:02 myb858
     * @history:
     */
    public XN705007Res addRole(String roleCode, String roleName,
            String roleLevel, String creator, String remark);

    /**
     * 删除角色
     * @param roleCode
     * @return 
     * @create: 2015年10月13日 上午11:14:05 myb858
     * @history:
     */
    public XN705008Res dropRole(String roleCode);

    /**
     * 修改角色
     * @param roleCode
     * @param roleName
     * @param roleLevel
     * @param updater
     * @param remark
     * @return 
     * @create: 2015年10月13日 上午11:13:57 myb858
     * @history:
     */
    public XN705009Res editRole(String roleCode, String roleName,
            String roleLevel, String updater, String remark);

    /**
     * 分页查询
     * @param roleCode
     * @param roleLevel
     * @param createDatetimeStart
     * @param createDatetimeEnd
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2015年10月13日 上午11:15:08 myb858
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public Page queryRolePage(String roleCode, String roleLevel,
            String createDatetimeStart, String createDatetimeEnd, String start,
            String limit, String orderColumn, String orderDir);

    /**
     * 按条件获取所有的角色
     * @param roleCode
     * @param roleLevel
     * @param createDatetimeStart
     * @param createDatetimeEnd
     * @return 
     * @create: 2015年10月13日 上午11:15:39 myb858
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public List queryRoleList(String roleCode, String roleLevel,
            String createDatetimeStart, String createDatetimeEnd);

}
