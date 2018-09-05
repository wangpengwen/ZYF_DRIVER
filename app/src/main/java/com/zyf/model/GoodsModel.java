package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.goods.GoodsEntity;
import com.zyf.net.RestRequest;

import java.util.List;

import rx.Observable;

/**
 * Created by TCJK on 2018/6/5.
 */

public class GoodsModel {

    public static Observable<ResponseJson<List<GoodsEntity>>> goodsList(){

        return RestRequest.<ResponseJson<List<GoodsEntity>>>builder()
                .url("/business/getmanibycom.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<GoodsEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<String>> createGoods(String goodsName,String startCode,String endCode,String carCode){

        return RestRequest.<ResponseJson<String>>builder()
                .addBody("manifestName",goodsName)
                .addBody("truckNum",carCode)
                .addBody("start",startCode)
                .addBody("end",endCode)
                .url("/business/emptymanifest.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<String>>() {
                }.getType())
                .requestJson();
    }

}
