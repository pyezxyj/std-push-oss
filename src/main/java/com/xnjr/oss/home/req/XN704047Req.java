package com.xnjr.oss.home.req;

public class XN704047Req {
    // 编号（必填）
    private String code;

    // 名称（必填）
    private String name;
   

    // 链接（必填）
    private String url;

    // 显示状态（必填）
    private String status;

    // 显示位置（必填）
    private String orderNo;

    // 模版编号（必填）
    private String templetCode;

    // 子节点（必填）
    private String parentCode;

    private String contentType;

    private String companyCode;

    // 备注（选填）
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTempletCode() {
        return templetCode;
    }

    public void setTempletCode(String templetCode) {
        this.templetCode = templetCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}
