package com.xnjr.oss.base;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.ServletContextAware;

/**
 * 配置静态文件工具类
 * @author: myb858 
 * @since: 2015年9月6日 下午7:14:34 
 * @history:
 */
public class ApplicationProperties implements ServletContextAware {

    private ServletContext servletContext;

    @Value("${oss.applyID}")
    private String applyID;

    @Value("${web.version}")
    private String version;

    public ServletContext getServletContext() {
        return servletContext;
    }

    public String getApplyID() {
        return applyID;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public void setServletContext(ServletContext context) {
        context.setAttribute("applyID", applyID);
        context.setAttribute("version", version);
    }
}
