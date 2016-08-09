package com.xnjr.oss.home.req;

import com.xnjr.oss.req.APageReq;

public class XN704043Req extends APageReq {

    // 编号
    private String code;

    // 涓婚〉
    private String home;

    // 瀵艰埅鏍峰紡
    private String menu;

    // 鑹茶皟
    private String color;

    // 甯冨眬
    private String layout;

    // 鍒涘缓鑰�
    private String creator;

    // 淇敼鑰�
    private String updater;

    private String companyCode;

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

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
