package com.xnjr.oss.security.req;

public class XN705036Req {
    // userId
    private String userCode;

    // 原登录密码
    private String oldPwd;

    // 新登录密码
    private String newPwd;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

}
