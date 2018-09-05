package com.zyf.ui.order;

import android.arch.lifecycle.MutableLiveData;
import android.text.TextUtils;

import com.biz.base.BaseViewModel;
import com.zyf.model.OrderModel;
import com.zyf.model.entity.order.PrePayEntity;

/**
 * Created by TCJK on 2018/4/14.
 */

public class PrePayViewModel extends BaseViewModel {

    MutableLiveData<PrePayEntity> prePayLiveData = new MutableLiveData<>();

    public void prePayInfo(String orderNum,String type,float inputPrice){

        if(!TextUtils.isEmpty(orderNum)
                && !TextUtils.isEmpty(type)){
            submitRequest(OrderModel.prePayInfo(orderNum, type,inputPrice), r -> {

                if(r.isOk()){

                    prePayLiveData.postValue(r.data);
                }else {
                    sendError(r);
                }

            });
        }
    }

    public MutableLiveData<PrePayEntity> getPrePayLiveData() {
        return prePayLiveData;
    }
}
