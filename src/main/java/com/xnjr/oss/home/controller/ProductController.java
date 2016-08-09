package com.xnjr.oss.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xnjr.oss.controller.BaseController;
import com.xnjr.oss.home.ao.IProductAO;
import com.xnjr.oss.home.res.XN704005Res;
import com.xnjr.oss.home.res.XN704006Res;
import com.xnjr.oss.home.res.XN704007Res;
import com.xnjr.oss.res.Page;

/**
 * 产品控制器
 * @author: jlxuu
 * @since: 2015年10月27日 上午9:36:42 
 * @history:
 */

@Controller
@RequestMapping(value = "/web/product")
public class ProductController extends BaseController {
    @Autowired
    protected IProductAO productAO;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public XN704005Res addProduct(@RequestParam("name") String name,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam("description") String description,
            @RequestParam("kind") String kind,
            @RequestParam("status") String status,
            @RequestParam("companyCode") String companyCode) {
        return productAO.addProduct(name, picture, description, kind, status,
            companyCode);
    }

    @RequestMapping(value = "/drop", method = RequestMethod.POST)
    @ResponseBody
    public boolean dropProduct(@RequestParam("code") String code) {
        XN704006Res res = productAO.dropProduct(code);
        return res.isSuccess();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public boolean editProduct(@RequestParam("code") String code,
            @RequestParam("name") String name,
            @RequestParam(value = "picture", required = false) String picture,
            @RequestParam("description") String description,
            @RequestParam("kind") String kind,
            @RequestParam("status") String status,
            @RequestParam("companyCode") String companyCode) {
        XN704007Res res = productAO.editProduct(code, name, picture,
            description, kind, status, companyCode);
        return res.isSuccess();
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page queryProductPage(
            
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "kind", required = false) String kind,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "companyCode", required = false) String companyCode,
            @RequestParam(value = "orderColumn", required = false) String orderColumn,
            @RequestParam(value = "orderDir", required = false) String orderDir,
            @RequestParam("start") String start,
            @RequestParam("limit") String limit) {
        return productAO.queryProductPage(code, name, kind, status,
            companyCode, start, limit, orderColumn, orderDir);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List queryProductList(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "kind", required = false) String kind,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "companyCode", required = false) String companyCode) {
        return productAO
            .queryProductList(code, name, kind, status, companyCode);
    }
}
