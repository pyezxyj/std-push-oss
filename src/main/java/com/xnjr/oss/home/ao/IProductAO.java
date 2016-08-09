package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704005Res;
import com.xnjr.oss.home.res.XN704006Res;
import com.xnjr.oss.home.res.XN704007Res;
import com.xnjr.oss.res.Page;

public interface IProductAO {

    public XN704005Res addProduct(String name, String picture,
            String description, String kind, String status, String companyCode);

    public XN704007Res editProduct(String code, String name, String picture,
            String description, String kind, String status, String companyCode);

    @SuppressWarnings("rawtypes")
    public List queryProductList(String code, String name, String kind,
            String status, String companyCode);

    @SuppressWarnings("rawtypes")
    public Page queryProductPage(String code, String name, String kind,
            String status, String companyCode, String start, String limit,
            String orderColumn, String orderDir);

    public XN704006Res dropProduct(String code);

}
