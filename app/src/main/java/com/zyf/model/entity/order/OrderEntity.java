package com.zyf.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/5/30.
 */

public class OrderEntity implements Parcelable {

    String orderNum;

    String cargoAmount;

    String senderAddr;

    String reciverAddr;

    String cargoName;

    String reciverName;

    String senderName;


    public OrderEntity() {
    }


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCargoAmount() {
        return cargoAmount;
    }

    public void setCargoAmount(String cargoAmount) {
        this.cargoAmount = cargoAmount;
    }

    public String getSenderAddr() {
        return senderAddr;
    }

    public void setSenderAddr(String senderAddr) {
        this.senderAddr = senderAddr;
    }

    public String getReciverAddr() {
        return reciverAddr;
    }

    public void setReciverAddr(String reciverAddr) {
        this.reciverAddr = reciverAddr;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderNum);
        dest.writeString(this.cargoAmount);
        dest.writeString(this.senderAddr);
        dest.writeString(this.reciverAddr);
        dest.writeString(this.cargoName);
        dest.writeString(this.reciverName);
        dest.writeString(this.senderName);
    }

    protected OrderEntity(Parcel in) {
        this.orderNum = in.readString();
        this.cargoAmount = in.readString();
        this.senderAddr = in.readString();
        this.reciverAddr = in.readString();
        this.cargoName = in.readString();
        this.reciverName = in.readString();
        this.senderName = in.readString();
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel source) {
            return new OrderEntity(source);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };
}
