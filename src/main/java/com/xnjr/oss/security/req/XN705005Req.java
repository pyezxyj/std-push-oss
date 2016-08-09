package com.xnjr.oss.security.req;

import com.xnjr.oss.req.APageReq;

public class XN705005Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 2033808282481867575L;

    // 角色编号（选填）
    private String roleCode;

    // 角色名称（选填）
    private String roleName;

    // 角色等级（选填）
    private String roleLevel;

    // 创建人（选填）
    private String creator;

    // 更新人（选填）
    private String updater;

    // 应用编号（必填）
    private String applyID;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }

}
