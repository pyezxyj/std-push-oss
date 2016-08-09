package com.xnjr.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xnjr.oss.session.ISessionProvider;
import com.xnjr.oss.session.SessionUser;

/**
 * 跳转页面Controller
 * 
 * @author zhanggl10620
 * 
 */
@Controller
public class PageController {

    private static final String SESSION_KEY_USER = "user";

    @Autowired
    protected ISessionProvider sessionProvider;

    @RequestMapping(value = "/security/{view}.htm", method = RequestMethod.GET)
    public String securityAction(@PathVariable("view") String view) {
        return "security/" + view;
    }

    @RequestMapping(value = "/general/{view}.htm", method = RequestMethod.GET)
    public String general(@PathVariable("view") String view) {
        return "general/" + view;
    }

    @RequestMapping(value = "/web/{view}.htm", method = RequestMethod.GET)
    public String webAction(@PathVariable("view") String view) {
        return "web/" + view;
    }

    @RequestMapping(value = "/attach/{view}.htm", method = RequestMethod.GET)
    public String attachAction(@PathVariable("view") String view) {
        return "attach/" + view;
    }

    @RequestMapping(value = "/plat/{view}.htm", method = RequestMethod.GET)
    public String platAction(@PathVariable("view") String view) {
        return "plat/" + view;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String page() {
        SessionUser user = (SessionUser) sessionProvider
            .getAttribute(SESSION_KEY_USER);
        if (null == user) {
            return "redirect:security/signin.htm";
        }
        return "redirect:security/main.htm";
    }
}
