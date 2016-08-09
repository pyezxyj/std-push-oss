package com.xnjr.oss.general.ao;

import java.util.List;

import com.xnjr.oss.general.res.XN707000Res;
import com.xnjr.oss.general.res.XN707002Res;
import com.xnjr.oss.general.res.XN707003Res;
import com.xnjr.oss.general.res.XN707004Res;
import com.xnjr.oss.res.Page;

public interface IDictAO {
    public XN707002Res addDict(String pId, String key, String value,
            String creator, String remark, String type);

    public XN707003Res dropDict(String id);

    public XN707004Res editDict(String id, String pId, String key,
            String value, String updater, String remark, String type);

    public List<XN707000Res> queryDictList(String id, String pId, String key,
            String type);

    @SuppressWarnings("rawtypes")
    public Page queryDictPage(String id, String pId, String key, String type,
            String start, String limit, String orderColumn, String orderDir);
}
