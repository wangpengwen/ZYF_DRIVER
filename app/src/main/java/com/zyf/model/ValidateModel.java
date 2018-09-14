package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.UserEntity;
import com.zyf.net.RestRequest;

import rx.Observable;

/**
 * Created by ltxxx on 2018/9/7.
 */

public class ValidateModel {

    public static Observable<ResponseJson<UserEntity>> driverInfo(){

        return RestRequest.<ResponseJson<UserEntity>>builder()
                .url("/api/data/get.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<UserEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> uploadDriverLicense(String idFront,String idOppsite,String driverLicencePic,String driverVehiclePic){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/api/driver/data/imageUrl.do")
                .addBody("driverIdcardPicFront",idFront)
                .addBody("driverIdcardPicReverse",idOppsite)
                .addBody("driverLicencePic",driverLicencePic)
                .addBody("driverVehiclePic",driverVehiclePic)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

}
