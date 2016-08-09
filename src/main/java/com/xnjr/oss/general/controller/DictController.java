package com.xnjr.oss.general.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.general.ao.IDictAO;
import com.xnjr.oss.general.res.XN707002Res;
import com.xnjr.oss.general.res.XN707003Res;
import com.xnjr.oss.general.res.XN707004Res;
import com.xnjr.oss.res.Page;

@Controller
@RequestMapping(value = "/general/dict")
public class DictController extends BaseController {
    @Autowired
    protected IDictAO dictAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN707002Res addDict(@RequestParam("pId") String pId,
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("type") String type,
            @RequestParam(value = "remark", required = false) String remark) {
        return dictAO.addDict(pId, key, value, this.getSessionUser()
            .getUserName(), remark, type);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropDict(@RequestParam("id") String id) {
        XN707003Res res = dictAO.dropDict(id);
        return res.getIsSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editDict(@RequestParam("id") String id,
            @RequestParam("pId") String pId, @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("type") String type,
            @RequestParam(value = "remark", required = false) String remark) {
        XN707004Res res = dictAO.editDict(id, pId, key, value, this
            .getSessionUser().getUserName(), remark, type);
        return res.getIsSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryDictPage(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "pId", required = false) String pId,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir) {
        return dictAO.queryDictPage(id, pId, key, type, start, limit,
            orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryDictList(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "pId", required = false) String pId,
            @RequestParam(value = "key", required = false) String key) {
        return dictAO.queryDictList(id, pId, key, type);
    }
}
