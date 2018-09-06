package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.net.RestRequest;

import rx.Observable;

/**
 * Title: SmsModel
 * Description:
 * Copyright:Copyright(c)2016
 * Company:
 * CreateTime:2017/12/5  15:47
 *
 * @author liutong
 * @version 1.0
 */
public class SmsModel {

    public static Observable<ResponseJson<Object>> sendSms(String mobile){
        return RestRequest.<ResponseJson<Object>>builder()
                .addPublicPara("driverPhone",mobile)
                .url("/driver/sms.do")
                .restMethod(RestMethodEnum.POST)
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }
}
