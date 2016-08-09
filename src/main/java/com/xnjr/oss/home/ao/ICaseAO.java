package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704015Res;
import com.xnjr.oss.home.res.XN704016Res;
import com.xnjr.oss.home.res.XN704017Res;
import com.xnjr.oss.res.Page;

public interface ICaseAO {

    public XN704015Res addCase(String name, String logo, String picture,
            String url, String description, String companyCode, String status);

    public XN704016Res dropCase(String code);

    public XN704017Res editCase(String code, String name, String logo,
            String status, String picture, String url, String description, String companyCode);

    @SuppressWarnings("rawtypes")
    public List queryCaseList(String code, String name, String companyCode,
            String status, String dateStart, String dateEnd);

    @SuppressWarnings("rawtypes")
    public Page queryCasePage(String code, String name, String companyCode,
            String status, String dateStart, String dateEnd, String start, String limit,
            String orderColumn, String orderDir);

}
