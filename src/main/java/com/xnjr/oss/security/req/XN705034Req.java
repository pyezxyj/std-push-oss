package com.xnjr.oss.security.req;

public class XN705034Req {
    // 用户编号（必填）
    private String userCode;

    // 用户名称（必填）
    private String userName;

    // 状态：1、正常2、注销（必填）
    private String status;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
