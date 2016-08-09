package com.xnjr.oss.security.req;

public class XN705007Req {
    // 角色编号（必填）
    private String roleCode;

    // 角色名称（必填）
    private String roleName;

    // 角色等级（必填）
    private String roleLevel;

    // 创建人（必填）
    private String creator;

    // 备注（选填）
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }
}
