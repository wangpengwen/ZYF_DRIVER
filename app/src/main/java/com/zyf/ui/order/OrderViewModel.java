package com.zyf.ui.order;

import android.arch.lifecycle.MutableLiveData;

import com.biz.base.BaseViewModel;
import com.zyf.model.OrderModel;
import com.zyf.model.entity.order.GoodsInfoEntity;
import com.zyf.model.entity.order.OrderDetailEntity;
import com.zyf.model.entity.order.OrderQRCodeEntity;
import com.zyf.model.entity.order.OrderRecipientsEntity;
import com.zyf.model.entity.order.OrderSenderEntity;
import com.zyf.model.entity.order.WebOrderEntity;

/**
 * Created by TCJK on 2018/6/5.
 */

public class OrderViewModel extends BaseViewModel {

    protected MutableLiveData<OrderDetailEntity> createOrderLiveData = new MutableLiveData<>();
    protected MutableLiveData<OrderDetailEntity> orderDetailLiveData = new MutableLiveData<>();
    protected MutableLiveData<OrderQRCodeEntity> qrcodeLiveData = new MutableLiveData<>();
    protected MutableLiveData<Boolean> codLiveData = new MutableLiveData<>();
    protected MutableLiveData<Object> firstReceiveLiveData = new MutableLiveData<>();
    protected MutableLiveData<String> firstFinishLiveData = new MutableLiveData<>();
    protected MutableLiveData<String> lastFinishLiveData = new MutableLiveData<>();
    protected MutableLiveData<WebOrderEntity> lastBeforeFinishLiveData = new MutableLiveData<>();

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

    public void firstDriverReceive(String webDrvId,String idNum){

        submitRequest(OrderModel.firstDriverReceive(webDrvId,idNum), r -> {

            if(r.isOk()){

                firstReceiveLiveData.postValue(true);
            }else {

                sendError(r);
            }
        });
    }

    public void firstDriverFinish(String webDrvId){

        submitRequest(OrderModel.firstDriverFinish(webDrvId), r -> {

            if(r.isOk()){

                firstFinishLiveData.postValue(r.data);
            }else {

                sendError(r);
            }
        });
    }

    public void lastDriverFinish(String orderDrvId,String picUrl){

        submitRequest(OrderModel.lastDriverFinish(orderDrvId,picUrl), r -> {

            if(r.isOk()){

                lastFinishLiveData.postValue(r.data);
            }else {

                sendError(r);
            }
        });
    }

    public void lastBeforeFinish(){

        submitRequest(OrderModel.lastBeforeFinish(), r -> {

            if(r.isOk()){

                lastBeforeFinishLiveData.postValue(r.data);
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

    public MutableLiveData<Object> getFirstReceiveLiveData() {
        return firstReceiveLiveData;
    }

    public MutableLiveData<String> getFirstFinishLiveData() {
        return firstFinishLiveData;
    }

    public MutableLiveData<String> getLastFinishLiveData() {
        return lastFinishLiveData;
    }

    public MutableLiveData<WebOrderEntity> getLastBeforeFinishLiveData() {
        return lastBeforeFinishLiveData;
    }
}
