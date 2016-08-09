package com.xnjr.oss.home.ao;

import java.util.List;

import com.xnjr.oss.home.res.XN704035Res;
import com.xnjr.oss.home.res.XN704036Res;
import com.xnjr.oss.home.res.XN704037Res;
import com.xnjr.oss.res.Page;

public interface INewsAO {
    /**
     * 增加
     * @param title
     * @param keyword
     * @param type
     * @param content
     * @param picture
     * @param jumpUrl
     * @param showUrl
     * @param companyCode
     * @param creator
     * @param author
     * @param remark
     * @return 
     * @create: 2015年10月23日 上午10:21:33 jlxuu
     * @history:
     */
    public XN704035Res addNews(String title, String keyword, String type,
            String content, String picture, String jumpUrl, String showUrl,
            String companyCode, String creator, String author, String remark);

    /**
     * 删除
     * @param code
     * @return 
     * @create: 2015年10月23日 上午10:21:53 jlxuu
     * @history:
     */
    public XN704036Res dropNews(String code);

    /**
     * 修改
     * @param code
     * @param title
     * @param keyword
     * @param type
     * @param content
     * @param picture
     * @param jumpUrl
     * @param showUrl
     * @param companyCode
     * @param creator
     * @param author
     * @param remark
     * @return 
     * @create: 2015年10月23日 上午10:22:01 jlxuu
     * @history:
     */
    public XN704037Res editNews(String code, String title, String keyword,
            String type, String content, String picture, String jumpUrl,
            String showUrl, String companyCode, String creator, String author,
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
    public List queryNewsList(String code, String title, String type,
            String companyCode, String creator, String dateStart, String dateEnd);

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
    public Page queryNewsPage(String code, String title, String type,
            String companyCode, String creator, String dateStart,
            String dateEnd, String start, String limit, String orderColumn,
            String orderDir);

}
