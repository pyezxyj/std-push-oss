package com.xnjr.oss.security.req;

public class XN705037Req {
    // 管理员账号
    private String adminCode;

    // 管理员密码
    private String adminPwd;

    // 被重置密码的用户名
    private String userCode;

    // 被重置后的密码
    private String userPwd;

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

}
