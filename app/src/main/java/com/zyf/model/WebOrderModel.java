package com.zyf.model;

import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.order.WebOrderEntity;
import com.zyf.net.RestRequest;

import java.util.List;

import rx.Observable;

/**
 * Title: WebOrderModel
 * Description:
 * Copyright:Copyright(c)2018
 * Company:同城酒库电子商务有限公司
 * CreateTime:2018/9/13  10:34
 *
 * @author liutong
 */
public class WebOrderModel {

    public static Observable<ResponseJson<WebOrderEntity>> getWebOrder(){

        return RestRequest.<ResponseJson<WebOrderEntity>>builder()
                .url("/api/shrt/drv/pull/order.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<WebOrderEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> takingWebOrder(String webId){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/api/shrt/drv/receive/order.do")
                .addBody("webId",webId)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> lastDriverRecieve(String orderNum){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/api/last/mile/receive/goods.do")
                .addBody("orderNum",orderNum)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

}
