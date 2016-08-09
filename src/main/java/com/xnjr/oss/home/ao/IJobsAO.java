package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704020Res;
import com.xnjr.oss.home.res.XN704021Res;
import com.xnjr.oss.home.res.XN704022Res;
import com.xnjr.oss.res.Page;

public interface IJobsAO {

    public XN704020Res addJobs(String name, String department, String area,
            String duty, String claim, String content, String description,
            String status, String companyCode);

    public XN704021Res dropJobs(String code);

    public XN704022Res editJobs(String code, String name, String department,
            String area, String duty, String claim, String content,
            String description, String status, String companyCode);

    @SuppressWarnings("rawtypes")
    public List queryJobsList(String code, String name, String status,
            String dateStart, String dateEnd);

    @SuppressWarnings("rawtypes")
    public Page queryJobsPage(String code, String name, String companyCode, String status,
            String dateStart, String dateEnd, String start, String limit,
            String orderColumn, String orderDir);

}
