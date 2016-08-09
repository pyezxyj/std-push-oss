package com.xnjr.oss.security.req;

import com.xnjr.oss.req.APageReq;

public class XN705000Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 5138736221155343722L;

    // 菜单编号(选填)
    private String menuCode;

    // 菜单名称(选填)
    private String menuName;

    // 菜单Url(选填)
    private String menuUrl;

    // 父菜单编号(选填)
    private String parentCode;

    // 类型(选填)
    private String type;

    // 创建人(选填)
    private String creator;

    // 更新人(选填)
    private String updater;

    // 应用编号（必填）
    private String applyID;

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
