package com.xnjr.oss.security.req;

public class XN705035Req {
    // 登陆名（必填）
    private String loginName;

    // 登陆密码（必填）
    private String loginPwd;

    // 登陆ip（必填）
    private String loginIp;

    // 应用编号（必填）
    private String applyID;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }
}
