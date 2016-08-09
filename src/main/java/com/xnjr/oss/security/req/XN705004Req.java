package com.xnjr.oss.security.req;

public class XN705004Req {
    // 菜单编号（必填）
    private String menuCode;

    // 菜单名称（必填）
    private String menuName;

    // 菜单链接地址（必填）
    private String menuUrl;

    // 父菜单编号（选填）
    private String parentCode;

    // 类型（必填）
    private String type;

    // 菜单位置编号（必填）
    private String orderNo;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
