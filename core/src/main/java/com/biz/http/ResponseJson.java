package com.biz.http;

import com.google.gson.annotations.Expose;

import com.biz.util.GsonUtil;

final public class ResponseJson<T> {

    /**
     * 返回的错误消息
     */
    public String errormsg;
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

    public boolean isOk() {
        return status == 0;
    }

    public String toJsonString() {
        return GsonUtil.toJson(this);
    }
}
