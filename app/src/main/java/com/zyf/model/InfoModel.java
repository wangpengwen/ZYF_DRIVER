package com.zyf.model;

import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.info.http.InfoResponseJson;
import com.zyf.net.RestRequest;

import rx.Observable;

/**
 * Created by TCJK on 2018/4/13.
 */

public class InfoModel {

    public static Observable<InfoResponseJson> getUnpayInfo(String carNo){

        return RestRequest.<InfoResponseJson>builder()
                .url("/oweparkingfee.do")
                .addBody("carNo", carNo)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<InfoResponseJson>() {
                }.getType())
                .requestJson();
    }
}
