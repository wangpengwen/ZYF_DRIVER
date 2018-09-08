package com.zyf.ui.info;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.AuthenticationModel;

/**
 * Created by ltxxx on 2018/9/7.
 */

public class ValidateViewModel extends BaseViewModel {

    protected MutableLiveData<Object> vehicleLiveData = new MutableLiveData<>();

    public void uploadVehicle(String vehicleType,String num){

        submitRequest(AuthenticationModel.bindVehicleInfo(vehicleType,num), r -> {

            if(r.isOk()){
                vehicleLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public MutableLiveData<Object> getVehicleLiveData() {
        return vehicleLiveData;
    }
}
