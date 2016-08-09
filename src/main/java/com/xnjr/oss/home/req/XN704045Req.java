package com.xnjr.oss.home.req;

public class XN704045Req {
    // 名称（必填）
    private String name;

    // 显示位置（必填）
    private String orderNo;
    
   // 父编号（必填）
    private String parentCode;

    // 模版编号（必填）
    private String contentType;

    // 内容源获取方式（必填）
    private String templetCode;

    // 公司编号（必填）
    private String companyCode;

    private String userid;

    // 备注（选填）
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTempletCode() {
        return templetCode;
    }

    public void setTempletCode(String templetCode) {
        this.templetCode = templetCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
