package com.xnjr.oss.general.ao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xnjr.oss.exception.BizException;
import com.xnjr.oss.general.ao.IDictAO;
import com.xnjr.oss.general.req.XN707000Req;
import com.xnjr.oss.general.req.XN707001Req;
import com.xnjr.oss.general.req.XN707002Req;
import com.xnjr.oss.general.req.XN707003Req;
import com.xnjr.oss.general.req.XN707004Req;
import com.xnjr.oss.general.res.XN707000Res;
import com.xnjr.oss.general.res.XN707002Res;
import com.xnjr.oss.general.res.XN707003Res;
import com.xnjr.oss.general.res.XN707004Res;
import com.xnjr.oss.http.BizConnecter;
import com.xnjr.oss.http.JsonUtils;
import com.xnjr.oss.res.Page;

@Service
public class DictAOImpl implements IDictAO {

    @Override
    public XN707002Res addDict(String pId, String key, String value,
            String creator, String remark, String type) {
        if (StringUtils.isBlank(pId)) {
            throw new BizException("XN700001", "父亲编号不能为空");
        }
        if (StringUtils.isBlank(key)) {
            throw new BizException("XN700001", "key不能为空");
        }
        if (StringUtils.isBlank(value)) {
            throw new BizException("XN700001", "value不能为空");
        }
        if (StringUtils.isBlank(type)) {
            throw new BizException("XN700001", "类型不能为空");
        }
        XN707002Req xn707002Req = new XN707002Req();
        xn707002Req.setpId(pId);
        xn707002Req.setKey(key);
        xn707002Req.setValue(value);
        xn707002Req.setCreator(creator);
        xn707002Req.setRemark(remark);
        xn707002Req.setType(type);
        return BizConnecter.getBizData("707002",
            JsonUtils.object2Json(xn707002Req), XN707002Res.class);
    }

    @Override
    public XN707003Res dropDict(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "id不能为空");
        }
        XN707003Req xn707003Req = new XN707003Req();
        xn707003Req.setId(id);
        return BizConnecter.getBizData("707003",
            JsonUtils.object2Json(xn707003Req), XN707003Res.class);
    }

    @Override
    public XN707004Res editDict(String id, String pId, String key,
            String value, String updater, String remark, String type) {
        if (StringUtils.isBlank(id)) {
            throw new BizException("XN700001", "id不能为空");
        }
        if (StringUtils.isBlank(pId)) {
            throw new BizException("XN700001", "父亲编号不能为空");
        }
        if (StringUtils.isBlank(key)) {
            throw new BizException("XN700001", "key不能为空");
        }
        if (StringUtils.isBlank(value)) {
            throw new BizException("XN700001", "value不能为空");
        }
        XN707004Req xn707004Req = new XN707004Req();
        xn707004Req.setId(id);
        xn707004Req.setpId(pId);
        xn707004Req.setKey(key);
        xn707004Req.setValue(value);
        xn707004Req.setUpdater(updater);
        xn707004Req.setRemark(remark);
        xn707004Req.setType(type);
        return BizConnecter.getBizData("707004",
            JsonUtils.object2Json(xn707004Req), XN707004Res.class);
    }

    @Override
    public List<XN707000Res> queryDictList(String id, String pId, String key,
            String type) {
        if (StringUtils.isBlank(type)) {
            type = "1";
        }
        XN707000Req xn707000Req = new XN707000Req();
        xn707000Req.setId(id);
        xn707000Req.setpId(pId);
        xn707000Req.setKey(key);
        xn707000Req.setType(type);
        String jsonStr = BizConnecter.getBizData("707000",
            JsonUtils.object2Json(xn707000Req));
        Gson gson = new Gson();
        List<XN707000Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN707000Res>>() {
            }.getType());
        return list;
    }

    @Override
    public Page queryDictPage(String id, String pId, String key, String type,
            String start, String limit, String orderColumn, String orderDir) {
        if (StringUtils.isBlank(start)) {
            throw new BizException("XN700001", "start不能为空");
        }
        if (StringUtils.isBlank(limit)) {
            throw new BizException("XN700001", "limit不能为空");
        }
        if (StringUtils.isBlank(type)) {
            type = "1";
        }
        XN707001Req xn707001Req = new XN707001Req();
        xn707001Req.setId(id);
        xn707001Req.setKey(key);
        xn707001Req.setpId(pId);
        xn707001Req.setLimit(limit);
        xn707001Req.setStart(start);
        xn707001Req.setOrderColumn(orderColumn);
        xn707001Req.setOrderDir(orderDir);
        xn707001Req.setType(type);
        return BizConnecter.getBizData("707001",
            JsonUtils.object2Json(xn707001Req), Page.class);
    }

}
