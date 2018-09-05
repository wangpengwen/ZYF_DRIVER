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

    public static Observable<ResponseJson<Object>> bindIDCard(String orderID,String name,String address,String idNum,int gender,String birthday){

        return RestRequest.<ResponseJson<Object>>builder()
                .url("/business/newthorder.do")
                .addBody("thirdpartyOrderNum", orderID)
                .addBody("thirdpartySenderName", name)
                .addBody("thirdpartySenderAddr", address)
                .addBody("thirdpartySenderIdcard", idNum)
                .addBody("thirdpartySenderSexual", gender+"")
                .addBody("thirdpartySenderBirthday", birthday)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

}
