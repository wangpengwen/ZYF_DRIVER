package com.biz.base;

import com.biz.http.ResponseJson;

public class RestErrorInfo {
    public int status;
    public String message;
    public int source;
    public RestErrorInfo(ResponseJson responseJson){
        this.status =responseJson.status;
        this.message=responseJson.errormsg;
    }
    public RestErrorInfo(int status, String message){
        this.status = status;
        this.message=message;
    }
    public RestErrorInfo(String message)
    {
        this.status =-1;
        this.message=message;
    }
}
