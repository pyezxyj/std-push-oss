/**
 * @Title BizConnecter.java 
 * @Package com.ibis.pz.http 
 * @Description 
 * @author miyb  
 * @date 2015-5-12 下午9:44:59 
 * @version V1.0   
 */
package com.xnjr.oss.http;

import java.util.Properties;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.util.PropertiesUtil;
import com.xnjr.oss.util.RegexUtils;

/** 
 * @author: miyb 
 * @since: 2015-5-12 下午9:44:59 
 * @history:
 */
public class BizConnecter {
    public static final String YES = "0";

    public static final String SEC_URL = PropertiesUtil.getProperty("secUrl");

    public static final String HOME_URL = PropertiesUtil.getProperty("homeUrl");

    public static final String BIZFRAME_URL = PropertiesUtil
        .getProperty("bizFrameUrl");

    public static <T> T getBizData(String code, String json, Class<T> clazz) {
        String data = getBizData(code, json);
        return JsonUtils.json2Bean(data, clazz);
    }

    public static String getBizData(String code, String json) {
        String data = null;
        String resJson = null;
        try {
            Properties formProperties = new Properties();
            formProperties.put("code", code);
            formProperties.put("json", json);
            resJson = PostSimulater.requestPostForm(getPostUrl(code),
                formProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 开始解析响应json
        String errorCode = RegexUtils.find(resJson, "errorCode\":\"(.+?)\"", 1);
        String errorInfo = RegexUtils.find(resJson, "errorInfo\":\"(.+?)\"", 1);
        System.out.println("request:" + code + " with parameters " + json
                + "\nresponse:" + errorCode + "<" + errorInfo + ">.");
        if (YES.equalsIgnoreCase(errorCode)) {
            data = RegexUtils.find(resJson, "data\":(.*)\\}", 1);
        } else {
            throw new BizException("Biz000", errorInfo);
        }
        return data;
    }

    public static String getPostUrl(String code) {
        String postUrl = null;
        if (code.startsWith("705")) {
            postUrl = SEC_URL;
        } else if (code.startsWith("704")) {
            postUrl = HOME_URL;
        } else if (code.startsWith("707")) {
            postUrl = BIZFRAME_URL;
        }
        return postUrl;
    }
}
