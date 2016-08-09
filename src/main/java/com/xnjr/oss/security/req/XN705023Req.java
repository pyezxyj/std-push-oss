package com.xnjr.oss.security.req;

public class XN705023Req {

    // 用户编号（必填）
    private String userCode;

    // 角色编号（必填）
    private String roleCode;

    // 创建人（必填）
    private String creator;

    // 备注（选填）
    private String remark;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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

}
