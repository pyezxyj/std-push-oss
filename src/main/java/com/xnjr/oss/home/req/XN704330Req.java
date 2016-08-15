package com.xnjr.oss.home.req;

import com.xnjr.oss.req.APageReq;

/**
 * 分页查询首页
 * @author: xieyj 
 * @since: 2016年8月15日 下午7:40:29 
 * @history:
 */
public class XN704330Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 编号(选填)
    private String code;

    // 所属公司编号(必填)
    private String companyCode;

    // 更新人(选填)
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
