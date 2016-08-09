package com.xnjr.oss.home.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.IJobsAO;
import com.xnjr.oss.home.req.XN704020Req;
import com.xnjr.oss.home.req.XN704021Req;
import com.xnjr.oss.home.req.XN704022Req;
import com.xnjr.oss.home.req.XN704023Req;
import com.xnjr.oss.home.req.XN704024Req;
import com.xnjr.oss.home.res.XN704020Res;
import com.xnjr.oss.home.res.XN704021Res;
import com.xnjr.oss.home.res.XN704022Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;

@Service
public class JobsAOImpl implements IJobsAO {

    @Override
    public XN704020Res addJobs(String name, String department, String area,
            String duty, String claim, String content, String description,
            String status, String companyCode) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN700001", "职位名称不能为空");
        }
        if (StringUtils.isBlank(department)) {
            throw new BizException("XN700001", "招聘部门不能为空");
        }
        if (StringUtils.isBlank(area)) {
            throw new BizException("XN700001", "招聘地区不能为空");
        }
        if (StringUtils.isBlank(duty)) {
            throw new BizException("XN700001", "岗位职责不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw new BizException("XN700001", "简介不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN700001", "岗位描述不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "状态不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        XN704020Req req = new XN704020Req();
        req.setName(name);
        req.setDepartment(department);
        req.setArea(area);
        req.setDuty(duty);
        req.setClaim(claim);
        req.setContent(content);
        req.setDescription(description);
        req.setStatus(status);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704020", JsonUtils.object2Json(req),
            XN704020Res.class);
    }

    @Override
    public XN704021Res dropJobs(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN700001", "岗位编号不能为空");
        }
        XN704021Req xn704021Req = new XN704021Req();
        xn704021Req.setCode(code);
        ;
        return BizConnecter.getBizData("704021",
            JsonUtils.object2Json(xn704021Req), XN704021Res.class);
    }

    @Override
    public XN704022Res editJobs(String code, String name, String department,
            String area, String duty, String claim, String content,
            String description, String status, String companyCode) {
        if (StringUtils.isBlank(name)) {
            throw new BizException("XN700001", "职位名称不能为空");
        }
        if (StringUtils.isBlank(department)) {
            throw new BizException("XN700001", "招聘部门不能为空");
        }
        if (StringUtils.isBlank(area)) {
            throw new BizException("XN700001", "招聘地区不能为空");
        }
        if (StringUtils.isBlank(duty)) {
            throw new BizException("XN700001", "岗位职责不能为空");
        }
        if (StringUtils.isBlank(claim)) {
            throw new BizException("XN700001", "职位要求不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw new BizException("XN700001", "简介不能为空");
        }
        if (StringUtils.isBlank(description)) {
            throw new BizException("XN700001", "岗位描述不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "状态不能为空");
        }
        XN704022Req req = new XN704022Req();
        req.setCode(code);
        req.setName(name);
        req.setDepartment(department);
        req.setArea(area);
        req.setDuty(duty);
        req.setClaim(claim);
        req.setContent(content);
        req.setDescription(description);
        req.setStatus(status);
        req.setCompanyCode(companyCode);
        return BizConnecter.getBizData("704022", JsonUtils.object2Json(req),
            XN704022Res.class);
    }

    @Override
    public List queryJobsList(String code, String name, String status,
            String dateStart, String dateEnd) {
        XN704024Req req = new XN704024Req();
        req.setName(name);
        req.setCode(code);
        req.setStatus(status);
        req.setDateStart(dateStart);
        req.setDateStart(dateEnd);
        return BizConnecter.getBizData("704024", JsonUtils.object2Json(req),
            List.class);
    }

    @Override
    public Page queryJobsPage(String code, String name, String companyCode, String status,
            String dateStart, String dateEnd, String start, String limit,
            String orderColumn, String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN704023", "第几页不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN704023", "页面个数不能为空");
        }
        XN704023Req req = new XN704023Req();
        req.setCode(code);
        req.setName(name);
        req.setCompanyCode(companyCode);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd);
        req.setStart(start);
        req.setStatus(status);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704023", JsonUtils.object2Json(req),
            Page.class);
    }
}
