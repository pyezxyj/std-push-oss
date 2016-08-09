package com.xnjr.oss.general.ao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.general.ao.IContractAO;
import com.xnjr.oss.general.res.XN707002Res;
import com.xnjr.oss.general.res.XN707005Req;
import com.xnjr.oss.general.res.XN707006Req;
import com.xnjr.oss.general.res.XN707007Req;
import com.xnjr.oss.general.res.XN707008Req;
import com.xnjr.oss.general.res.XN707009Req;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;

@Service
public class ContractAOImpl implements IContractAO {
    @Override
    public XN707002Res addContractTemplate(String title, String content,
            String type, String status, String creator, String remark) {
        if (StringUtils.isBlank(title)) {
            throw new BizException("XN700001", "合同标题不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw new BizException("XN700001", "合同内容不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "合同类型不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "合同状态不能为空");
        }
        XN707007Req xn707007Req = new XN707007Req();
        xn707007Req.setTitle(title);
        xn707007Req.setContent(content);
        xn707007Req.setType(type);
        xn707007Req.setStatus(status);
        xn707007Req.setCreator(creator);
        xn707007Req.setRemark(remark);
        return BizConnecter.getBizData("707007",
            JsonUtils.object2Json(xn707007Req), XN707002Res.class);
    }

    @Override
    public boolean deleteContractTemplate(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "合同编号不能为空");
        }
        XN707008Req xn707008Req = new XN707008Req();
        xn707008Req.setId(id);
        return BizConnecter.getBizData("707008",
            JsonUtils.object2Json(xn707008Req), XN707002Res.class).isSuccess();
    }

    @Override
    public boolean editContractTemplate(String id, String title,
            String content, String type, String status, String updater,
            String remark) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "合同编号不能为空");
        }
        if (StringUtils.isBlank(title)) {
            throw new BizException("XN700001", "合同标题不能为空");
        }
        if (StringUtils.isBlank(content)) {
            throw new BizException("XN700001", "合同内容不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "合同类型不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new BizException("XN700001", "合同状态不能为空");
        }
        XN707009Req xn707009Req = new XN707009Req();
        xn707009Req.setId(id);
        xn707009Req.setTitle(title);
        xn707009Req.setContent(content);
        xn707009Req.setType(type);
        xn707009Req.setStatus(status);
        xn707009Req.setUpdater(updater);
        xn707009Req.setRemark(remark);
        return BizConnecter.getBizData("707009",
            JsonUtils.object2Json(xn707009Req), XN707002Res.class).isSuccess();

    }

    @Override
    public Object queryContractTemplateList(String id, String title,
            String type, String status, String creator, String updater) {
        XN707005Req xn707005Req = new XN707005Req();
        xn707005Req.setId(id);
        xn707005Req.setTitle(title);
        xn707005Req.setType(type);
        xn707005Req.setStatus(status);
        xn707005Req.setCreator(creator);
        xn707005Req.setUpdater(updater);
        return BizConnecter.getBizData("707005",
            JsonUtils.object2Json(xn707005Req), Object.class);
    }

    @Override
    public Page queryContractTemplatePage(String id, String title, String type,
            String status, String creator, String updater, String dateStart,
            String dateEnd, String start, String limit, String orderColumn,
            String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        XN707006Req xn707006Req = new XN707006Req();
        xn707006Req.setId(id);
        xn707006Req.setTitle(title);
        xn707006Req.setType(type);
        xn707006Req.setStatus(status);
        xn707006Req.setCreator(creator);
        xn707006Req.setUpdater(updater);
        xn707006Req.setDateStart(dateStart);
        xn707006Req.setDateEnd(dateEnd);
        xn707006Req.setStart(start);
        xn707006Req.setLimit(limit);
        xn707006Req.setOrderColumn(orderColumn);
        xn707006Req.setOrderDir(orderDir);
        return BizConnecter.getBizData("707006",
            JsonUtils.object2Json(xn707006Req), Page.class);
    }
}
