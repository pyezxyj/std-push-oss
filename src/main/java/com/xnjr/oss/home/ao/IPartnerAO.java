package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704010Res;
import com.xnjr.oss.home.res.XN704011Res;
import com.xnjr.oss.home.res.XN704012Res;
import com.xnjr.oss.res.Page;

public interface IPartnerAO {
    /**
     *  新增合作伙伴
     * @param name
     * @param logo
     * @param description
     * @param url
     * @param companyCode
     * @return 
     * @create: 2016年3月22日 上午7:52:57 xieyj
     * @history:
     */
    public XN704010Res addPartner(String name, String logo, String description,
            String url, String companyCode);

    /**
     * 删除合作伙伴信息
     * @param code
     * @return 
     * @create: 2016年3月17日 下午4:11:42 wu
     * @history:
     */
    public XN704011Res dropPartner(String code);

    /**
     * 修改合作伙伴
     * @param code
     * @param name
     * @param logo
     * @param description
     * @param url
     * @param companyCode
     * @return 
     * @create: 2016年3月22日 上午7:52:29 xieyj
     * @history:
     */
    public XN704012Res editPartner(String code, String name, String logo,
            String description, String url, String companyCode);

    /**
     * 查询合作伙伴列表
     * @param code
     * @param name
     * @param companyCode
     * @return 
     * @create: 2016年3月17日 下午4:16:20 wu
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public List queryPartnerList(String code, String name, String companyCode);

    /**
     * 分页查询合作伙伴
     * @param code
     * @param name
     * @param companyCode
     * @param start
     * @param limit
     * @param orderColumn
     * @param orderDir
     * @return 
     * @create: 2016年3月17日 下午4:15:32 wu
     * @history:
     */
    @SuppressWarnings("rawtypes")
    public Page queryPartnerPage(String code, String name, String companyCode,
            String start, String limit, String orderColumn, String orderDir);

}
