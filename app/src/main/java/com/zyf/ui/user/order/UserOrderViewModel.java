package com.zyf.ui.user.order;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.OrderModel;
import com.zyf.model.entity.order.UserOrderListEntity;
import com.zyf.model.entity.order.WebOrderEntity;

import java.util.List;

/**
 * Created by TCJK on 2018/5/29.
 */

public class UserOrderViewModel extends BaseViewModel {

    protected MutableLiveData<List<WebOrderEntity>> userOrderListLiveData = new MutableLiveData<>();
    protected MutableLiveData<Boolean> delOrderLiveData = new MutableLiveData<>();

    public void getUserOrder(){

        submitRequest(OrderModel.userOrder(), r -> {

            if(r.isOk()){
                userOrderListLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void delOrder(String orderNum){

        submitRequest(OrderModel.delOrder(orderNum), r -> {

            if(r.isOk()){
                delOrderLiveData.postValue(true);
            }else {
                sendError(r);
            }
        });

    }

    public MutableLiveData<List<WebOrderEntity>> getUserOrderListLiveData() {
        return userOrderListLiveData;
    }

    public MutableLiveData<Boolean> getDelOrderLiveData() {
        return delOrderLiveData;
    }
}
