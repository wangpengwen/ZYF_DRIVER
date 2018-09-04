package com.zywl.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zywl.model.entity.car.CarEntity;
import com.zywl.model.entity.goods.StationEntity;
import com.zywl.net.RestRequest;

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
