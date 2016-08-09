package com.xnjr.oss.general.ao;

import java.util.List;

import com.xnjr.oss.res.Page;

public interface IOperatorAO {
    /**
     * 增加
     * @param userId
     * @param companyId
     * @param mobile
     * @param idKind
     * @param idNo
     * @param realName
     * @param photo
     * @param tradePwd
     * @param introduction
     * @param remark
     * @param status
     * @return 
     * @create: 2016年1月13日 下午4:11:24 jlxuu
     * @history:
     */
    public boolean addOperator(String userId, String companyId, String mobile,
            String idKind, String idNo, String realName, String photo,
            String tradePwd, String introduction, String remark);

    /**
     * 删除
     * @param userId
     * @return 
     * @create: 2016年1月13日 下午4:11:35 jlxuu
     * @history:
     */
    public boolean deleteOperator(String userId);

    /**
     * 修改
     * @param userId
     * @param companyId
     * @param mobile
     * @param level
     * @param photo
     * @param introduction
     * @param remark
     * @param status
     * @return 
     * @create: 2016年1月13日 下午4:11:45 jlxuu
     * @history:
     */
    public boolean editOperator(String userId, String companyId, String mobile,
            String level, String photo, String introduction, String remark,
            String status);

    /**
     * 列表
     * @param userId
     * @param companyId
     * @param mobile
     * @param realName
     * @param level
     * @param status
     * @return 
     * @create: 2016年1月13日 下午4:11:55 jlxuu
     * @history:
     */
    public List queryOperatorList(String userId, String companyId,
            String mobile, String realName, String level, String status);

    /**
     * 分页
     * @param userId
     * @param companyId
     * @param mobile
     * @param realName
     * @param level
     * @param status
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年1月13日 下午4:12:23 jlxuu
     * @history:
     */
    public Page queryOperatorPage(String userId, String companyId,
            String mobile, String realName, String level, String status,
            String start, String limit, String orderColumn, String orderDir);

}
