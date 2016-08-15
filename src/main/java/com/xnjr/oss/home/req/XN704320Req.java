package com.xnjr.oss.home.req;

/**
 * 修改首页
 * @author: xieyj 
 * @since: 2016年8月15日 下午7:40:29 
 * @history:
 */
public class XN704320Req {
	
	private String code;
    // banner1(必填)
    private String banner1;

    // banner2(必填)
    private String banner2;

    // banner3(必填)
    private String banner3;

    // 全尺寸图(选填)
    private String fullSizePic;

    // url(必填)
    private String url;

    // url 文本(必填)
    private String urlText;

    // 所属公司编号(必填)
    private String companyCode;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    public String getBanner1() {
        return banner1;
    }

    public void setBanner1(String banner1) {
        this.banner1 = banner1;
    }

    public String getBanner2() {
        return banner2;
    }

    public void setBanner2(String banner2) {
        this.banner2 = banner2;
    }

    public String getBanner3() {
        return banner3;
    }

    public void setBanner3(String banner3) {
        this.banner3 = banner3;
    }

    public String getFullSizePic() {
        return fullSizePic;
    }

    public void setFullSizePic(String fullSizePic) {
        this.fullSizePic = fullSizePic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlText() {
        return urlText;
    }

    public void setUrlText(String urlText) {
        this.urlText = urlText;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
