package com.xnjr.app.req;

public class XNyw4062Req {
    // 业务编号
    private String businessCode;

    // 审核人
    private String checkUser;

    // 审核结果
    private String checkResult;

    // 操盘手密码
    private String tradePwd;

    // 备注
    private String checkNote;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

	public String getCheckNote() {
		return checkNote;
	}

	public void setCheckNote(String checkNote) {
		this.checkNote = checkNote;
	}


}
