package com.zywl.model.entity.info.http;

import com.biz.util.GsonUtil;
import com.google.gson.annotations.Expose;
import com.zywl.model.entity.info.InfoEntity;

import java.util.List;

/**
 * Created by TCJK on 2018/4/13.
 */

final public class InfoResponseJson<T> {

    /**
     * 返回的错误消息
     */
    public String message;
    /**
     * 调用返回状态码
     */
    @Expose
    public int status = -1;
    /**
     * 服务器当前时间戳
     */
    @Expose
    public long ts;
    /**
     * 接口返回具体数据内容 JSONObject
     */
    @Expose
    public T data;

    public long execTime;

    public int total;

    public List<InfoEntity> rows;

    public boolean isOk() {
        return status == 0;
    }

    public String toJsonString() {
        return GsonUtil.toJson(this);
    }
}
