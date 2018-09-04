package com.zywl.model.entity.order;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TCJK on 2018/6/6.
 */

public class UserOrderItemEntity implements Parcelable {

    String orderManifestState;

    String senderAddr;

    String reciverAddr;

    String orderNum;

    String carriage;

    String senderName;

    String reciverName;

    public String getOrderManifestState() {
        return orderManifestState;
    }

    public void setOrderManifestState(String orderManifestState) {
        this.orderManifestState = orderManifestState;
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public UserOrderItemEntity() {
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderManifestState);
        dest.writeString(this.senderAddr);
        dest.writeString(this.reciverAddr);
        dest.writeString(this.orderNum);
        dest.writeString(this.carriage);
        dest.writeString(this.senderName);
        dest.writeString(this.reciverName);
    }

    protected UserOrderItemEntity(Parcel in) {
        this.orderManifestState = in.readString();
        this.senderAddr = in.readString();
        this.reciverAddr = in.readString();
        this.orderNum = in.readString();
        this.carriage = in.readString();
        this.senderName = in.readString();
        this.reciverName = in.readString();
    }

    public static final Creator<UserOrderItemEntity> CREATOR = new Creator<UserOrderItemEntity>() {
        @Override
        public UserOrderItemEntity createFromParcel(Parcel source) {
            return new UserOrderItemEntity(source);
        }

        @Override
        public UserOrderItemEntity[] newArray(int size) {
            return new UserOrderItemEntity[size];
        }
    };
}
