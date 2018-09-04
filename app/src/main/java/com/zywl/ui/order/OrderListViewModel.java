package com.zywl.ui.order;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.biz.http.ResponseJson;
import com.zywl.model.OrderModel;
import com.zywl.model.entity.order.OrderDetailEntity;
import com.zywl.model.entity.order.OrderEntity;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by TCJK on 2018/5/21.
 */

public class OrderListViewModel extends BaseViewModel {

    protected MutableLiveData<List<OrderEntity>> orderListLiveData = new MutableLiveData<>();
    protected MutableLiveData<List<String>> areaLiveData = new MutableLiveData<>();
    protected MutableLiveData<List<OrderDetailEntity>> orderListByAreaLiveData = new MutableLiveData<>();
    protected MutableLiveData<Boolean> goodsAddOrderLiveData = new MutableLiveData<>();

    public void orderListByGoodsNum(String goodsNum){

        submitRequest(OrderModel.orderListByGoodsNum(goodsNum), r -> {

            if(r.isOk()){
                orderListLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void getArea(String city){

        submitRequest(OrderModel.getArea(city), r -> {

            if(r.isOk()){
                areaLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void orderListByArea(String area){

        submitRequest(OrderModel.orderListByArea(area), r -> {

            if(r.isOk()){
                orderListByAreaLiveData.postValue(r.data);
            }else {
                sendError(r);
            }
        });
    }

    public void goodsAddOrder(String goodsNum,String orderNums){

        submitRequest(OrderModel.goodsAddOrder(goodsNum, orderNums), r -> {

            if(r.isOk()){

                goodsAddOrderLiveData.postValue(true);
            }else {
                sendError(r);
            }

        });

    }

    public MutableLiveData<List<OrderEntity>> getOrderListLiveData() {
        return orderListLiveData;
    }

    public MutableLiveData<List<OrderDetailEntity>> getOrderListByAreaLiveData() {
        return orderListByAreaLiveData;
    }

    public MutableLiveData<List<String>> getAreaLiveData() {
        return areaLiveData;
    }

    public MutableLiveData<Boolean> getGoodsAddOrderLiveData() {
        return goodsAddOrderLiveData;
    }
}
