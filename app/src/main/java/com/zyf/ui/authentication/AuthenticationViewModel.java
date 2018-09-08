package com.zyf.ui.authentication;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.AuthenticationModel;

/**
 * Created by TCJK on 2018/6/15.
 */

public class AuthenticationViewModel extends BaseViewModel {

    protected MutableLiveData<Boolean> bindLiveData = new MutableLiveData<>();

    public void bindIDCard(String name,String idNum){

        submitRequest(AuthenticationModel.bindIDCard(name, idNum), objectResponseJson -> {
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
