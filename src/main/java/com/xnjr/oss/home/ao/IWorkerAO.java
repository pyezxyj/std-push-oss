package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704030Res;
import com.xnjr.oss.home.res.XN704031Res;
import com.xnjr.oss.home.res.XN704032Res;
import com.xnjr.oss.res.Page;

public interface IWorkerAO {

    /**
     *  增加
     * @param name
     * @param position
     * @param picture
     * @param description
     * @param remark
     * @param companyCode
     * @param creator
     * @return 
     * @create: 2016年3月18日 下午4:24:26 wu
     * @history:
     */
    public XN704030Res addWorker(String name, String position, String picture,
            String description, String remark, String companyCode,
            String creator);

    /**
     * 删除
     * @param code
     * @return 
     * @create: 2016年3月17日 下午2:26:48 wu
     * @history:
     */
    public XN704031Res dropWorker(String code);

    /**
     * 修改
     * @param code
     * @param name
     * @param position
     * @param picture
     * @param description
     * @param remark
     * @param status
     * @param companyCode
     * @return 
     * @create: 2016年3月17日 下午2:30:08 wu
     * @history:
     */
    public XN704032Res editWorker(String code, String name, String position,
            String picture, String description, String remark, 
            String companyCode);

    /**
     * 列表查询
     * @param code
     * @param name
     * @param status
     * @param dateStart
     * @param dateEnd
     * @param companyCode
     * @return 
     * @create: 2016年3月17日 下午2:36:18 wu
     * @history:
     */
    public List queryWorkerList(String code, String name, String status,
            String dateStart, String dateEnd, String companyCode);

    /**
     * 分页查询
     * @param code
     * @param name
     * @param status
     * @param dateStart
     * @param dateEnd
     * @param companyCode
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年3月17日 下午2:34:47 wu
     * @history:
     */
    public Page queryWorkerPage(String code, String name, String status,
            String dateStart, String dateEnd, String companyCode, String start,
            String limit, String orderColumn, String orderDir);

}
