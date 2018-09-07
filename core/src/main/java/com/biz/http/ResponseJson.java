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
     * 调用返回错误值
     */
    @Expose
    public String errorcode;
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
        return status == 0 && "00000".equals(errorcode);
    }

    public String toJsonString() {
        return GsonUtil.toJson(this);
    }
}
