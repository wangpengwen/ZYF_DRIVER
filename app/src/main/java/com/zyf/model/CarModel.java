package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.car.CarEntity;
import com.zyf.net.RestRequest;

import java.util.List;

import rx.Observable;

/**
 * Created by ltxxx on 2018/6/5.
 */

public class CarModel {

    public static Observable<ResponseJson<List<CarEntity>>> findCar(String carNum){

        return RestRequest.<ResponseJson<List<CarEntity>>>builder()
                .addBody("truckNo",carNum)
                .addBody("marker","0")
                .url("/user/querytruckinfo.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<CarEntity>>>() {
                }.getType())
                .requestJson();
    }

}
