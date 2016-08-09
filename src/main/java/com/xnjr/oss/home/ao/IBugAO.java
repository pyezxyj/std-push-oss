package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704026Res;
import com.xnjr.oss.home.res.XN704027Res;
import com.xnjr.oss.res.Page;

public interface IBugAO {

    /**
     * 
     * @param code
     * @return 支付
     * @create: 2015年10月22日 下午4:10:07 jlxuu
     * @history:
     */
    public XN704027Res payBug(String code, String money);

    /**
     * 審核
     * @param code
     * @param checkUser
     * @param checkResult
     * @param checkDetail
     * @return 
     * @create: 2015年10月22日 下午4:10:12 jlxuu
     * @history:
     */
    public XN704026Res checkBug(String code, String checkUser,
            String checkResult, String checkDetail);

    /**
     * 
     * @param code
     * @param module
     * @param alipay
     * @param period
     * @param checkUser
     * @param checkResult
     * @param money
     * @param status
     * @return 
     * @create: 2015年10月22日 下午4:10:16 jlxuu
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public List queryBugList(String code, String module, String alipay,
            String period, String checkUser, String checkResult, String money,
            String status, String dateStart, String dateEnd);

    /**
     * 
     * @param code
     * @param module
     * @param alipay
     * @param period
     * @param orderColumn
     * @param orderDir
     * @param checkUser
     * @param checkResult
     * @param money
     * @param status
     * @param start
     * @param limit
     * @return 
     * @create: 2015年10月22日 下午4:10:22 jlxuu
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public Page queryBugPage(String code, String module, String alipay,
            String period, String orderColumn, String orderDir,
            String checkUser, String checkResult, String money, String status,
            String start, String limit, String dateStart, String dateEnd);
}
