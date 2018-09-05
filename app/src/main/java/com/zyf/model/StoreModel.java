package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.store.StoreEntity;
import com.zyf.net.RestRequest;

import java.util.List;

import rx.Observable;

/**
 * Created by TCJK on 2018/6/5.
 */

public class StoreModel {

    public static Observable<ResponseJson<List<StoreEntity>>> getStoreList(){

        return RestRequest.<ResponseJson<List<StoreEntity>>>builder()
                .url("/business/querystorage.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<StoreEntity>>>() {
                }.getType())
                .requestJson();
    }


    public static Observable<ResponseJson<Object>> bindStore(String storeNum){

        return RestRequest.<ResponseJson<Object>>builder()
                .addBody("storageNum",storeNum)
                .url("/business/setuserstg.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }
}
