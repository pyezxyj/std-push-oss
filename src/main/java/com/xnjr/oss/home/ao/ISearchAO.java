package com.xnjr.oss.home.ao;

public interface ISearchAO {
    
    //分页
    public Object querySearchPage(String person, String contact, String content1, String content2, 
    		String organizationDesc, String remark, String personDesc, String dateStart, String dateEnd, String code, String type, String status,
    		String creator, String updater, String companyCode, String start, String limit, String orderColumn, String orderDir);

    //列表
    public Object querySearchList(String code, String type, String status,
    		String creator, String updater, String companyCode);

}
