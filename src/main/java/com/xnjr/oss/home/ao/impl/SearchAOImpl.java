package com.xnjr.oss.home.ao.impl;

import org.springframework.stereotype.Service;

import com.xnjr.oss.home.ao.ISearchAO;
import com.xnjr.oss.home.req.XN704101Req;
import com.xnjr.oss.home.req.XN704102Req;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.util.UploadUtil;

@Service
public class SearchAOImpl implements ISearchAO {

    @Override
    public Object querySearchPage(String person, String contact, String content1, String content2, String organization,
    		String organizationDesc, String remark, String personDesc, String dateStart, String dateEnd, String code, String type, String status,
    		String creator, String updater, String companyCode, String start, String limit, String orderColumn, String orderDir) {
        XN704101Req req = new XN704101Req();
        req.setPerson(person);
        req.setContact(contact);
        req.setContent1(content1);
        req.setContent2(content2);
        req.setOrganization(organization);
        req.setOrganizationDesc(organizationDesc);
        req.setRemark(remark);
        req.setPersonDesc(personDesc);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd);
        req.setCode(code);
        req.setType(type);
        req.setStatus(status);
        req.setCreator(creator);
        req.setUpdater(updater);
        req.setCompanyCode(companyCode);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704101", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public Object querySearchList(String code, String type, String status,
    		String creator, String updater, String companyCode) {
        XN704102Req req = new XN704102Req();
        req.setCode(code);
        req.setType(type);
        req.setStatus(status);
        req.setCreator(creator);
        req.setUpdater(updater);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704102", JsonUtils.object2Json(req),
            Object.class);
    }

}
