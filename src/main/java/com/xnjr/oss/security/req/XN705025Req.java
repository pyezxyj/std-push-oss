package com.xnjr.oss.security.req;

public class XN705025Req {
    // 主键编号（必填）
    private String id;

    // 用户编号（必填）
    private String userCode;

    // 角色编号（必填）
    private String roleCode;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
