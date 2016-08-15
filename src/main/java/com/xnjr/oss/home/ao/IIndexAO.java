package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.res.Page;

public interface IIndexAO {

    public Object editIndex(String code, String banner1, String banner2, String banner3, 
    		String fullSizePic, String url, String urlText, String companyCode, String creater, 
    		String remark);

    /**
     * 列表查询
     * @param code
     * @param title
     * @param type
     * @param companyCode
     * @param creater
     * @param dateStart
     * @param dateEnd
     * @return 
     * @create: 2016年3月21日 下午3:04:24 xieyj
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public List queryIndexList(String code, String companyCode, String creator,
    		String orderColumn, String orderDir);

    /**
     * 分页查询
     * @param code
     * @param title
     * @param type
     * @param companyCode
     * @param creater
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年3月21日 下午3:04:15 xieyj
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public Page queryIndexPage(String code, String companyCode, String creator,
            String start, String limit, String orderColumn, String orderDir);
    
    public Object indexDetail(String code);

}
