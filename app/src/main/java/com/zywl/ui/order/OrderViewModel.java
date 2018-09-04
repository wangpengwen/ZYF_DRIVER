package com.zywl.ui.order;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zywl.model.OrderModel;
import com.zywl.model.entity.order.GoodsInfoEntity;
import com.zywl.model.entity.order.OrderDetailEntity;
import com.zywl.model.entity.order.OrderQRCodeEntity;
import com.zywl.model.entity.order.OrderRecipientsEntity;
import com.zywl.model.entity.order.OrderSenderEntity;

/**
 * Created by TCJK on 2018/6/5.
 */

public class OrderViewModel extends BaseViewModel {

    protected MutableLiveData<OrderDetailEntity> createOrderLiveData = new MutableLiveData<>();
    protected MutableLiveData<OrderDetailEntity> orderDetailLiveData = new MutableLiveData<>();
    protected MutableLiveData<OrderQRCodeEntity> qrcodeLiveData = new MutableLiveData<>();
    protected MutableLiveData<Boolean> codLiveData = new MutableLiveData<>();

    public void createOrder(OrderSenderEntity sender, OrderRecipientsEntity recipients, GoodsInfoEntity goodsInfo,String remark,String orderCod,String addtionalCostPrice){

        submitRequest(OrderModel.createOrder(sender, recipients, goodsInfo,remark,orderCod,addtionalCostPrice), objectResponseJson -> {

            if(objectResponseJson.isOk()){

                createOrderLiveData.postValue(objectResponseJson.data);
            }else {

                sendError(objectResponseJson);
            }
        });
    }

    public void requestOrder(String orderNum){

        submitRequest(OrderModel.requestOrder(orderNum), r -> {

            if(r.isOk()){

                orderDetailLiveData.postValue(r.data);
            }else {

                sendError(r);
            }
        });
    }

    public void qrCode(String orderNum){

        submitRequest(OrderModel.qrCode(orderNum), r -> {

            if(r.isOk()){

                qrcodeLiveData.postValue(r.data);
            }else {

                sendError(r);
            }
        });
    }

    public void orderTypeCOD(String orderNum,float inputPrice){

        submitRequest(OrderModel.codPay(orderNum,inputPrice), r -> {

            if(r.isOk()){

                codLiveData.postValue(true);
            }else {

                sendError(r);
            }
        });
    }

    public MutableLiveData<OrderDetailEntity> getCreateOrderLiveData() {
        return createOrderLiveData;
    }

    public MutableLiveData<OrderDetailEntity> getOrderDetailLiveData() {
        return orderDetailLiveData;
    }

    public MutableLiveData<OrderQRCodeEntity> getQrcodeLiveData() {
        return qrcodeLiveData;
    }

    public MutableLiveData<Boolean> getCodLiveData() {
        return codLiveData;
    }
}
