package com.xnjr.app.req;

public class XNyw4030Req {

    // 投资用户编号(必填)
    private String userId;

    // 标的编号(必填)
    private String projectCode;

    // 投资金额
    private String nowAmount;

    // 投资说明
    private String nowNote;

    // 交易密码(必填)
    private String tradePwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getNowAmount() {
        return nowAmount;
    }

    public void setNowAmount(String nowAmount) {
        this.nowAmount = nowAmount;
    }

    public String getNowNote() {
        return nowNote;
    }

    public void setNowNote(String nowNote) {
        this.nowNote = nowNote;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }
}
