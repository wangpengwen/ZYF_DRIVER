package com.zyf.ui.user.order;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.OrderModel;
import com.zyf.model.WebOrderModel;
import com.zyf.model.entity.order.UserOrderListEntity;
import com.zyf.model.entity.order.WebOrderEntity;

import java.util.List;

/**
 * Created by TCJK on 2018/5/29.
 */

public class WebOrderViewModel extends BaseViewModel {

    protected MutableLiveData<WebOrderEntity> webOrderLiveData = new MutableLiveData<>();
    protected MutableLiveData<WebOrderEntity> takingWebOrderLiveData = new MutableLiveData<>();

    public void getWebOrder(){

        submitRequest(WebOrderModel.getWebOrder(), r -> {

            if(r.isOk()){
                webOrderLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void takingWebOrder(WebOrderEntity entity){

        submitRequest(WebOrderModel.takingWebOrder(entity.webId), r -> {

            if(r.isOk()){
                takingWebOrderLiveData.postValue(entity);
            }else {
                sendError(r);
            }
        });
    }

    public MutableLiveData<WebOrderEntity> getWebOrderLiveData() {
        return webOrderLiveData;
    }

    public MutableLiveData<WebOrderEntity> getTakingWebOrderLiveData() {
        return takingWebOrderLiveData;
    }
}
