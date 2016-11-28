package com.xnjr.app.general.ao;

/** 
 * @author: miyb 
 * @since: 2015-5-12 下午1:42:52 
 * @history:
 */
public interface ISmsAO {
    /**
     * 发送短信验证码(校验短信验证码包含在具体业务中)
     * @param mobile
     * @param bizType 
     * @create: 2015-3-13 下午12:41:18 xieyj
     * @history:
     */
    public void sendSmsCaptcha(String mobile, String bizType);

}
