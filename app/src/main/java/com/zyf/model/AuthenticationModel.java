package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.net.RestRequest;

import rx.Observable;

/**
 * Created by TCJK on 2018/6/15.
 */

public class AuthenticationModel {

    public static Observable<ResponseJson<Object>> bindIDCard(String name,String idNum){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/api/driver/cmpl/idcard.do")
                .addBody("driverRealName", name)
                .addBody("driverIdcard", idNum)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> bindVehicleInfo(String vehicleType,String num){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/api/cmpl/truck.do")
                .addBody("carNum", num)
                .addBody("truckType", vehicleType)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

}
