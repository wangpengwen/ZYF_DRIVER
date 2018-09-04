package com.zywl.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zywl.model.entity.goods.StationEntity;
import com.zywl.net.RestRequest;

import java.util.List;

import rx.Observable;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StationModel {

    public static Observable<ResponseJson<List<StationEntity>>> findStation(String stationName){

        return RestRequest.<ResponseJson<List<StationEntity>>>builder()
                .addBody("stationName",stationName)
                .addBody("marker","0")
                .url("/user/getstationname.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<StationEntity>>>() {
                }.getType())
                .requestJson();
    }

}
