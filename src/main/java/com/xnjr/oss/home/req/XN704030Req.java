package com.xnjr.oss.home.req;

public class XN704030Req {

    // 名称（必填）
    private String name;

    // 职位（必填）
    private String position;

    // 照片（选填）
    private String picture;

    // 简介（必填）
    private String description;

    // 备注（选填）
    private String remark;

    // 公司编号（必填）
    private String companyCode;

    // 创建时间
    private String creatDatetime;

    // 状态
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCreatDatetime(String creatDatetime) {
        this.creatDatetime = creatDatetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
