package com.biz.http;

public class HttpErrorException extends RuntimeException {
    private ResponseJson responseJson;
    public HttpErrorException(ResponseJson responseJson) {
        super(responseJson!=null?responseJson.errormsg :"");
        this.responseJson=responseJson;
    }
    public HttpErrorException(String message) {
        super(message);
        this.responseJson=new ResponseJson();
        responseJson.status=-1;
        responseJson.errormsg =message;
    }
    public ResponseJson getResponseJson() {
        return responseJson;
    }
}