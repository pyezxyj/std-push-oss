package com.xnjr.oss.home.ao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.home.ao.IIndexAO;
import com.xnjr.oss.home.ao.INewsAO;
import com.xnjr.oss.home.req.XN704035Req;
import com.xnjr.oss.home.req.XN704036Req;
import com.xnjr.oss.home.req.XN704037Req;
import com.xnjr.oss.home.req.XN704038Req;
import com.xnjr.oss.home.req.XN704039Req;
import com.xnjr.oss.home.req.XN704320Req;
import com.xnjr.oss.home.req.XN704330Req;
import com.xnjr.oss.home.req.XN704331Req;
import com.xnjr.oss.home.req.XN704332Req;
import com.xnjr.oss.home.res.XN704035Res;
import com.xnjr.oss.home.res.XN704036Res;
import com.xnjr.oss.home.res.XN704037Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;
import com.xnjr.oss.util.UploadUtil;

@Service
public class IndexAOImpl implements IIndexAO {

	@Override
	public Object editIndex(String code, String banner1, String banner2,
			String banner3, String fullSizePic, String url, String urlText,
			String companyCode, String creater, String remark) {
		XN704320Req req = new XN704320Req();
        req.setCode(code);
        req.setBanner1(UploadUtil.uploadPicture(banner1));
        req.setBanner2(UploadUtil.uploadPicture(banner2));
        req.setBanner3(UploadUtil.uploadPicture(banner3));
        req.setFullSizePic(UploadUtil.uploadPicture(fullSizePic));
        req.setUrl(url);
        req.setUrlText(urlText);
        req.setCompanyCode(companyCode);
        req.setUpdater(creater);
        req.setRemark(remark);
        return BizConnecter.getBizData("704320", JsonUtils.object2Json(req),
            Object.class);
	}

	@Override
	public List queryIndexList(String code, String companyCode, String creator,
			String orderColumn, String orderDir) {
		XN704331Req req = new XN704331Req();
        req.setCode(code);
        req.setCompanyCode(companyCode);
        req.setUpdater(creator);
        return BizConnecter.getBizData("704331", JsonUtils.object2Json(req),
            List.class);
	}

	@Override
	public Page queryIndexPage(String code, String companyCode, String creator,
			String start, String limit, String orderColumn, String orderDir) {
		XN704330Req req = new XN704330Req();
        req.setCode(code);
        req.setCompanyCode(companyCode);
        req.setUpdater(creator);
        req.setStart(start);
        req.setLimit(limit);
        req.setOrderColumn(orderColumn);
        req.setOrderDir(orderDir);
        return BizConnecter.getBizData("704330", JsonUtils.object2Json(req),
            Page.class);
	}

	@Override
	public Object indexDetail(String code) {
		XN704332Req req = new XN704332Req();
        req.setCode(code);
        return BizConnecter.getBizData("704332", JsonUtils.object2Json(req),
            Object.class);
	}

}
