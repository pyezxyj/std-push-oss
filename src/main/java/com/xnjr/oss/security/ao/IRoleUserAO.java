package com.xnjr.oss.security.ao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xnjr.oss.security.res.XN705021Res;
import com.xnjr.oss.security.res.XN705022Res;
import com.xnjr.oss.security.res.XN705023Res;
import com.xnjr.oss.security.res.XN705024Res;
import com.xnjr.oss.security.res.XN705025Res;

@Service
public interface IRoleUserAO {
    /**
     * 给用户赋值角色（先删后增）
     * @param userCode
     * @param roleCode
     * @param creator
     * @return 
     * @create: 2015年10月19日 下午4:26:47 myb858
     * @history:
     */
    public boolean changeUserRole(String userCode, String roleCode,
            String creator);

    /**
     * 根据用户获取单一角色
     * @param userCode
     * @return 
     * @create: 2015年10月20日 下午4:21:15 myb858
     * @history:
     */
    public XN705021Res getRole(String userCode);

    public XN705023Res addUserRole(String userCode, String roleCode,
            String creator, String remark);

    public XN705024Res dropUserRole(String id);

    public XN705025Res editUserRole(String id, String userCode,
            String roleCode, String updater, String remark);

    public List queryRoleList(String userCode);

    public List queryUserList(String roleCode);

    public XN705022Res getUserRole(String id);

}
