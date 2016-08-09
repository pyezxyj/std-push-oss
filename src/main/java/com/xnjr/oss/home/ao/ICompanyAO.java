package com.xnjr.oss.home.ao;

public interface ICompanyAO {
    /**
     * 新增
     * @param name
     * @param corporation
     * @param logo
     * @param address
     * @param copyright
     * @param description
     * @param telephone
     * @param fax
     * @param email
     * @param url
     * @param slogan
     * @param barCode
     * @param longitude
     * @param latitude
     * @param userid
     * @return 
     * @create: 2016年7月15日 下午12:32:32 XIANDONG
     * @history:
     */
    public Object addCompany(String name, String corporation, String logo,
            String address, String copyright, String description,
            String telephone, String fax, String email, String url,
            String slogan, String barCode, String longitude, String latitude,
            String userid);

    /**
     * 删除
     * @param code
     * @return 
     * @create: 2016年7月15日 下午12:32:50 XIANDONG
     * @history:
     */
    public Object dropCompany(String code);

    /**
     * 修改
     * @param code
     * @param name
     * @param corporation
     * @param logo
     * @param address
     * @param copyright
     * @param description
     * @param telephone
     * @param fax
     * @param email
     * @param url
     * @param slogan
     * @param barCode
     * @param longitude
     * @param latitude
     * @param userid
     * @return 
     * @create: 2016年7月15日 下午12:39:35 XIANDONG
     * @history:
     */
    public Object editCompany(String code, String name, String corporation,
            String logo, String address, String copyright, String description,
            String telephone, String fax, String email, String url,
            String slogan, String barCode, String longitude, String latitude,
            String userid);

    /**
     * 分页
     * @param name
     * @param code
     * @param userid
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年7月15日 下午1:29:27 XIANDONG
     * @history:
     */
    public Object queryCompanyPage(String name, String code, String userid,
            String start, String limit, String orderColumn, String orderDir);

    /**
     * 列表
     * @param name
     * @param code
     * @param userid
     * @return 
     * @create: 2016年7月15日 下午1:29:44 XIANDONG
     * @history:
     */
    public Object queryCompanyList(String name, String code, String userid);

    /**
     * code详情
     * @param code
     * @return 
     * @create: 2016年7月15日 下午12:47:36 XIANDONG
     * @history:
     */
    public Object detailcompany(String code);

    /**
     * userid详情
     * @param userid
     * @return 
     * @create: 2016年7月15日 下午12:47:56 XIANDONG
     * @history:
     */
    public Object detailcompanyuserid(String userid);

}
