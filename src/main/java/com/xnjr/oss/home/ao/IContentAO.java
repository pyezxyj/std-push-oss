package com.xnjr.oss.home.ao;

public interface IContentAO {
    /**
     * 新增内容
     * @param type
     * @param title
     * @param picture1
     * @param picture2
     * @param description
     * @param menuCode
     * @param remark
     * @return 
     * @create: 2016年7月15日 下午2:36:29 XIANDONG
     * @history:
     */
    public Object addcontent(String title, String type, String picture1, String picture2,
            String description, String menuCode, String remark, String endNote);

    /**
     * 删除内容
     * @param code
     * @return 
     * @create: 2016年7月15日 下午2:37:57 XIANDONG
     * @history:
     */
    public Object dropcontent(String code);

    /**
     * 修改内容
     * @param code
     * @param type
     * @param title
     * @param picture1
     * @param picture2
     * @param description
     * @param menuCode
     * @param remark
     * @return 
     * @create: 2016年7月15日 下午2:38:25 XIANDONG
     * @history:
     */
    public Object editcontent(String code, String type, String title,
            String picture1, String picture2, String description,
            String menuCode, String remark, String endNote);

    /**
     * 分页查询
     * @param code
     * @param type
     * @param title
     * @param menuCode
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年7月15日 下午2:39:18 XIANDONG
     * @history:
     */
    public Object querycontentPage(String code, String type, String title,
            String menuCode, String start, String limit, String orderColumn,
            String orderDir);

    /**
     * 列表查询
     * @param code
     * @param type
     * @param title
     * @param menuCode
     * @return 
     * @create: 2016年7月15日 下午2:39:42 XIANDONG
     * @history:
     */
    public Object querycontentList(String code, String type, String title,
            String menuCode);




}
