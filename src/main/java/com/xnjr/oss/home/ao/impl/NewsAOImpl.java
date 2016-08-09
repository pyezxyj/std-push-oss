package com.xnjr.oss.home.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.INewsAO;
import com.xnjr.oss.home.req.XN704035Req;
import com.xnjr.oss.home.req.XN704036Req;
import com.xnjr.oss.home.req.XN704037Req;
import com.xnjr.oss.home.req.XN704038Req;
import com.xnjr.oss.home.req.XN704039Req;
import com.xnjr.oss.home.res.XN704035Res;
import com.xnjr.oss.home.res.XN704036Res;
import com.xnjr.oss.home.res.XN704037Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class NewsAOImpl implements INewsAO {

    @Override
    public XN704035Res addNews(String title, String keyword, String type,
            String content, String picture, String jumpUrl, String showUrl,
            String companyCode, String creator, String author, String remark) {
        if (StringUtils.isBlank(title)) {
            throw new BizException("XN700001", "标题不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "类型不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("XN700001", "创建者不能为空");
        }
        if (StringUtils.isBlank(author)) {
            throw new BizException("XN700001", "作者不能为空");
        }
        XN704035Req xn704035Req = new XN704035Req();
        xn704035Req.setAuthor(author);
        xn704035Req.setCompanyCode(companyCode);
        xn704035Req.setContent(content);
        xn704035Req.setCreator(creator);
        xn704035Req.setJumpUrl(jumpUrl);
        xn704035Req.setKeyword(keyword);
        xn704035Req.setPicture(UploadUtil.uploadPicture(picture));
        xn704035Req.setRemark(remark);
        xn704035Req.setShowUrl(showUrl);
        xn704035Req.setTitle(title);
        xn704035Req.setType(type);

        return BizConnecter.getBizData("704035",
            JsonUtils.object2Json(xn704035Req), XN704035Res.class);
    }

    @Override
    public XN704036Res dropNews(String code) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN700001", "产品编号不能为空");
        }
        XN704036Req xn704036Req = new XN704036Req();
        xn704036Req.setCode(code);
        ;
        return BizConnecter.getBizData("704036",
            JsonUtils.object2Json(xn704036Req), XN704036Res.class);
    }

    @Override
    public XN704037Res editNews(String code, String title, String keyword,
            String type, String content, String picture, String jumpUrl,
            String showUrl, String companyCode, String creator, String author,
            String remark) {
        if (StringUtils.isBlank(code)) {
            throw new BizException("XN700001", "新闻编码不能为空");
        }
        if (StringUtils.isBlank(title)) {
            throw new BizException("XN700001", "标题不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "类型不能为空");
        }
        if (StringUtils.isBlank(companyCode)) {
            throw new BizException("XN700001", "公司编号不能为空");
        }
        if (StringUtils.isBlank(creator)) {
            throw new BizException("XN700001", "创建者不能为空");
        }
        if (StringUtils.isBlank(author)) {
            throw new BizException("XN700001", "作者不能为空");
        }
        if (picture.length() > 100) {
            picture = UploadUtil.uploadPicture(picture);
        }
        XN704037Req xn704037Req = new XN704037Req();
        xn704037Req.setAuthor(author);
        xn704037Req.setCode(code);
        xn704037Req.setCompanyCode(companyCode);
        xn704037Req.setContent(content);
        xn704037Req.setCreator(creator);
        xn704037Req.setJumpUrl(jumpUrl);
        xn704037Req.setKeyword(keyword);
        xn704037Req.setPicture(uploadPicture(picture));
        xn704037Req.setRemark(remark);
        xn704037Req.setShowUrl(showUrl);
        xn704037Req.setTitle(title);
        xn704037Req.setType(type);

        return BizConnecter.getBizData("704037",
            JsonUtils.object2Json(xn704037Req), XN704037Res.class);
    }

    private String uploadPicture(String picture) {
        Pattern pattern = Pattern.compile("data:image/(.+?);base64");
        Matcher matcher = pattern.matcher(picture);
        if (!matcher.find()) {
            return picture;
        }
        return UploadUtil.uploadPicture(picture);
    }

    /** 
     * @see com.xnjr.oss.home.ao.INewsAO#queryNewsList(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List queryNewsList(String code, String title, String type,
            String companyCode, String creator, String dateStart, String dateEnd) {
        XN704039Req req = new XN704039Req();
        req.setCode(code);
        req.setTitle(title);
        req.setType(type);
        req.setCompanyCode(companyCode);
        req.setCreator(creator);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd);
        return BizConnecter.getBizData("704039", JsonUtils.object2Json(req),
            List.class);

    }

    /** 
     * @see com.xnjr.oss.home.ao.INewsAO#queryNewsPage(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Page queryNewsPage(String code, String title, String type,
            String companyCode, String creator, String dateStart,
            String dateEnd, String start, String limit, String orderColumn,
            String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN704038Req req = new XN704038Req();
        req.setCode(code);
        req.setTitle(title);
        req.setType(type);
        req.setCompanyCode(companyCode);
        req.setCreator(creator);
        req.setDateStart(dateStart);
        req.setDateEnd(dateEnd);
        req.setLimit(limit);
        req.setStart(start);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704038", JsonUtils.object2Json(req),
            Page.class);

    }

}
