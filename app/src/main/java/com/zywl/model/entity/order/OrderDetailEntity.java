package com.zywl.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/6.
 */

public class OrderDetailEntity implements Parcelable {

    String orderNum;

    OrderRecipientsEntity reciver;

    OrderSenderEntity sender;

    GoodsInfoEntity cargo;
    //0未支付  1已支付
    String orderManifestState;
    //0未回单  1已回单
    String orderReturnState;

    String orderIsDelete;

    float carriage;

    String orderIsManifest;

    String orderNotes;

    String orderCod;

    String orderFreightForward;

    String orderAddedFee;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public OrderRecipientsEntity getReciver() {
        return reciver;
    }

    public void setReciver(OrderRecipientsEntity reciver) {
        this.reciver = reciver;
    }

    public OrderSenderEntity getSender() {
        return sender;
    }

    public void setSender(OrderSenderEntity sender) {
        this.sender = sender;
    }

    public GoodsInfoEntity getCargo() {
        return cargo;
    }

    public void setCargo(GoodsInfoEntity cargo) {
        this.cargo = cargo;
    }

    public String getOrderManifestState() {
        return orderManifestState;
    }

    public void setOrderManifestState(String orderManifestState) {
        this.orderManifestState = orderManifestState;
    }

    public String getOrderReturnState() {
        return orderReturnState;
    }

    public void setOrderReturnState(String orderReturnState) {
        this.orderReturnState = orderReturnState;
    }

    public OrderDetailEntity() {
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getOrderCod() {
        return orderCod;
    }

    public void setOrderCod(String orderCod) {
        this.orderCod = orderCod;
    }

    public String getOrderIsDelete() {
        return orderIsDelete;
    }

    public void setOrderIsDelete(String orderIsDelete) {
        this.orderIsDelete = orderIsDelete;
    }

    public float getCarriage() {
        return carriage;
    }

    public void setCarriage(float carriage) {
        this.carriage = carriage;
    }

    public String getOrderIsManifest() {
        return orderIsManifest;
    }

    public void setOrderIsManifest(String orderIsManifest) {
        this.orderIsManifest = orderIsManifest;
    }

    public String getOrderFreightForward() {
        return orderFreightForward;
    }

    public void setOrderFreightForward(String orderFreightForward) {
        this.orderFreightForward = orderFreightForward;
    }

    public String getOrderAddedFee() {
        return orderAddedFee;
    }

    public void setOrderAddedFee(String orderAddedFee) {
        this.orderAddedFee = orderAddedFee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNum);
        dest.writeParcelable(this.reciver, flags);
        dest.writeParcelable(this.sender, flags);
        dest.writeParcelable(this.cargo, flags);
        dest.writeString(this.orderManifestState);
        dest.writeString(this.orderReturnState);
        dest.writeString(this.orderIsDelete);
        dest.writeFloat(this.carriage);
        dest.writeString(this.orderIsManifest);
        dest.writeString(this.orderNotes);
        dest.writeString(this.orderCod);
        dest.writeString(this.orderFreightForward);
        dest.writeString(this.orderAddedFee);
    }

    protected OrderDetailEntity(Parcel in) {
        this.orderNum = in.readString();
        this.reciver = in.readParcelable(OrderRecipientsEntity.class.getClassLoader());
        this.sender = in.readParcelable(OrderSenderEntity.class.getClassLoader());
        this.cargo = in.readParcelable(GoodsInfoEntity.class.getClassLoader());
        this.orderManifestState = in.readString();
        this.orderReturnState = in.readString();
        this.orderIsDelete = in.readString();
        this.carriage = in.readFloat();
        this.orderIsManifest = in.readString();
        this.orderNotes = in.readString();
        this.orderCod = in.readString();
        this.orderFreightForward = in.readString();
        this.orderAddedFee = in.readString();
    }

    public static final Creator<OrderDetailEntity> CREATOR = new Creator<OrderDetailEntity>() {
        @Override
        public OrderDetailEntity createFromParcel(Parcel source) {
            return new OrderDetailEntity(source);
        }

        @Override
        public OrderDetailEntity[] newArray(int size) {
            return new OrderDetailEntity[size];
        }
    };
}
