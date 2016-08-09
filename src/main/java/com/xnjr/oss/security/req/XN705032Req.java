package com.xnjr.oss.security.req;

public class XN705032Req {
    // 用户编号（必填）
    private String userCode;

    // 用户名称（必填）
    private String userName;

    // 密码（必填）
    private String password;

    // 状态：1、正常2、注销（必填）
    private String status;

    // 创建人（必填）
    private String creator;

    // 备注（选填）
    private String remark;

    // 应用编号（必填）
    private String applyID;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
