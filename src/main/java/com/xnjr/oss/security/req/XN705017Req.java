package com.xnjr.oss.security.req;

public class XN705017Req {
    // 角色编号
    private String roleCode;

    // 父级菜单编号
    private String parentCode;

    // 菜单类型
    private String type;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
