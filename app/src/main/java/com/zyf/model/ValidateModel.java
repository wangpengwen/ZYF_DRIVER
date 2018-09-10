package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.net.RestRequest;

import rx.Observable;

/**
 * Created by ltxxx on 2018/9/7.
 */

public class ValidateModel {

    public static Observable<ResponseJson<Object>> driverInfo(){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/api/data/get.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

}
