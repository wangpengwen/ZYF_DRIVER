package com.zywl.ui.authentication;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.biz.http.ResponseJson;
import com.zywl.model.AuthenticationModel;

import rx.functions.Action1;

/**
 * Created by TCJK on 2018/6/15.
 */

public class AuthenticationViewModel extends BaseViewModel {

    protected MutableLiveData<Boolean> bindLiveData = new MutableLiveData<>();

    public void bindIDCard(String orderID,String name,String address,String idNum,int gender,String birthday){

        submitRequest(AuthenticationModel.bindIDCard(orderID, name, address, idNum, gender, birthday), objectResponseJson -> {
            if(objectResponseJson.isOk()){
                bindLiveData.postValue(true);
            }else {
                sendError(objectResponseJson);
            }
        });
    }

    public MutableLiveData<Boolean> getBindLiveData() {
        return bindLiveData;
    }
}
