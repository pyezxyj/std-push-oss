package com.xnjr.oss.home.ao;

public interface IMenuplatAO {

    /**
     * 新增菜单
     * @param name
     * @param orderNo
     * @param templetCode
     * @param contentType
     * @param userId
     * @param remark
     * @return 
     * @create: 2016年7月15日 下午8:49:40 xieyj
     * @history:
     */
    public Object addmenu(String name, String orderNo, String parentCode, String templetCode,
            String contentType, String userId, String remark);

    /**
     * 删除菜单
     * @param code
     * @return 
     * @create: 2016年7月15日 下午12:32:50 XIANDONG
     * @history:
     */
    public Object dropmenu(String code);
    
    /**
     * 改变菜单状态
     * @param code
     * @return 
     * @create: 2016年7月23日 下午4:32:50 paopao
     * @history:
     */
    public Object changemenu(String code);

    /**
     * 修改菜单
     * @param code
     * @param name
     * @param orderNo
     * @param templetCode
     * @param contentType
     * @param userId
     * @param remark
     * @return 
     * @create: 2016年7月15日 下午8:51:21 xieyj
     * @history:
     */
    public Object editmenu(String code, String name, String parentCode, String orderNo,
            String templetCode, String contentType, String userId,
            String remark);

    /**
     * 分页查询
     * @param code
     * @param status
     * @param templetCode
     * @param parentCode
     * @param contentType
     * @param contentSource
     * @param companyCode
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年7月15日 下午1:51:48 XIANDONG
     * @history:
     */
    public Object querymenuPage(String code, String name, String status,
            String templetCode, String parentCode, String contentType,
            String companyCode, String start, String limit, String orderColumn,
            String orderDir);

    /**
     * 列表查询
     * @param code
     * @param status
     * @param templetCode
     * @param parentCode
     * @param contentType
     * @param contentSource
     * @param companyCode
     * @return 
     * @create: 2016年7月15日 下午1:52:17 XIANDONG
     * @history:
     */
    public Object querymenuList(String code, String status, String templetCode,
            String parentCode, String contentType, String companyCode);

	

}
