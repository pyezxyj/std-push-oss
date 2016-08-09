/**
 * @Title IMenuAO.java 
 * @Package com.ibis.pz.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-14 上午9:16:37 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao;

import java.util.List;

import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.res.XN705001Res;
import com.xnjr.oss.security.res.XN705002Res;
import com.xnjr.oss.security.res.XN705003Res;
import com.xnjr.oss.security.res.XN705004Res;

/** 
 * @author: miyb 
 * @since: 2015-5-14 上午9:16:37 
 * @history:
 */
public interface IMenuAO {

    /**
     * 添加菜单
     * @param menuCode
     * @param menuName
     * @param menuUrl
     * @param parentCode
     * @param type
     * @param orderNo
     * @param creator
     * @param remark
     * @return 
     * @create: 2015年10月12日 下午2:20:12 myb858
     * @history:
     */
    public XN705002Res addMenu(String menuCode, String menuName,
            String menuUrl, String parentCode, String type, String orderNo,
            String creator, String remark);

    /**
     * 删除菜单
     * @param menuCode
     * @return 
     * @create: 2015年10月12日 下午2:31:26 myb858
     * @history:
     */
    public XN705003Res dropMenu(String menuCode);

    /**
     * 修改菜单
     * @param menuCode
     * @param menuName
     * @param menuUrl
     * @param parentCode
     * @param type
     * @param orderNo
     * @param updater
     * @param remark
     * @return 
     * @create: 2015年10月12日 下午2:36:36 myb858
     * @history:
     */
    public XN705004Res editMenu(String menuCode, String menuName,
            String menuUrl, String parentCode, String type, String orderNo,
            String updater, String remark);

    /**
     * 分页查询菜单
     * @param menuCode
     * @param menuUrl
     * @param parentCode
     * @param type
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2015年10月13日 上午10:38:38 myb858
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public Page queryMenuPage(String menuCode, String menuUrl,
            String parentCode, String type, String start, String limit,
            String orderColumn, String orderDir);

    /**
     * 查询菜单列表
     * @param menuCode
     * @param menuUrl
     * @param parentCode
     * @param type
     * @return 
     * @create: 2015年10月13日 上午10:38:09 myb858
     * @history:
     */
    public List<XN705001Res> queryMenuList(String menuCode, String menuUrl,
            String parentCode, String type);

}
