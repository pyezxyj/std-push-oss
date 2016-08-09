package com.xnjr.oss.security.req;

public class XN705031Req {
    // 用户编号（选填）
    private String userCode;

    // 用户名称（选填）
    private String userName;

    // 密码（选填）
    private String password;

    // 状态：1、正常2、注销（选填）
    private String status;

    // 创建起始时间(YYYY-MM-DD)（选填）
    private String dateStart;

    // 创建终止时间(YYYY-MM-DD)（选填）
    private String dateEnd;

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

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }

}
