/**
 * @Title ESmsBizType.java 
 * @Package com.ibis.pz.enums 
 * @Description 
 * @author miyb  
 * @date 2015-4-14 下午2:45:40 
 * @version V1.0   
 */
package com.xnjr.app.enums;

/** 
 * @author: miyb 
 * @since: 2015-4-14 下午2:45:40 
 * @history:
 */
public enum ESmsBizType {
    REGISTER("805041", "注册"), FINDLOGINPWD("805048", "找回登陆密码"), RESETLOGINPWD(
            "3", "重置登陆密码"), SETTRADEPWD("805045", "设置交易密码"), FINDTRADEPWD("5",
            "找回交易密码"), RESETTRADEPWD("6", "重置交易密码"), CHANGEMOBILE("805047",
            "修改手机号码");

    ESmsBizType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
