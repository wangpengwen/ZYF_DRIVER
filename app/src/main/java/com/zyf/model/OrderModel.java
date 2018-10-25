package com.zyf.model;

import com.biz.http.BaseRequest;
import com.biz.http.ResponseJson;
import com.biz.http.RestMethodEnum;
import com.google.gson.reflect.TypeToken;
import com.zyf.model.entity.order.GoodsInfoEntity;
import com.zyf.model.entity.order.OrderDetailEntity;
import com.zyf.model.entity.order.OrderEntity;
import com.zyf.model.entity.order.OrderQRCodeEntity;
import com.zyf.model.entity.order.OrderRecipientsEntity;
import com.zyf.model.entity.order.OrderSenderEntity;
import com.zyf.model.entity.order.PrePayEntity;
import com.zyf.model.entity.order.UserOrderListEntity;
import com.zyf.model.entity.order.WebOrderEntity;
import com.zyf.net.RestRequest;

import java.util.List;

import rx.Observable;

/**
 * Created by TCJK on 2018/4/14.
 */

public class OrderModel {

    public static final String PAY_TYPE_WXPAY = "0";
    public static final String PAY_TYPE_ALIPAY = "1";
    public static final String PAY_TYPE_PINGAN = "3";
    public static final String PAY_TYPE_COD = "4";

    public static Observable<ResponseJson<OrderDetailEntity>> createOrder(OrderSenderEntity sender, OrderRecipientsEntity recipients, GoodsInfoEntity goodsInfo, String remark,String orderCod,String addtionalCostPrice){

        return RestRequest.<ResponseJson<OrderDetailEntity>>builder()
                .url("/business/neworder.do")
                .addBody("cargoAmount", goodsInfo.cargoAmount)
                .addBody("cargoHeight",goodsInfo.cargoHeight)
                .addBody("cargoWeight",goodsInfo.cargoWeight)
                .addBody("cargoLong",goodsInfo.cargoLong)
                .addBody("cargoWide",goodsInfo.cargoWide)
                .addBody("cargoName",goodsInfo.cargoName)
                .addBody("reciverPhone",recipients.getReciverPhone())
                .addBody("reciverProvince",recipients.getReciverProvince())
                .addBody("reciverCity",recipients.getReciverCity())
                .addBody("reciverArea",recipients.getReciverArea())
                .addBody("reciverName",recipients.getReciverName())
                .addBody("reciverAddr",recipients.getReciverAddr())
                .addBody("senderPhone",sender.getSenderPhone())
                .addBody("senderProvince",sender.getSenderProvince())
                .addBody("senderCity",sender.getSenderCity())
                .addBody("senderArea",sender.getSenderArea())
                .addBody("senderAddr",sender.getSenderAddr())
                .addBody("senderIdcard",sender.getSenderIdcard())
                .addBody("senderSexual",sender.getSenderSexual())
                .addBody("senderName",sender.getSenderName())
                .addBody("orderNotes",remark)
                .addBody("orderCod",orderCod)
                .addBody("orderAddedFee",addtionalCostPrice)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<OrderDetailEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<WebOrderEntity>> requestOrder(String orderNum){

        return RestRequest.<ResponseJson<WebOrderEntity>>builder()
                .url("/api/shrt/drv/detail/order.do")
                .addBody("webDrvId", orderNum)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<WebOrderEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<WebOrderEntity>>> userOrder(){

        return RestRequest.<ResponseJson<List<WebOrderEntity>>>builder()
                .url("/api/shrt/drv/all/order.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<WebOrderEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<PrePayEntity>> prePayInfo(String orderNum,String payWay,float inputPrice){

        BaseRequest<ResponseJson<PrePayEntity>> request = RestRequest.builder();
        request.addBody("orderNum", orderNum)
                .addBody("payWay", payWay);
        if(inputPrice>0)
            request.addBody("carriage", inputPrice + "");

        return request.url("/business/payment.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<PrePayEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<OrderQRCodeEntity>> qrCode(String orderNum){

        return RestRequest.<ResponseJson<OrderQRCodeEntity>>builder()
                .url("/business/getordercode.do")
                .addBody("orderNum", orderNum)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<OrderQRCodeEntity>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<OrderEntity>>> orderListByGoodsNum(String goodsNum){

        return RestRequest.<ResponseJson<List<OrderEntity>>>builder()
                .url("/business/maniunder.do")
                .addBody("manifestNum", goodsNum)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<OrderEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<String>>> getArea(String city){

        return RestRequest.<ResponseJson<List<String>>>builder()
                .url("/business/getarea.do")
                .addBody("city", city)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<String>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<OrderDetailEntity>>> orderListByArea(String area){

        return RestRequest.<ResponseJson<List<OrderDetailEntity>>>builder()
                .url("/business/ordeybyarea.do")
                .addBody("area", area)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<OrderDetailEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<List<OrderDetailEntity>>> goodsAddOrder(String goodsNum,String orderNums){

        return RestRequest.<ResponseJson<List<OrderDetailEntity>>>builder()
                .addBody("orderNums", orderNums)
                .addBody("manifestNum", goodsNum)
                .url("/business/bathmanifest.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<List<OrderDetailEntity>>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> codPay(String orderNum,float inputPrice){

        BaseRequest<ResponseJson<Object>> request = RestRequest.<ResponseJson<Object>>builder();
        request.addBody("orderNum", orderNum);
        if(inputPrice>0)
            request.addBody("carriage",inputPrice+"");

        return request.url("/business/postpaid.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> delOrder(String orderNum){

        return RestRequest.<ResponseJson<Object>>builder()
                .addBody("orderNum",orderNum)
                .url("/business/delorder.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<Object>> firstDriverReceive(String webDrvId,String idNum){

        return RestRequest.<ResponseJson<Object>>builder()
                .addBody("webDrvId",webDrvId)
                .addBody("senderIdcard",idNum)
                .url("/api/shrt/drv/receive/goods.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<Object>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<String>> firstDriverFinish(String webDrvId){

        return RestRequest.<ResponseJson<String>>builder()
                .addBody("webDrvId",webDrvId)
                .url("/api/shrt/drv/cmpl/qr/code.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<String>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<String>> lastDriverFinish(String orderDrvId,String picUrl){

        return RestRequest.<ResponseJson<String>>builder()
                .url("/api/shrt/drv/deliver/goods.do")
                .addBody("orderDrvId",orderDrvId)
                .addBody("picUrl",picUrl)
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<String>>() {
                }.getType())
                .requestJson();
    }

    public static Observable<ResponseJson<WebOrderEntity>> lastBeforeFinish(){

        return RestRequest.<ResponseJson<WebOrderEntity>>builder()
                .url("/api/get/shrt/drv/devli/order.do")
                .restMethod(RestMethodEnum.POST)
                .token(UserModel.getInstance().getUserToken())
                .setToJsonType(new TypeToken<ResponseJson<WebOrderEntity>>() {
                }.getType())
                .requestJson();
    }
}
