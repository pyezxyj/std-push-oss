package com.xnjr.app.account;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.app.controller.BaseController;
import com.xnjr.app.http.BizConnecter;
import com.xnjr.app.http.JsonUtils;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {
	
	@RequestMapping(value = "/system", method = RequestMethod.GET)
    @ResponseBody
    public Object detailsystemaccount(@RequestParam Map<String,String> allRequestParams) {
		return BizConnecter.getBizData("802011", JsonUtils.mapToJson(allRequestParams),
	              Object.class);
    }
	
	@RequestMapping(value = "/jour/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryAJourPage(@RequestParam Map<String,String> allRequestParams) {
		return BizConnecter.getBizData("802020", JsonUtils.mapToJson(allRequestParams),
	              Object.class);
    }

    @RequestMapping(value = "/frozen/jour/page", method = RequestMethod.GET)
    @ResponseBody
    public Object queryFrozenAccountPag(@RequestParam Map<String,String> allRequestParams) {
    	return BizConnecter.getBizData("802030", JsonUtils.mapToJson(allRequestParams),
	              Object.class);
    }
}
