/**
 * @Title ISysUserAO.java 
 * @Package com.xnjr.cpzc.ao 
 * @Description 
 * @author xieyj  
 * @date 2015年8月25日 下午3:07:46 
 * @version V1.0   
 */
package com.xnjr.oss.security.ao;

import java.util.List;

import com.xnjr.oss.res.Page;
import com.xnjr.oss.security.res.XN705032Res;
import com.xnjr.oss.security.res.XN705033Res;
import com.xnjr.oss.security.res.XN705034Res;
import com.xnjr.oss.security.res.XN705035Res;
import com.xnjr.oss.security.res.XN705036Res;
import com.xnjr.oss.security.res.XN705037Res;

/** 
 * 系统用户
 * @author: xieyj 
 * @since: 2015年8月25日 下午3:07:46 
 * @history:
 */
public interface IUserAO {

    /**
     * 用户登录
     * @param loginName 用户名
     * @param loginPwd 用户密码
     * @param loginIp ip地址
     * @return 
     * @create: 2015年8月17日 上午11:22:16 xieyj
     * @history:
     */
    public XN705035Res login(String userName, String pwd, String loginIp);

    /**
     * 更改密码
     * @param userCode
     * @param oldPwd
     * @param newPwd
     * @param updater
     * @return 
     * @create: 2015年10月13日 上午11:43:12 myb858
     * @history:
     */
    public XN705036Res changePsd(String userCode, String oldPwd, String newPwd);

    /**
     * 管理员重置密码
     * @param adminCode
     * @param adminPwd
     * @param userCode
     * @return 
     * @create: 2015年10月14日 上午11:42:57 myb858
     * @history:
     */
    public XN705037Res changePsdByAdmin(String adminCode, String adminPwd,
            String userCode);

    /**
     * 新增用户
     * @param userCode
     * @param userName
     * @param creator
     * @param remark
     * @return 
     * @create: 2015年10月13日 上午11:38:32 myb858
     * @history:
     */
    public XN705032Res addUser(String userCode, String userName,
            String creator, String remark);

    /**
     * 删除用户
     * @param userCode
     * @return 
     * @create: 2015年10月13日 上午11:14:05 myb858
     * @history:
     */
    public XN705033Res dropUser(String userCode);

    /**
     * 修改用户
     * @param userCode
     * @param userName
     * @param status
     * @param updater
     * @param remark
     * @return 
     * @create: 2015年10月14日 上午11:46:35 myb858
     * @history:
     */
    public XN705034Res editUser(String userCode, String userName,
            String status, String updater, String remark);

    /**
     * 查询用户
     * @param userCode
     * @param userName
     * @param status
     * @param orderDir 
     * @param orderColumn 
     * @param limit 
     * @param start 
     * @return 
     * @create: 2015年8月30日 下午8:56:36 yuexia
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public Page queryUserPage(String userCode, String userName, String status,
            String start, String limit, String orderColumn, String orderDir);

    /**
     * 查询用户列表
     * @param userCode
     * @param userName
     * @param status
     * @return 
     * @create: 2015年8月30日 下午9:48:57 yuexia
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public List queryUserList(String userCode, String userName, String status);

}
