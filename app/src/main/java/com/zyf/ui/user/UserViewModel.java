package com.zyf.ui.user;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.UserModel;
import com.zyf.model.entity.UserEntity;

/**
 * Created by TCJK on 2018/3/28.
 */

public class UserViewModel extends BaseViewModel {

    protected MutableLiveData<UserEntity> infoLiveData = new MutableLiveData<>();

    public void userInfo(){

        submitRequest(UserModel.info(), r -> {
            if(r.isOk()){

                infoLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });

    }

    public MutableLiveData<UserEntity> getInfoLiveData() {
        return infoLiveData;
    }
}
